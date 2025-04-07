package internal.su.pernova.assertions.matchers;

import su.pernova.assertions.Description;

public abstract class ShortMatcher extends PromptDescriptiveMatcher {

	protected final short expected;

	public ShortMatcher(CharSequence description, boolean prompt, short expected) {
		super(description, prompt);
		this.expected = expected;
	}

	@Override
	public Description describe(Description description) {
		return super.describe(description).appendExpected(expected);
	}
}
