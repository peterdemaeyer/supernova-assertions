package internal.su.pernova.assertions.matchers;

import su.pernova.assertions.Matcher;

public class Or extends BiMatcher {

	public Or(Matcher left, Matcher right) {
		super(left, right);
	}

	@Override
	public boolean match(Object actualValue) {
		return left.match(actualValue) || right.match(actualValue);
	}
}
