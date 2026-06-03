package internal.su.pernova.assertions.matchers;

import su.pernova.assertions.Context;
import su.pernova.assertions.Matcher;
import su.pernova.assertions.MatcherFactory;

public class IsFloat extends FloatMatcher {

	private final MatcherFactory matcherFactory;

	public IsFloat(CharSequence name, boolean prompt, float expectedValue) {
		super(name, prompt, expectedValue);
		matcherFactory = Is.getMatcherFactory(name);
	}

	public IsFloat(float expectedValue) {
		this("is", true, expectedValue);
	}

	@Override
	public boolean match(Object actualValue) {
		if (actualValue instanceof Number) {
			return expectedValue == ((Number) actualValue).floatValue();
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
