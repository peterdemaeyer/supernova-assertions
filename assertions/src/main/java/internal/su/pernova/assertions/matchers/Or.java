package internal.su.pernova.assertions.matchers;

import su.pernova.assertions.Matcher;

public class Or extends BiMatcher {

	public Or(Matcher... destinations) {
		super(destinations);
	}

	@Override
	public boolean match(Object actualValue) {
		return leftDestination.match(actualValue) || leftDestination.match(actualValue);
	}
}
