package internal.su.pernova.assertions.matchers;

import su.pernova.assertions.Context;

public class IsShort extends ShortMatcher {

	public IsShort(CharSequence description, boolean prompt, short expected) {
		super(description, prompt, expected);
		Context.set(this).matcherFactory(Is.MATCHER_FACTORY);
	}

	public IsShort(short expected) {
		this("is", true, expected);
	}

	@Override
	public boolean match(Object actual) {
		if (actual instanceof Number) {
			return expected == ((Number) actual).shortValue();
		}
		if (actual instanceof Character) {
			return expected == (Character) actual;
		}
		return false;
	}
}
