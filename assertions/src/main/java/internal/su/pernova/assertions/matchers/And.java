package internal.su.pernova.assertions.matchers;

import su.pernova.assertions.Matcher;

public class And extends BiMatcher {

	public And(Matcher... destinations) {
		super(destinations);
	}

	@Override
	public boolean match(Object actualValue) {
		return leftDestination.match(actualValue) && rightDestination.match(actualValue);
	}
}
