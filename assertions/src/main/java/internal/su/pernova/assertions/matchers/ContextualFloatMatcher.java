package internal.su.pernova.assertions.matchers;

import su.pernova.assertions.Context;

public class ContextualFloatMatcher extends FloatMatcher {

	public ContextualFloatMatcher(float expected, boolean prompt) {
		super("", prompt, expected);
	}

	@Override
	public boolean match(Object actual) {
		return Context.get(this).matcherFactory().create(expected).match(actual);
	}

	public static ContextualFloatMatcher[] arrayOf(float... expected) {
		ContextualFloatMatcher[] matchers = new ContextualFloatMatcher[expected.length];
		int i = 0;
		for (float value : expected) {
			matchers[i++] = new ContextualFloatMatcher(value, false);
		}
		return matchers;
	}
}
