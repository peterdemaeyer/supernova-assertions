package internal.su.pernova.assertions.matchers;

import su.pernova.assertions.Context;

public class IsDouble extends DoubleMatcher {

	public IsDouble(CharSequence description, boolean prompt, double expected) {
		super(description, prompt, expected);
		Context.set(this).matcherFactory(Is.MATCHER_FACTORY);
	}

	public IsDouble(double expected) {
		this("is", true, expected);
	}

	@Override
	public boolean match(Object actual) {
		if (actual instanceof Number) {
			return expected == ((Number) actual).doubleValue();
		} else if (actual instanceof Character) {
			return expected == (Character) actual;
		}
		return false;
	}
}
