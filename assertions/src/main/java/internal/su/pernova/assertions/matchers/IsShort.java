package internal.su.pernova.assertions.matchers;

import su.pernova.assertions.Description;

public class IsShort extends PromptDescriptiveMatcher {

	private short expected;

	public IsShort(CharSequence description, boolean prompt, short expected) {
		super(description, prompt);
		this.expected = expected;
	}

	public IsShort(short expected) {
		this("is", true, expected);
	}

	@Override
	public boolean match(Object actual) {
		if (actual instanceof Number) {
			return expected == ((Number) actual).shortValue();
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
