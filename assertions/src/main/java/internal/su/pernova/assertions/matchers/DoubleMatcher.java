package internal.su.pernova.assertions.matchers;

import su.pernova.assertions.Context;
import su.pernova.assertions.Description;
import su.pernova.assertions.Matcher;

public class DoubleMatcher extends ExpectedValueMatcher {

	protected final double expectedValue;

	public DoubleMatcher(CharSequence name, boolean prompt, double expectedValue) {
		super(name, prompt);
		this.expectedValue = expectedValue;
	}

	public DoubleMatcher(double expectedValue) {
		this("", true, expectedValue);
	}

	@Override
	public Description describe(Description description) {
		return super.describe(description).appendExpectedValue(expectedValue);
	}

	@Override
	public String toString() {
		return super.toString() + "(" + expectedValue + ")";
	}

	@Override
	public Matcher contextualize(Context context) {
		return context.imply(this, expectedValue);
	}
}
