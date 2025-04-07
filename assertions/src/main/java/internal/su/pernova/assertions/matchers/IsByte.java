package internal.su.pernova.assertions.matchers;

import su.pernova.assertions.Context;

public class IsByte extends ByteMatcher {

	public IsByte(CharSequence description, boolean prompt, byte expected) {
		super(description, prompt, expected);
		Context.set(this).matcherFactory(Is.MATCHER_FACTORY);
	}

	public IsByte(byte expected) {
		this("is", true, expected);
	}

	@Override
	public boolean match(Object actual) {
		if (actual instanceof Number) {
			return expected == ((Number) actual).byteValue();
		}
		if (actual instanceof Character) {
			return expected == (Character) actual;
		}
		return false;
	}
}
