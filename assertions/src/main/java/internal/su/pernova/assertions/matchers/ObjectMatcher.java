package internal.su.pernova.assertions.matchers;

import su.pernova.assertions.Context;
import su.pernova.assertions.Description;
import su.pernova.assertions.Matcher;

public class ObjectMatcher extends ExpectedValueMatcher {

	protected final Object expectedValue;

	public ObjectMatcher(CharSequence name, boolean prompt, Object expectedValue) {
		super(name, prompt);
		this.expectedValue = expectedValue;
	}

	public ObjectMatcher(Object expectedValue) {
		this("?Object", true, expectedValue);
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
