package internal.su.pernova.assertions.matchers;

import su.pernova.assertions.Description;

public abstract class ExpectedMatcher extends DescriptiveMatcher {

	protected final Object expected;

	protected ExpectedMatcher(CharSequence description, Object expected) {
		super(description);
		this.expected = expected;
	}

	@Override
	public Description describe(Description description) {
		return super.describe(description).appendExpected(expected);
	}
}
