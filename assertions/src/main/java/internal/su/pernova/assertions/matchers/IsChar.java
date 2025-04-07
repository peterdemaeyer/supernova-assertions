package internal.su.pernova.assertions.matchers;

import su.pernova.assertions.Context;

public class IsChar extends CharMatcher {

	public IsChar(CharSequence description, boolean prompt, char expected) {
		super(description, prompt, expected);
		Context.set(this).matcherFactory(Is.MATCHER_FACTORY);
	}

	public IsChar(char expected) {
		this("is", true, expected);
	}

	@Override
	public boolean match(Object actual) {
		if (actual instanceof Number) {
			return expected == ((Number) actual).intValue();
		}
		if (actual instanceof Character) {
			return expected == (Character) actual;
		}
		return false;
	}
}
