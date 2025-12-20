package internal.su.pernova.assertions.matchers;

import su.pernova.assertions.Description;

public abstract class DoubleMatcher extends ExpectedValueMatcher {

	protected final double expectedValue;

	public DoubleMatcher(CharSequence description, boolean prompt, double expectedValue) {
		super(description, prompt);
		this.expectedValue = expectedValue;
	}

	@Override
	public Description describe(Description description) {
		return super.describe(description).appendExpectedValue(expectedValue);
	}

	@Override
	public String toString() {
		return super.toString() + "(" + expectedValue + ")";
	}
}
