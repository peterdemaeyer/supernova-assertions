package internal.su.pernova.assertions.matchers;

import su.pernova.assertions.Description;

public class IsDouble extends PromptDescriptiveMatcher {

	private final double expected;

	public IsDouble(CharSequence description, boolean prompt, double expected) {
		super(description, prompt);
		this.expected = expected;
	}

	public IsDouble(double expected) {
		this("is", true, expected);
	}

	@Override
	public boolean match(Object actual) {
		if (actual instanceof Number) {
			return expected == ((Number) actual).doubleValue();
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
