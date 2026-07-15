package su.pernova.assertions;

import static java.lang.System.arraycopy;
import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.function.BiFunction;
import java.util.function.Function;

import internal.su.pernova.assertions.matchers.BiMatcher;
import internal.su.pernova.assertions.matchers.ForwardingMatcher;
import internal.su.pernova.assertions.matchers.ImplicitMatcher;

/**
 * This class implements a framework that allows providing context to matchers.
 * The semantic and behavior of such contextual matchers depends on the context.
 * There are three different categories of contextual matchers.
 *
 * <h1>Context-providing matchers</h1>
 * <p>
 * Forward-providing context matchers provide context to leaf matchers further in the syntax tree.
 *
 * <h1>Contextual leaf matchers</h1>
 *
 * @since 2.0.0
 */
public final class Context {

	private static final Map<Matcher, ImplicitMatcherFactory> MATCHER_FACTORIES_BY_PROTOTYPE = new WeakHashMap<>();

	private static final Map<Matcher, ContextProvidingFunction> COMPLETION_FUNCTIONS_BY_MATCHER = new WeakHashMap<>();

	private Function<Object, Object> actualValueTransformation;

	private Function<Object, Object> expectedValueTransformation;

	private final Map<Matcher, MatcherFactory> matcherFactoriesByOrigin = new IdentityHashMap<>();

	private final Map<Matcher, Matcher> originsByDestination = new IdentityHashMap<>();

	public Matcher forward(Matcher origin, MatcherFactory matcherFactory, Matcher destination) {
		requireNonNull(origin, "origin is null");
		requireNonNull(matcherFactory, "matcher factory is null");
		requireNonNull(destination, "destination is null");
		matcherFactoriesByOrigin.put(origin, matcherFactory);
		originsByDestination.put(destination, origin);
		final Matcher contextualizedDestination = destination.contextualize(this);
		if (contextualizedDestination == destination) {
			// Preserve object identity.
			return origin;
		}
		return matcherFactory.create(contextualizedDestination);
	}

	public Matcher contextualize(Matcher matcher) {
		requireNonNull(matcher, "matcher is null");
		final Matcher contextualizedMatcher = matcher.contextualize(this);
		if (contextualizedMatcher != matcher) {
			final Matcher origin = originsByDestination.get(matcher);
			if (origin != null) {
				originsByDestination.put(origin, contextualizedMatcher);
			}
		}
		return contextualizedMatcher;
	}

	private void putMatcherFactory(Matcher origin, MatcherFactory matcherFactory) {
		matcherFactoriesByOrigin.put(
				requireNonNull(origin, "origin is null"),
				requireNonNull(matcherFactory, "matcher factory is null")
		);
	}

	public Matcher contextualize(Matcher matcher, MatcherFactory matcherFactory, Object expectedValue) {
		putMatcherFactory(matcher, matcherFactory);
		List<MatcherFactory> wrapper = buildWrapper(matcher);
		if (expectedValueTransformation != null) {
			final Object transformedExpectedValue = expectedValueTransformation.apply(expectedValue);
			if (transformedExpectedValue != expectedValue) {
				return wrap(wrapper, matcherFactory.create(transformedExpectedValue));
			}
		}
		return wrap(wrapper, matcher);
	}

	private List<MatcherFactory> buildWrapper(Matcher matcher) {
		final ArrayList<MatcherFactory> wrapper = new ArrayList<>(1);
		for (Matcher origin = originsByDestination.get(matcher); origin != null; origin = originsByDestination.get(origin)) {
			final MatcherFactory matcherFactory = matcherFactoriesByOrigin.get(origin);
			if (matcherFactory == null) {
				break;
			}
			wrapper.add(matcherFactory);
		}
		wrapper.trimToSize();
		return wrapper;
	}

	private static Matcher wrap(List<MatcherFactory> wrapper, Matcher matcher) {
		for (MatcherFactory matcherFactory : wrapper) {
			matcher = matcherFactory.create(matcher);
		}
		return matcher;
	}

	public Matcher contextualize(Matcher origin, Matcher destination, Function<Matcher, Matcher> contextualizer) {
		final Matcher oldOrigin = originsByDestination.put(destination, origin);
		if (oldOrigin != null) {
			originsByDestination.put(origin, oldOrigin);
		}
		final Matcher contextualizedDestination = destination.contextualize(this);
		if (contextualizedDestination == destination) {
			return origin;
		}
		return contextualizer.apply(contextualizedDestination);
	}

	/**
	 * To be called by {@link BiMatcher#contextualize(Context)}.
	 * The old origin is the {@code this} object where for example {@link Matcher#and} has been called on.
	 * Its origin needs to be rewired to point to the new origin, which is the matcher returned by {@link Matcher#and}.
	 * The destination is context-requiring matcher, that does not need to be rewired.
	 *
	 * @param origin an origin bi-matcher, not {@code null}.
	 * @param destination1 a destination 1, not {@code null}.
	 * @param destination2 a destination 2, not {@code null}.
	 * @param factory a factory for contextualized origin bi-matcher instances.
	 * @return the contextualized origin, which is potentially the same origin if both destinations are already contextualized.
	 */
	public Matcher fork(Matcher origin, Matcher destination1, Matcher destination2, BiFunction<Matcher, Matcher, Matcher> factory) {
		// If destination2 is an ImplicitMatcher, it means that the origin BiMatcher
		if (destination2 instanceof ImplicitMatcher) {
			return origin;
		}
		requireNonNull(origin, "origin is null");
		requireNonNull(destination1, "destination 1 is null");
		requireNonNull(destination2, "destination 2 is null");
		requireNonNull(factory, "factory is null");
		originsByDestination.put(destination1, origin);
		final Matcher contextualizedDestination1 = destination1.contextualize(this);
		final MatcherFactory destination1MatcherFactory = matcherFactoriesByOrigin.get(destination1);
		// If destination1 is a multi-matcher such as AnyOf, destination1MatcherFactory is null.
		if (destination1MatcherFactory != null) {
			final MatcherFactory originMatcherFactory = new ImplicitMatcherFactory(destination1MatcherFactory);
			matcherFactoriesByOrigin.put(origin, originMatcherFactory);
		}
		originsByDestination.put(destination2, origin);
		final Matcher contextualizedDestination2 = destination2.contextualize(this);
		if (contextualizedDestination1 != destination1 || contextualizedDestination2 != destination2) {
			return factory.apply(contextualizedDestination1, contextualizedDestination2);
		}
		return origin;
	}

	/// Contextualizes a multi-matcher such as "all of", "any of" or "none of".
	/// A multi-matcher, for example "any of: [1, 2]", consists of a multi-matcher "any of" and destination matchers for
	/// "1" and "2".
	/// The abstract syntax tree is given by the string representation "anyOf{(1)}{(2)}"
	/// These destination matchers have no context of their own, so they need to be contextualized
	///
	/// If all destinations have already been resolved, this method returns the origin.
	/// If not, this method will use a given factory to instantiate a new multi-matcher for the resolved destinations.
	///
	/// @param origin a multi-matcher origin to resolve, not {@code null}.
	/// @param destinations an array of destinations, not {@code null}.
	/// @param factory a factory function to create a new multi-matcher with for the contextualized destinations, not {@code null}.
	/// @return a resolved multi-matcher, potentially the origin itself, not {@code null}.
	public Matcher resolve(Matcher origin, Matcher[] destinations, Function<Matcher[], Matcher> factory) {
		requireNonNull(origin, "origin is null");
		requireNonNull(destinations, "array of destinations is null");
		// We wouldn't need a factory if we would type the origin as MultiMatcher.
		// We can't do that however, because MultiMatcher is NOT on the API while this class, Context, IS.
		// We can't bleed non-API classes onto the API, so origin remains a Matcher type.
		// This also allows users to implement their own MyMultiMatcher types should they wish to do so.
		requireNonNull(factory, "factory is null");
		Matcher[] contextualizedDestinations = null;
		int i = 0;
		for (Matcher destination : destinations) {
			originsByDestination.put(destination, origin);
			Matcher contextualizedDestination = destination.contextualize(this);
			if (contextualizedDestination != destination) {
				if (contextualizedDestinations == null) {
					contextualizedDestinations = new Matcher[destinations.length];
					arraycopy(destinations, 0, contextualizedDestinations, 0, destinations.length);
				}
				contextualizedDestinations[i] = contextualizedDestination;
			}
			i++;
		}
		return contextualizedDestinations != null ? factory.apply(contextualizedDestinations) : origin;
	}

	public Context forwardMatcherFactory(Matcher origin, MatcherFactory matcherFactory) {
		requireNonNull(origin, "origin is null");
		requireNonNull(matcherFactory, "matcher factory is null");
		matcherFactoriesByOrigin.put(origin, matcherFactory);
		return this;
	}

	public Context forwardValueContextualizer(Function<Object, Object> contextualizer) {
		return forwardActualValueContextualizer(contextualizer).forwardExpectedValueContextualizer(contextualizer);
	}

	public Context forwardActualValueContextualizer(Function<Object, Object> contextualizer) {
		this.actualValueTransformation = contextualizer;
		return this;
	}

	public Context forwardExpectedValueContextualizer(Function<Object, Object> contextualizer) {
		this.expectedValueTransformation = contextualizer;
		return this;
	}

	public Subject contextualize(Subject subject, Object actualValue, Function<Object, Subject> subjectFactory) {
		if (actualValueTransformation == null || subjectFactory == null) {
			return subject;
		}
		return subjectFactory.apply(actualValueTransformation.apply(actualValue));
	}

	public Matcher contextualize(Matcher matcher, Object expectedValue, Function<Object, Matcher> contextualization) {
		if (expectedValueTransformation != null) {
			Object contextualizedExpectedValue = expectedValueTransformation.apply(expectedValue);
			if (contextualizedExpectedValue != expectedValue) {
				final Matcher contextualizedMatcher = contextualization.apply(contextualizedExpectedValue);
				final Matcher origin = originsByDestination.get(matcher);
				if (origin != null) {
					originsByDestination.put(contextualizedMatcher, origin);
				}
				return contextualizedMatcher;
			}
		}
		return matcher;
	}

	/**
	 * Finds the most relevant matcher factory for a given contextual matcher, transforms the expected value, and
	 * returns a contextualized matcher.
	 *
	 * @param matcher a contextual matcher, not {@code null}.
	 * @param expectedValue an expected value, possibly {@code null}.
	 * @return a contextualized matcher, not {@code null}.
	 * @since 2.0.0
	 */
	public Matcher imply(Matcher matcher, Object expectedValue) {
		return imply(matcher, f -> f.create(expectedValue));
	}

	public Matcher imply(Matcher matcher, byte expectedValue) {
		return imply(matcher, f -> f.create(expectedValue));
	}

	public Matcher imply(Matcher matcher, short expectedValue) {
		return imply(matcher, f -> f.create(expectedValue));
	}

	/**
	 * Contextualizes a contextual matcher.
	 *
	 * @param matcher a contextual matcher, not {@code null}.
	 * @param expectedValue an expected value.
	 * @return a contextualized matcher.
	 */
	public Matcher imply(Matcher matcher, int expectedValue) {
		return imply(matcher, f -> f.create(expectedValue));
	}

	public Matcher imply(Matcher matcher, long expectedValue) {
		return imply(matcher, f -> f.create(expectedValue));
	}

	public Matcher imply(Matcher matcher, double expectedValue) {
		return imply(matcher, f -> f.create(expectedValue));
	}

	public Matcher imply(Matcher matcher, float expectedValue) {
		return imply(matcher, f -> f.create(expectedValue));
	}

	public Matcher imply(Matcher matcher, char expectedValue) {
		return imply(matcher, f -> f.create(expectedValue));
	}

	public Matcher imply(Matcher matcher, boolean expectedValue) {
		return imply(matcher, f -> f.create(expectedValue));
	}

	private Matcher imply(Matcher matcher, Function<MatcherFactory, Matcher> contextualizer) {
		final Matcher initialOrigin = originsByDestination.get(matcher);
		for (Matcher origin = initialOrigin; origin != null; origin = originsByDestination.get(origin)) {
			final MatcherFactory matcherFactory = matcherFactoriesByOrigin.get(origin);
			if (matcherFactory != null) {
				return new ImplicitMatcher(contextualizer.apply(matcherFactory));
			}
		}
		throw new IllegalStateException("no origin");
	}

	public Matcher contextualizeExpectedValue(Matcher matcher, Object expectedValue, MatcherFactory matcherFactory) {
		if (expectedValueTransformation != null) {
			final Object contextualizedExpectedValue = expectedValueTransformation.apply(expectedValue);
			if (contextualizedExpectedValue != expectedValue) {
				final Matcher contextualizedMatcher = matcherFactory.create(contextualizedExpectedValue);
				final Matcher origin = originsByDestination.get(matcher);
				if (origin != null) {
					originsByDestination.put(contextualizedMatcher, origin);
				}
				return contextualizedMatcher;
			}
		}
		return matcher;
	}

	/**
	 * Registers a matcher factory for a destination matcher, returning the destination matcher.
	 * The matcher factory will be used to fork the given destination matcher anonymously
	 * when a forking method such as {@link Matcher#and} is called on it.
	 * This method is thread-safe.
	 *
	 * @param prototype a prototype matcher, which must not be {@code null}.
	 * @param matcherFactory a matcher factory, which must not be {@code null}.
	 * @return the prototype matcher.
	 */
	public static Matcher putMatcherFactory2(Matcher prototype, MatcherFactory matcherFactory) {
		requireNonNull(prototype, "prototype is null");
		requireNonNull(matcherFactory, "matcher factory is null for prototype: " + prototype);
		final ImplicitMatcherFactory implicitMatcherFactory = new ImplicitMatcherFactory(matcherFactory);
		synchronized (Context.class) {
			MATCHER_FACTORIES_BY_PROTOTYPE.put(prototype, implicitMatcherFactory);
		}
		return prototype;
	}

	public static synchronized Matcher forwardMatcherFactory(Function<Matcher, Matcher> forwardingFunction, CharSequence name, Matcher destination) {
		requireNonNull(destination, "destination is null");
		requireNonNull(forwardingFunction, "forwarding function is null");
		final ImplicitMatcherFactory destinationFactory = MATCHER_FACTORIES_BY_PROTOTYPE.get(destination);
		requireNonNull(destinationFactory, "factory is null for: " + destination);
		final Matcher forwardMatcher = forwardingFunction.apply(destination);
		requireNonNull(forwardMatcher, "forwarding function returned null for: " + destination);
		return forwardMatcher;
	}

	public static Matcher forwardMatcherFactory(CharSequence name, Matcher destination) {
		return forwardMatcherFactory(d -> new ForwardingMatcher(name, d), name, destination);
	}
}
