package su.pernova.matchers.core;

import su.pernova.matchers.Matcher;

class NotMatcher<T> extends IdentityMatcher<T> {

	NotMatcher(Matcher<T> delegate) {
		super("not ", delegate);
	}

	@Override
	public boolean matches(Object actual) {
		return !super.matches(actual);
	}
}
