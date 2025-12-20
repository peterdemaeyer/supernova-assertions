package internal.su.pernova.assertions.matchers;

import su.pernova.assertions.Description;

public abstract class LongMatcher extends ExpectedValueMatcher {

	protected final long expectedValue;

	public LongMatcher(CharSequence name, boolean prompt, long expectedValue) {
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
