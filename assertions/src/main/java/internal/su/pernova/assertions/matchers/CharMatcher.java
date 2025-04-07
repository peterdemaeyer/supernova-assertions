package internal.su.pernova.assertions.matchers;

import su.pernova.assertions.Description;

public abstract class CharMatcher extends PromptDescriptiveMatcher {

	protected final char expected;

	public CharMatcher(CharSequence description, boolean prompt, char expected) {
		super(description, prompt);
		this.expected = expected;
	}

	@Override
	public Description describe(Description description) {
		return super.describe(description).appendExpected(expected);
	}
}
