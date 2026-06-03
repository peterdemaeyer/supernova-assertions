package internal.su.pernova.assertions.matchers;

import su.pernova.assertions.Context;
import su.pernova.assertions.Matcher;
import su.pernova.assertions.MatcherFactory;

public class IsChar extends CharMatcher {

	private final MatcherFactory matcherFactory;

	public IsChar(CharSequence name, boolean prompt, char expectedValue) {
		super(name, prompt, expectedValue);
		matcherFactory = Is.getMatcherFactory(name);
	}

	public IsChar(char expectedValue) {
		this("is", true, expectedValue);
	}

	@Override
	public boolean match(Object actualValue) {
		if (actualValue instanceof Number) {
			return expectedValue == ((Number) actualValue).intValue();
		}
		if (actualValue instanceof Character) {
			return expectedValue == (Character) actualValue;
		}
		return false;
	}

	@Override
	public Matcher contextualize(Context context) {
		return context.contextualize(this, matcherFactory, expectedValue);
	}
}
