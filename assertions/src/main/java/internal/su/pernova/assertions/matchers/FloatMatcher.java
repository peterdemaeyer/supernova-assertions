package internal.su.pernova.assertions.matchers;

import su.pernova.assertions.Description;

public abstract class FloatMatcher extends PromptDescriptiveMatcher {

	protected final float expected;

	public FloatMatcher(CharSequence description, boolean prompt, float expected) {
		super(description, prompt);
		this.expected = expected;
	}

	@Override
	public Description describe(Description description) {
		return super.describe(description).appendExpected(expected);
	}
}
