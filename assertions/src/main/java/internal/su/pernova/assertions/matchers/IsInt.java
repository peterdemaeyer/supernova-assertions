package internal.su.pernova.assertions.matchers;

import su.pernova.assertions.Description;

public class IsInt extends PromptDescriptiveMatcher {

	private final int expected;

	public IsInt(CharSequence description, boolean prompt, int expected) {
		super(description, prompt);
		this.expected = expected;
	}

	public IsInt(int expected) {
		this("is", true, expected);
	}

	@Override
	public boolean match(Object actual) {
		if (actual instanceof Number) {
			return expected == ((Number) actual).intValue();
		}
		if (actual instanceof Character) {
			return expected == (Character) actual;
		}
		return false;
	}

	@Override
	public Description describe(Description description) {
		return super.describe(description).appendExpected(expected);
	}
}
