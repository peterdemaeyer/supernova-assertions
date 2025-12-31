package su.pernova.assertions;

import static java.util.Objects.requireNonNull;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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
public final class Context implements AutoCloseable {

	private static final Map<Object, Entry<?>> ENTRIES_BY_ORIGIN = new WeakHashMap<>();

	private final Entry<Subject> root;

	/**
	 * Opens a context for a given subject for internal use only.
	 *
	 * @param subject a subject to open a context for.
	 */
	Context(Subject subject) {
		synchronized (ENTRIES_BY_ORIGIN) {
			root = getEntry(subject);
			walkAndSetAll(root.destinations, null, null);
		}
		this.matcherFactory = null;
	}

	private final ImplicitMatcherFactory matcherFactory;

	private Context(ImplicitMatcherFactory matcherFactory) {
		this.matcherFactory = matcherFactory;
		this.root = null;
	}

	public MatcherFactory getFactory() {
		return matcherFactory;
	}

	public Matcher putMatcherFactory(Matcher prototype) {
		MATCHER_FACTORIES_BY_PROTOTYPE.put(prototype, matcherFactory);
		return prototype;
	}

	/**
	 * For internal use. Always call this from within a {@code synchronized (ENTRIES_BY_ORIGIN)} block.
	 */
	@SuppressWarnings("unchecked")
	private static <O> Entry<O> getEntry(O origin) {
		return (Entry<O>) ENTRIES_BY_ORIGIN.get(origin);
	}

	@SuppressWarnings("unchecked")
	private static <O> Entry<O> computeEntryIfAbsent(O origin) {
		return (Entry<O>) ENTRIES_BY_ORIGIN.computeIfAbsent(origin, o -> new Entry<>(origin));
	}

	private void walkAndSetAll(Collection<Entry<?>> entries, Matcher matcher, Descriptor matcherDescriptor) {
		for (Entry<?> entry : entries) {
			if ((entry.origin instanceof Matcher) && (entry.descriptor != null)) {
				matcher = (Matcher) entry.origin;
				matcherDescriptor = entry.descriptor;
			}
			for (Iterator<WeakReference<Listener>> it = entry.listeners.iterator(); it.hasNext(); ) {
				Listener listener = it.next().get();
				if (listener != null) {
					listener.allSet(root.origin, root.descriptor, matcher, matcherDescriptor);
				} else {
					it.remove();
				}
			}
			walkAndSetAll(entry.destinations, matcher, matcherDescriptor);
		}
	}

	/**
	 * Closes this context so that held resources can be garbage collected.
	 */
	@Override
	public void close() {
		synchronized (ENTRIES_BY_ORIGIN) {
			walkAndUnsetAll(root.destinations);
		}
	}

	private void walkAndUnsetAll(Collection<Entry<?>> entries) {
		for (Entry<?> entry : entries) {
			for (Iterator<WeakReference<Listener>> it = entry.listeners.iterator(); it.hasNext(); ) {
				Listener listener = it.next().get();
				if (listener != null) {
					listener.allUnset();
				} else {
					it.remove();
				}
			}
			walkAndUnsetAll(entry.destinations);
		}
	}

	/**
	 * Returns a setter for setting a given matcher's contextual parameters.
	 *
	 * @param matcher a matcher to use as origin, not {@code null}.
	 * @return a setter using the given matcher as origin, not {@code null}.
	 * @since 2.0.0
	 */
	public static <M extends Matcher> Setter<M> set(M matcher) {
		return new Setter<>(matcher);
	}

	/**
	 * Returns a setter for setting a given subject's contextual parameters.
	 *
	 * @param subject a subject to use as origin, not {@code null}.
	 * @return a setter using the given subject as origin, not {@code null}.
	 * @since 2.0.0
	 */
	public static <S extends Subject> Setter<S> set(S subject) {
		return new Setter<>(subject);
	}

	/**
	 * A setter for changing contextual parameters for a given origin.
	 * Changes to the setter are immediate, meaning there is no "commit" or "save" method.
	 * To allow using the setter in a fluent style, the {@link #get()} method gets the origin out of the setter.
	 *
	 * @since 2.0.0
	 */
	public static class Setter<P> {

		private final Entry<P> entry;

		private Setter(P origin) {
			synchronized (ENTRIES_BY_ORIGIN) {
				this.entry = computeEntryIfAbsent(origin);
			}
		}

		public Setter<P> forward(Matcher... destinations) {
			synchronized (ENTRIES_BY_ORIGIN) {
				for (Matcher destination : destinations) {
					entry.destinations.add(computeEntryIfAbsent(destination));
				}
			}
			return this;
		}

		/**
		 * Reroutes all destinations for a given (old) origin via this setter's (new) origin.
		 * This facilitates writing fluent API like this:
		 *
		 * <pre>{@code
		 * Match myMatcher = Context.set(new MyMatcher(destination)).descriptor("forward(destination).get();
		 * }</pre>
		 *
		 * @param oldOrigin an origin to reroute all destinations for, not {@code null}.
		 * @return this setter.
		 */
		public Setter<P> reroute(Matcher oldOrigin) {
			synchronized (ENTRIES_BY_ORIGIN) {
				Entry<?> oldEntry = getEntry(oldOrigin);
				entry.destinations.addAll(oldEntry.destinations);
				oldEntry.destinations.clear();
				oldEntry.destinations.add(entry);
			}
			return this;
		}

		/**
		 * Sets a descriptor for this setter's origin.
		 *
		 * @param descriptor a descriptor, not {@code null}.
		 * @return this setter.
		 */
		public Setter<P> setDescriptor(Descriptor descriptor) {
			entry.descriptor = requireNonNull(descriptor, "descriptor is null");
			return this;
		}

		public Setter<P> addListener(Listener listener) {
			entry.listeners.add(new WeakElement<>(requireNonNull(listener, "listener is null")));
			return this;
		}

		/**
		 * Gets this setter's origin.
		 *
		 * @return this setter's origin, not {@code null}.
		 */
		public P get() {
			return entry.origin;
		}
	}

	private static class WeakElement<T> extends WeakReference<T> {

		WeakElement(T referent) {
			super(referent);
		}

		@Override
		public int hashCode() {
			return Objects.hashCode(get());
		}

		@Override
		public boolean equals(Object other) {
			if (!(other instanceof WeakElement)) {
				return false;
			}
			return Objects.equals(get(), ((WeakElement<?>) other).get());
		}
	}

	/**
	 * @since 2.0.0
	 */
	public interface Listener {

		void allSet(Subject subject, Descriptor subjectDescriptor, Matcher matcher, Descriptor matcherDescriptor);

		void allUnset();
	}

	private static class Entry<O> {

		final O origin;

		Descriptor descriptor;

		final List<Entry<?>> destinations = new ArrayList<>(1);

		final Collection<WeakReference<Listener>> listeners = new ArrayList<>(1);

		Entry(O origin) {
			this.origin = origin;
		}
	}

	private static final Map<Matcher, ImplicitMatcherFactory> MATCHER_FACTORIES_BY_PROTOTYPE = new WeakHashMap<>();

	private static final Map<Matcher, ContextProvidingFunction> COMPLETION_FUNCTIONS_BY_MATCHER = new WeakHashMap<>();

	public static Matcher newIncompleteMatcher(ContextProvidingFunction contextProvidingFunction) {
		final ContextSensitiveMatcher matcher = new ContextSensitiveMatcher();
		COMPLETION_FUNCTIONS_BY_MATCHER.put(matcher, contextProvidingFunction);
//		MATCHER_FACTORIES_BY_PROTOTYPE.put(matcher, new AnonymousMatcherFactory(IncompleteMatcher.MATCHER_FACTORY));
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
//		final ForwardingMatcherFactory forwardingMatcherFactory = new ForwardingMatcherFactory(name, destinationFactory.delegatee(), forwardingFunction);
//		MATCHER_FACTORIES_BY_PROTOTYPE.put(forwardMatcher, new AnonymousMatcherFactory(forwardingMatcherFactory));
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
