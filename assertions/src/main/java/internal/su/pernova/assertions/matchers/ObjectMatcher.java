package internal.su.pernova.assertions.matchers;

import su.pernova.assertions.Description;

public abstract class ObjectMatcher extends ExpectedValueMatcher {

	protected final Object expectedValue;

	public ObjectMatcher(CharSequence name, boolean prompt, Object expectedValue) {
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
