package internal.su.pernova.assertions.matchers;

import su.pernova.assertions.Description;

public abstract class ShortMatcher extends ExpectedValueMatcher {

	protected final short expectedValue;

	public ShortMatcher(CharSequence name, boolean prompt, short expectedValue) {
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
