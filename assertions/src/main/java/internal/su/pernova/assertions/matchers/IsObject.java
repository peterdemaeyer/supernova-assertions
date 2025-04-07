package internal.su.pernova.assertions.matchers;

import su.pernova.assertions.Context;

public class IsObject extends ObjectMatcher {

	public IsObject(CharSequence description, boolean prompt, Object expected) {
		super(description, prompt, expected);
		Context.set(this).matcherFactory(Is.MATCHER_FACTORY);
	}

	public IsObject(Object expected) {
		this("is", true, expected);
	}

	@Override
	public boolean match(Object actual) {
		return actual == expected;
	}
}
