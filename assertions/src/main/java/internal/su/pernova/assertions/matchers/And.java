package internal.su.pernova.assertions.matchers;

import su.pernova.assertions.Matcher;

public class And extends BiMatcher {

	public And(Matcher left, Matcher right) {
		super(null, left, right);
	}

	@Override
	public boolean match(Object actual) {
		return left.match(actual) && right.match(actual);
	}
}
