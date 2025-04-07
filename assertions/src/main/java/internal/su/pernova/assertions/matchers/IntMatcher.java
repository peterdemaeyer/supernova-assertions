package internal.su.pernova.assertions.matchers;

import su.pernova.assertions.Description;

public abstract class IntMatcher extends PromptDescriptiveMatcher {

	protected final int expected;

	public IntMatcher(CharSequence description, boolean prompt, int expected) {
		super(description, prompt);
		this.expected = expected;
	}

	@Override
	public Description describe(Description description) {
		return super.describe(description).appendExpected(expected);
	}
}
