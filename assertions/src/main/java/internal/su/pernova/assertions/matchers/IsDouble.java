package internal.su.pernova.assertions.matchers;

import su.pernova.assertions.Description;

public class IsDouble extends DescriptiveMatcher {

	private final double expected;

	public IsDouble(double expected) {
		super("is");
		this.expected = expected;
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
		return super.describe(description).appendArgument(expected);
	}
}
