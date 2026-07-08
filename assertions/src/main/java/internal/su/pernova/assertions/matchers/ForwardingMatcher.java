package internal.su.pernova.assertions.matchers;

import static java.util.Objects.requireNonNull;

import static internal.su.pernova.assertions.DescribableUtils.getDefaultName;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

import internal.su.pernova.assertions.ForwardingDescribable;
import su.pernova.assertions.Context;
import su.pernova.assertions.Matcher;
import su.pernova.assertions.MatcherFactory;

/**
 * A forwarding matcher that forwards a matcher factory to its destination(s).
 * This matcher is contextful: it has context of its own, the matcher factory, and forwards it to its destinations,
 * providing context for them.
 */
public class ForwardingMatcher extends ForwardingDescribable<Matcher> implements Matcher {

	private static final ReferenceQueue<MatcherFactory> REFERENCE_QUEUE = new ReferenceQueue<>();

	private static final Map<CharSequence, NamedWeakReference> MATCHER_FACTORIES_BY_NAME = new HashMap<>();

	protected final MatcherFactory matcherFactory;

	public ForwardingMatcher(CharSequence name, Matcher destination, MatcherFactory matcherFactory) {
		super(name, destination);
		this.matcherFactory = requireNonNull(matcherFactory, "matcher factory is null");
	}

	public ForwardingMatcher(CharSequence name, Matcher destination) {
		this(name, destination, getMatcherFactory(name));
	}

	public ForwardingMatcher(Class<?> matcherClass, Matcher destination, MatcherFactory matcherFactory) {
		this(getDefaultName(requireNonNull(matcherClass, "matcher class is null")), destination, matcherFactory);
	}

	@Override
	public boolean match(Object actualValue) {
		return destination.match(actualValue);
	}

	@Override
	public Matcher contextualize(Context context) {
		return context.forward(this, matcherFactory, destination);
	}

	public synchronized static MatcherFactory getMatcherFactory(CharSequence name) {
		for (NamedWeakReference reference = (NamedWeakReference) REFERENCE_QUEUE.poll(); reference != null; reference = (NamedWeakReference) REFERENCE_QUEUE.poll()) {
			MATCHER_FACTORIES_BY_NAME.remove(reference.name);
		}
		return MATCHER_FACTORIES_BY_NAME.computeIfAbsent(name, ignored -> new NamedWeakReference(name, new MatcherFactory() {

			@Override
			public Matcher create(Object expectedValue) {
				throw new UnsupportedOperationException();
			}

			public Matcher create(Matcher matcher) {
				return new ForwardingMatcher(name, matcher, this);
			}
		}, REFERENCE_QUEUE)).get();
	}

	static class NamedWeakReference extends WeakReference<MatcherFactory> {

		final CharSequence name;

		public NamedWeakReference(CharSequence name, MatcherFactory referent, ReferenceQueue<MatcherFactory> q) {
			super(referent, q);
			this.name = name;
		}
	}
}
