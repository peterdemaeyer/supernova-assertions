package su.pernova.assertions;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.function.Function;

import internal.su.pernova.assertions.matchers.ContextProvidingMatcher;

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

	public static Matcher newIncompleteMatcher(ContextProvidingFunction contextProvidingFunction) {
		final ContextSensitiveMatcher matcher = new ContextSensitiveMatcher();
		COMPLETION_FUNCTIONS_BY_MATCHER.put(matcher, contextProvidingFunction);
		return matcher;
	}

	/**
	 * Registers a matcher factory for a destination matcher, returning the destination matcher.
	 * The matcher factory will be used to fork the given destination matcher anonylously
	 * when a forking method such as {@link Matcher#and} is called on it.
	 * This method is thread-safe.
	 *
	 * @param prototype a prototype matcher, which must not be {@code null}.
	 * @param matcherFactory a matcher factory, which must not be {@code null}.
	 * @return the prototype matcher.
	 */
	public static Matcher putMatcherFactory(Matcher prototype, MatcherFactory matcherFactory) {
		requireNonNull(prototype, "prototype is null");
		requireNonNull(matcherFactory, "matcher factory is null for prototype: " + prototype);
		final ImplicitMatcherFactory implicitMatcherFactory = new ImplicitMatcherFactory(matcherFactory);
		synchronized (Context.class) {
			MATCHER_FACTORIES_BY_PROTOTYPE.put(prototype, implicitMatcherFactory);
		}
		return prototype;
	}

	public static Matcher putMatcherFactory(Function<Matcher, Matcher> prototypeFunction, Matcher destination, MatcherFactory matcherFactory) {
		requireNonNull(prototypeFunction, "prototype function is null");
		requireNonNull(destination, "destination is null");
		requireNonNull(matcherFactory, "matcher factory is null");
		final ImplicitMatcherFactory implicitMatcherFactory = new ImplicitMatcherFactory(matcherFactory);
		synchronized (Context.class) {
			final ContextProvidingFunction contextProvidingFunction = COMPLETION_FUNCTIONS_BY_MATCHER.get(destination);
			Matcher prototype;
			if (contextProvidingFunction != null) {
				prototype = prototypeFunction.apply(contextProvidingFunction.provide(implicitMatcherFactory));
			} else {
				prototype = prototypeFunction.apply(destination);
			}
			requireNonNull(prototype, "prototype function returns null");
			MATCHER_FACTORIES_BY_PROTOTYPE.put(prototype, implicitMatcherFactory);
			return prototype;
		}
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
		return forwardMatcherFactory(d -> new ContextProvidingMatcher(name, d), name, destination);
	}

	/**
	 * Creates an anonymous clone of a given destination matcher for a given expected value.
	 * This method is thread-safe.
	 *
	 * @param origin a destination matcher, which must not be {@code null}.
	 * @param destination a function that creates a matcher for a given factory, which must not be {@code null}.
	 * @return the bi-matcher, never {@code null}.
	 * @see #forwardMatcherFactory
	 */
	public static Matcher fork(ForkFunction forkFunction, Matcher origin, ContextProvidingFunction destination) {
		return fork(forkFunction, origin, newIncompleteMatcher(destination));
	}

	public static synchronized Matcher fork(ForkFunction forkFunction, Matcher origin, Matcher destination) {{
		final ImplicitMatcherFactory matcherFactory = MATCHER_FACTORIES_BY_PROTOTYPE.get(origin);
		if (matcherFactory != null) {
			// If the origin is a complete matcher, we complete the destination straight away.
			return forkFunction.fork(origin, Context.provide(destination, matcherFactory));
		}}
		return newIncompleteMatcher(matcherFactory -> {
			synchronized (Context.class) {
				final Matcher destination0 = provide(origin, matcherFactory);
				final Matcher destination1 = provide(destination, matcherFactory);
				return forkFunction.fork(destination0, destination1);
			}
		});
	}

	public static synchronized Matcher provide(Matcher matcher, MatcherFactory matcherFactory) {
		final ContextProvidingFunction contextProvidingFunction = COMPLETION_FUNCTIONS_BY_MATCHER.get(matcher);
		if (contextProvidingFunction != null) {
			return contextProvidingFunction.provide(matcherFactory);
		}
		return matcher;
	}
}
