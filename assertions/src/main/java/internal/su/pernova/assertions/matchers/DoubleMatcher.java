package internal.su.pernova.assertions.matchers;

import su.pernova.assertions.Description;

public abstract class DoubleMatcher extends PromptDescriptiveMatcher {

	protected final double expected;

	public DoubleMatcher(CharSequence description, boolean prompt, double expected) {
		super(description, prompt);
		this.expected = expected;
	}

	@Override
	public Description describe(Description description) {
		return super.describe(description).appendExpected(expected);
	}
}
