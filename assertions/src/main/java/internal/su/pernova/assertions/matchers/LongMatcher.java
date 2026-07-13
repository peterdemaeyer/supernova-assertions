package internal.su.pernova.assertions.matchers;

import su.pernova.assertions.Context;
import su.pernova.assertions.Description;
import su.pernova.assertions.Matcher;

public class LongMatcher extends ExpectedValueMatcher {

	protected final long expectedValue;

	public LongMatcher(CharSequence name, boolean prompt, long expectedValue) {
		super(name, prompt);
		this.expectedValue = expectedValue;
	}

	public LongMatcher(long expectedValue) {
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
