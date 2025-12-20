package internal.su.pernova.assertions.matchers;

import internal.su.pernova.assertions.ForwardingDescribable;
import su.pernova.assertions.Matcher;

public class ForwardingMatcher extends ForwardingDescribable<Matcher> implements Matcher {

	public ForwardingMatcher(CharSequence name, Matcher destination) {
		super(name, destination);
	}

	@Override
	public boolean match(Object actualValue) {
		return destination.match(actualValue);
	}
}
