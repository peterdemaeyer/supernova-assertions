package internal.su.pernova.assertions.matchers;

import su.pernova.assertions.Description;

public class IsLong extends DescriptiveMatcher {

	private final long expected;

	public IsLong(long expected) {
		super("is");
		this.expected = expected;
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
		return super.describe(description).appendArgument(expected);
	}
}
