package su.pernova.assertions;

import static java.util.Objects.requireNonNull;

import java.util.IdentityHashMap;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.function.BiFunction;
import java.util.function.Function;

import internal.su.pernova.assertions.matchers.ForwardingMatcher;
import internal.su.pernova.assertions.matchers.ImplicitForwardingMatcher;
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

	public Context forward(Matcher origin, Matcher... destinations) {
		requireNonNull(origin, "origin is null");
		requireNonNull(destinations, "array of destinations is null");
		for (Matcher destination : destinations) {
			final Matcher oldOrigin = originsByDestination.put(destination, origin);
			if (oldOrigin != null) {
				originsByDestination.put(oldOrigin, origin);
			}
		}
		return this;
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
		if (expectedValueTransformation != null) {
			final Object transformedExpectedValue = expectedValueTransformation.apply(expectedValue);
			if (transformedExpectedValue != expectedValue) {
				return matcherFactory.create(transformedExpectedValue);
			}
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
	 * To be called by {@link internal.su.pernova.assertions.matchers.BiMatcher#contextualize(Context)}.
	 * The first destination is the {@code this} object where for example {@link Matcher#and} has been called on.
	 * Its origin needs to be rewired to point to the origin now rather than to the destination.
	 * The second destination is another object, that does not need to be rewired.
	 *
	 * @param newOrigin an uncontextualized origin, not {@code null}.
	 * @param oldOrigin an uncontextualized destination, not {@code null}.
	 * @param destination an uncontextualized destination, not {@code null}.
	 * @param contextualizer a contextualizer (contextualization function) which is a factory for contextualized origin instances.
	 * @return the contextualized origin, which is potentially the same origin if both destinations contextualized into themselves.
	 */
	public Matcher fork(Matcher newOrigin, Matcher oldOrigin, Matcher destination, BiFunction<Matcher, Matcher, Matcher> contextualizer) {
		destination = new ImplicitMatcher(new ImplicitForwardingMatcher(oldOrigin.name(), destination));
		originsByDestination.put(oldOrigin, newOrigin);
		originsByDestination.put(destination, newOrigin);
		final Matcher contextualizedOldOrigin = oldOrigin.contextualize(this);
		final MatcherFactory matcherFactory = matcherFactoriesByOrigin.get(oldOrigin);
		matcherFactoriesByOrigin.put(newOrigin, matcherFactory);
		final Matcher contextualizedDestination = destination.contextualize(this);
		return contextualizer.apply(contextualizedOldOrigin, contextualizedDestination);
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

	public static Matcher newIncompleteMatcher(ContextProvidingFunction contextProvidingFunction) {
		final ContextSensitiveMatcher matcher = new ContextSensitiveMatcher();
		COMPLETION_FUNCTIONS_BY_MATCHER.put(matcher, contextProvidingFunction);
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
		return forwardMatcherFactory(d -> new ImplicitForwardingMatcher(name, d), name, destination);
	}
}
