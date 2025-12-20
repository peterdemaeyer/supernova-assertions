package internal.su.pernova.assertions.matchers;

import su.pernova.assertions.Description;

public abstract class IntMatcher extends ExpectedValueMatcher {

	protected final int expectedValue;

	public IntMatcher(CharSequence name, boolean prompt, int expectedValue) {
		super(name, prompt);
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
