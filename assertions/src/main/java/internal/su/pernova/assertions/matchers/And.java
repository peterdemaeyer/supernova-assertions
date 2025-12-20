package internal.su.pernova.assertions.matchers;

import su.pernova.assertions.Matcher;

public class And extends BiMatcher {

	public And(Matcher left, Matcher right) {
		super(left, right);
	}

	@Override
	public boolean match(Object actualValue) {
		return left.match(actualValue) && right.match(actualValue);
	}
}
