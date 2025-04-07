package internal.su.pernova.assertions.matchers;

import su.pernova.assertions.Context;

public class ContextualByteMatcher extends ByteMatcher {

	public ContextualByteMatcher(byte expected, boolean prompt) {
		super("", prompt, expected);
	}

	@Override
	public boolean match(Object actual) {
		return Context.get(this).matcherFactory().create(expected).match(actual);
	}

	public static ContextualByteMatcher[] arrayOf(byte... expected) {
		ContextualByteMatcher[] matchers = new ContextualByteMatcher[expected.length];
		int i = 0;
		for (byte value : expected) {
			matchers[i++] = new ContextualByteMatcher(value, false);
		}
		return matchers;
	}
}
