package internal.su.pernova.assertions.matchers;

import su.pernova.assertions.Description;

public class IsShort extends DescriptiveMatcher {

	private short expected;

	public IsShort(short expected) {
		super("is");
		this.expected = expected;
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
		return super.describe(description).appendArgument(expected);
	}
}
