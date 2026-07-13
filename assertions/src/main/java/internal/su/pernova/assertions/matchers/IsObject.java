package internal.su.pernova.assertions.matchers;

import su.pernova.assertions.Context;
import su.pernova.assertions.Description;
import su.pernova.assertions.Matcher;
import su.pernova.assertions.MatcherFactory;

public class IsObject extends ObjectMatcher {

	private final MatcherFactory matcherFactory;

	public IsObject(CharSequence name, boolean prompt, Object expectedValue) {
		super(name, prompt, expectedValue);
		matcherFactory = Is.getMatcherFactory(name);
	}

	public IsObject(CharSequence name, Object expectedValue) {
		this(name, true, expectedValue);
	}

	public IsObject(Object expectedValue) {
		this("is", expectedValue);
	}

	@Override
	public boolean match(Object actualValue) {
		return actualValue == expectedValue;
	}

	@Override
	public Description describe(Description description) {
		return super.describe(description.setAppendIdentity(true));
	}

	@Override
	public Matcher contextualize(Context context) {
		return context.contextualize(this, matcherFactory, expectedValue);
	}
}
