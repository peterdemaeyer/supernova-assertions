package internal.su.pernova.assertions.matchers;

import su.pernova.assertions.Description;

public abstract class LongMatcher extends PromptDescriptiveMatcher {

	protected final long expected;

	public LongMatcher(CharSequence description, boolean prompt, long expected) {
		super(description, prompt);
		this.expected = expected;
	}

	@Override
	public Description describe(Description description) {
		return super.describe(description).appendExpected(expected);
	}
}
