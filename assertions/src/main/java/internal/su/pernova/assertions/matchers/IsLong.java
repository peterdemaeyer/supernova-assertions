package internal.su.pernova.assertions.matchers;

import su.pernova.assertions.Description;

public class IsLong extends PromptDescriptiveMatcher {

	private final long expected;

	public IsLong(CharSequence description, boolean prompt, long expected) {
		super(description, prompt);
		this.expected = expected;
	}

	public IsLong(long expected) {
		this("is", true, expected);
	}

	@Override
	public boolean match(Object actual) {
		if (actual instanceof Number) {
			return expected == ((Number) actual).longValue();
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
