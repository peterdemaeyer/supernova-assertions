package internal.su.pernova.assertions.matchers;

import internal.su.pernova.assertions.ForwardingDescribable;
import su.pernova.assertions.Context;
import su.pernova.assertions.Matcher;

/**
 * A contextual (= dependent on context) matcher that forwards the matching to a destination.
 * The object itself is the origin of the forwarding.
 */
public class ImplicitForwardingMatcher extends ForwardingDescribable<Matcher> implements Matcher {

	public ImplicitForwardingMatcher(CharSequence name, Matcher destination) {
		super(name, destination);
	}

	@Override
	public boolean match(Object actualValue) {
		return destination.match(actualValue);
	}

	@Override
	public Matcher contextualize(Context context) {
		context.forward(this, null, destination);
		final Matcher contextualizedDestination = destination.contextualize(context);
		if (contextualizedDestination == destination) {
			// Preserve object identity.
			return this;
		}
		return new ImplicitForwardingMatcher(name, contextualizedDestination);
	}
}
