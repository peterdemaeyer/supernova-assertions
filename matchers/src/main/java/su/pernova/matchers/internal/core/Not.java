package su.pernova.matchers.internal.core;

import su.pernova.matchers.Matcher;

public class Not<T> extends DelegatingMatcher<T> {

	public Not(Matcher<T> delegate) {
		super("not ", delegate);
	}

	@Override
	public boolean matches(Object actual) {
		return !super.matches(actual);
	}
}
