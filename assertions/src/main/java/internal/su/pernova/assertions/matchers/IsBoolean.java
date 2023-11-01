package internal.su.pernova.assertions.matchers;

import su.pernova.assertions.Description;

public class IsBoolean extends DescriptiveMatcher {

	private final boolean expected;

	public IsBoolean(boolean expected) {
		super("is");
		this.expected = expected;
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
