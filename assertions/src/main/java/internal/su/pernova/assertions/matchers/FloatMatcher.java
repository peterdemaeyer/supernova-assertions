package internal.su.pernova.assertions.matchers;

import su.pernova.assertions.Description;

public abstract class FloatMatcher extends ExpectedValueMatcher {

	protected final float expectedValue;

	public FloatMatcher(CharSequence name, boolean prompt, float expectedValue) {
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
