package internal.su.pernova.assertions.matchers;

import su.pernova.assertions.Context;
import su.pernova.assertions.Matcher;
import su.pernova.assertions.MatcherFactory;

public class IsBoolean extends BooleanMatcher {

	private final MatcherFactory matcherFactory;

	public IsBoolean(CharSequence name, boolean prompt, boolean expectedValue) {
		super(name, prompt, expectedValue);
		matcherFactory = Is.getMatcherFactory(name);
	}

	public IsBoolean(boolean expectedValue) {
		this("is", true, expectedValue);
	}

	@Override
	public boolean match(Object actualValue) {
		if (actualValue instanceof Boolean) {
			return expectedValue == (Boolean) actualValue;
		}
		return false;
	}

	@Override
	public Matcher contextualize(Context context) {
		return context.contextualize(this, matcherFactory, expectedValue);
	}
}
