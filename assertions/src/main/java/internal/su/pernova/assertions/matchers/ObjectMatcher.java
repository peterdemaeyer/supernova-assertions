package internal.su.pernova.assertions.matchers;

import su.pernova.assertions.Description;

public abstract class ObjectMatcher extends PromptDescriptiveMatcher {

	protected final Object expected;

	protected ObjectMatcher(CharSequence description, boolean prompt, Object expected) {
		super(description, prompt);
		this.expected = expected;
	}

	@Override
	public Description describe(Description description) {
		return super.describe(description).appendExpected(expected);
	}
}
