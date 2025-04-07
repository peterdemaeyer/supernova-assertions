package internal.su.pernova.assertions.matchers;

import su.pernova.assertions.Description;

public abstract class ByteMatcher extends PromptDescriptiveMatcher {

	protected final byte expected;

	public ByteMatcher(CharSequence description, boolean prompt, byte expected) {
		super(description, prompt);
		this.expected = expected;
	}

	@Override
	public Description describe(Description description) {
		return super.describe(description).appendExpected(expected);
	}
}
