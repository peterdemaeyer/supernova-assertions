package internal.su.pernova.assertions.matchers;

import su.pernova.assertions.Context;

public class ContextualCharMatcher extends CharMatcher {

	public ContextualCharMatcher(char expected, boolean prompt) {
		super("", prompt, expected);
	}

	@Override
	public boolean match(Object actual) {
		return Context.get(this).matcherFactory().create(expected).match(actual);
	}

	public static ContextualCharMatcher[] arrayOf(char... expected) {
		ContextualCharMatcher[] matchers = new ContextualCharMatcher[expected.length];
		int i = 0;
		for (char value : expected) {
			matchers[i++] = new ContextualCharMatcher(value, false);
		}
		return matchers;
	}
}
