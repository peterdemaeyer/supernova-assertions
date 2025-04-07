package internal.su.pernova.assertions.matchers;

import su.pernova.assertions.Context;

public class IsLong extends LongMatcher {

	public IsLong(CharSequence description, boolean prompt, long expected) {
		super(description, prompt, expected);
		Context.set(this).matcherFactory(Is.MATCHER_FACTORY);
	}

	public IsLong(long expected) {
		this("is", true, expected);
	}

	@Override
	public boolean match(Object actual) {
		if (actual instanceof Number) {
			return expected == ((Number) actual).longValue();
		}
		if (actual instanceof Character) {
			return expected == (Character) actual;
		}
		return false;
	}
}
