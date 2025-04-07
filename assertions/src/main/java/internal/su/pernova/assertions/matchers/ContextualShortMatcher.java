package internal.su.pernova.assertions.matchers;

import su.pernova.assertions.Context;

public class ContextualShortMatcher extends ShortMatcher {

	public ContextualShortMatcher(short expected, boolean prompt) {
		super("", prompt, expected);
	}

	@Override
	public boolean match(Object actual) {
		return Context.get(this).matcherFactory().create(expected).match(actual);
	}

	public static ContextualShortMatcher[] arrayOf(short... expected) {
		ContextualShortMatcher[] matchers = new ContextualShortMatcher[expected.length];
		int i = 0;
		for (short value : expected) {
			matchers[i++] = new ContextualShortMatcher(value, false);
		}
		return matchers;
	}
}
