package internal.su.pernova.assertions.matchers;

import su.pernova.assertions.Description;

public abstract class BooleanMatcher extends PromptDescriptiveMatcher {

	protected final boolean expected;

	public BooleanMatcher(CharSequence description, boolean prompt, boolean expected) {
		super(description, prompt);
		this.expected = expected;
	}

	@Override
	public Description describe(Description description) {
		return super.describe(description).appendExpected(expected);
	}
}
