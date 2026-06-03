package internal.su.pernova.assertions.matchers;

import static java.util.Objects.requireNonNull;

import static internal.su.pernova.assertions.DescribableUtils.getDefaultName;

import su.pernova.assertions.Context;
import su.pernova.assertions.Matcher;
import su.pernova.assertions.MatcherFactory;

/**
 * A forwarding matcher that forwards a matcher factory to its destination(s).
 * This matcher is contextful: it has context of its own, the matcher factory, and forwards it to its destinations,
 * providing context for them.
 */
public class ForwardingMatcher extends ImplicitForwardingMatcher {

	protected final MatcherFactory matcherFactory;

	public ForwardingMatcher(CharSequence name, Matcher destination, MatcherFactory matcherFactory) {
		super(name, destination);
		this.matcherFactory = requireNonNull(matcherFactory, "matcher factory is null");
	}

	public ForwardingMatcher(Class<?> matcherClass, Matcher destination, MatcherFactory matcherFactory) {
		this(getDefaultName(requireNonNull(matcherClass, "matcher class is null")), destination, matcherFactory);
	}

	@Override
	public Matcher contextualize(Context context) {
		return super.contextualize(context.forwardMatcherFactory(this, matcherFactory));
	}
}
