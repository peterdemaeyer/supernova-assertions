package internal.su.pernova.assertions.matchers;

import su.pernova.assertions.Context;

public class ContextualBooleanMatcher extends BooleanMatcher {

	public ContextualBooleanMatcher(boolean expected, boolean prompt) {
		super("", prompt, expected);
	}

	@Override
	public boolean match(Object actual) {
		return Context.get(this).matcherFactory().create(expected).match(actual);
	}

	public static ContextualBooleanMatcher[] arrayOf(boolean... expected) {
		ContextualBooleanMatcher[] matchers = new ContextualBooleanMatcher[expected.length];
		int i = 0;
		for (boolean value : expected) {
			matchers[i++] = new ContextualBooleanMatcher(value, false);
		}
		return matchers;
	}
}
