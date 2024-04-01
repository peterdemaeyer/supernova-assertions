package internal.su.pernova.assertions.matchers;

import su.pernova.assertions.Description;

public class IsChar extends PromptDescriptiveMatcher {

	private final char expected;

	public IsChar(CharSequence description, boolean prompt, char expected) {
		super(description, prompt);
		this.expected = expected;
	}

	public IsChar(char expected) {
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
