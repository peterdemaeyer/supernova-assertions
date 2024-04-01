package internal.su.pernova.assertions.matchers;

import su.pernova.assertions.Description;

public class IsByte extends PromptDescriptiveMatcher {

	private final byte expected;

	private IsByte(CharSequence description, boolean prompt, byte expected) {
		super(description, prompt);
		this.expected = expected;
	}

	public IsByte(byte expected) {
		this("is", true, expected);
	}

	@Override
	public boolean match(Object actual) {
		if (actual instanceof Number) {
			return expected == ((Number) actual).byteValue();
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
