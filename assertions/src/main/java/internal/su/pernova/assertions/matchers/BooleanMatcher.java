package internal.su.pernova.assertions.matchers;

import su.pernova.assertions.Context;
import su.pernova.assertions.Description;
import su.pernova.assertions.Matcher;

public class BooleanMatcher extends ExpectedValueMatcher {

	protected final boolean expectedValue;

	public BooleanMatcher(CharSequence name, boolean prompt, boolean expectedValue) {
		super(name, prompt);
		this.expectedValue = expectedValue;
	}

	public BooleanMatcher(boolean expectedValue) {
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
