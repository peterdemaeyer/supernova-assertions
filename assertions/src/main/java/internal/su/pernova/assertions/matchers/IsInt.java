package internal.su.pernova.assertions.matchers;

import su.pernova.assertions.Context;

public class IsInt extends IntMatcher {

	public IsInt(CharSequence description, boolean prompt, int expected) {
		super(description, prompt, expected);
		Context.set(this).matcherFactory(Is.MATCHER_FACTORY);
	}

	public IsInt(int expected) {
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
