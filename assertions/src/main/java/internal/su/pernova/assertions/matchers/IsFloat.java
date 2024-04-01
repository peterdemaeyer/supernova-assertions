package internal.su.pernova.assertions.matchers;

import su.pernova.assertions.Description;

public class IsFloat extends PromptDescriptiveMatcher {

	private final float expected;

	public IsFloat(CharSequence description, boolean prompt, float expected) {
		super(description, prompt);
		this.expected = expected;
	}

	public IsFloat(float expected) {
		this("is", true, expected);
	}

	@Override
	public boolean match(Object actual) {
		if (actual instanceof Number) {
			return expected == ((Number) actual).floatValue();
		} else if (actual instanceof Character) {
			return expected == (Character) actual;
		}
		return false;
	}

	@Override
	public Description describe(Description description) {
		return super.describe(description).appendExpected(expected);
	}
}
