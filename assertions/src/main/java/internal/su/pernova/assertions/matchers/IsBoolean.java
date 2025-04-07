package internal.su.pernova.assertions.matchers;

import su.pernova.assertions.Context;

public class IsBoolean extends BooleanMatcher {

	public IsBoolean(CharSequence description, boolean prompt, boolean expected) {
		super(description, prompt, expected);
	}

	public IsBoolean(boolean expected) {
		this("is", true, expected);
		Context.set(this).matcherFactory(Is.MATCHER_FACTORY);
	}

	@Override
	public boolean match(Object actual) {
		if (actual instanceof Boolean) {
			return expected == (Boolean) actual;
		}
		return false;
	}
}
