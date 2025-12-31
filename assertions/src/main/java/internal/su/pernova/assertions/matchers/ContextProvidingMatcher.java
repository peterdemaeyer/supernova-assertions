package internal.su.pernova.assertions.matchers;

import internal.su.pernova.assertions.ContextProvidingDescribable;
import su.pernova.assertions.Matcher;

public class ContextProvidingMatcher extends ContextProvidingDescribable<Matcher> implements Matcher {

	public ContextProvidingMatcher(CharSequence name, Matcher destination) {
		super(name, destination);
	}

	@Override
	public boolean match(Object actualValue) {
		return destination.match(actualValue);
	}
}
