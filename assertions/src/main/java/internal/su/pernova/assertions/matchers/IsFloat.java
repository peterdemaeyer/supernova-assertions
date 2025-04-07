package internal.su.pernova.assertions.matchers;

import su.pernova.assertions.Context;

public class IsFloat extends FloatMatcher {

	public IsFloat(CharSequence description, boolean prompt, float expected) {
		super(description, prompt, expected);
		Context.set(this).matcherFactory(Is.MATCHER_FACTORY);
	}

	public IsFloat(float expected) {
		this("is", true, expected);
	}

	@Override
	public boolean match(Object actual) {
		if (actual instanceof Number) {
			return expected == ((Number) actual).floatValue();
		} else if (actual instanceof Character) {
			return expected == (Character) actual;
		}
		return false;
	}
}
