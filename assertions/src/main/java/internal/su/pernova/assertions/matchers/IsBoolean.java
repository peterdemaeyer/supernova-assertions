package internal.su.pernova.assertions.matchers;

import su.pernova.assertions.Description;

public class IsBoolean extends PromptDescriptiveMatcher {

	private final boolean expected;

	public IsBoolean(CharSequence description, boolean prompt, boolean expected) {
		super(description, prompt);
		this.expected = expected;
	}

	public IsBoolean(boolean expected) {
		this("is", true, expected);
	}

	@Override
	public boolean match(Object actual) {
		if (actual instanceof Boolean) {
			return expected == ((Boolean) actual).booleanValue();
		}
		return false;
	}

	@Override
	public Description describe(Description description) {
		return super.describe(description).appendExpected(expected);
	}
}
