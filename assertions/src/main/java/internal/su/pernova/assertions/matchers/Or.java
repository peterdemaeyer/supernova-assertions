package internal.su.pernova.assertions.matchers;

import su.pernova.assertions.Matcher;

public class Or extends BiMatcher {

	public Or(Matcher left, Matcher right) {
		this(null, left, right);
	}

	public Or(CharSequence description, Matcher left, Matcher right) {
		super(description, left, right);
	}

	@Override
	public boolean match(Object actual) {
		return left.match(actual) || right.match(actual);
	}
}
