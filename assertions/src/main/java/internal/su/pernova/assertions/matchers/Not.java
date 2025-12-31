package internal.su.pernova.assertions.matchers;

import su.pernova.assertions.Matcher;

public class Not extends ContextProvidingMatcher {

	public Not(Matcher delegate) {
		super(null, delegate);
	}

	@Override
	public boolean match(Object actualValue) {
		return !destination.match(actualValue);
	}
}
