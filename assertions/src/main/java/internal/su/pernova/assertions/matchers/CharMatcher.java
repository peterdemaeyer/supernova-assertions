package internal.su.pernova.assertions.matchers;

import su.pernova.assertions.Description;

public abstract class CharMatcher extends ExpectedValueMatcher {

	protected final char expectedValue;

	public CharMatcher(CharSequence name, boolean prompt, char expectedValue) {
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
