package internal.su.pernova.assertions.matchers;

import su.pernova.assertions.Description;

public abstract class ByteMatcher extends ExpectedValueMatcher {

	protected final byte expectedValue;

	public ByteMatcher(CharSequence name, boolean prompt, byte expectedValue) {
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
