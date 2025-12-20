package internal.su.pernova.assertions.matchers;

import su.pernova.assertions.Description;

public abstract class BooleanMatcher extends PromptedNamedMatcher {

	protected final boolean expectedValue;

	public BooleanMatcher(CharSequence name, boolean prompt, boolean expectedValue) {
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
