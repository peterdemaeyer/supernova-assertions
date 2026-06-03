package internal.su.pernova.assertions.matchers;

import su.pernova.assertions.Context;
import su.pernova.assertions.Matcher;
import su.pernova.assertions.MatcherFactory;

public class IsDouble extends DoubleMatcher {

	private final MatcherFactory matcherFactory;

	public IsDouble(CharSequence name, boolean prompt, double expectedValue) {
		super(name, prompt, expectedValue);
		this.matcherFactory = Is.getMatcherFactory(name);
	}

	public IsDouble(double expectedValue) {
		this("is", true, expectedValue);
	}

	@Override
	public boolean match(Object actualValue) {
		if (actualValue instanceof Number) {
			return expectedValue == ((Number) actualValue).doubleValue();
		} else if (actualValue instanceof Character) {
			return expectedValue == (Character) actualValue;
		}
		return false;
	}

	@Override
	public Matcher contextualize(Context context) {
		return context.contextualize(this, matcherFactory, expectedValue);
	}
}
