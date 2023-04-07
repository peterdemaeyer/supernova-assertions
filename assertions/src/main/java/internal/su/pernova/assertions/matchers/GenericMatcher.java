package internal.su.pernova.assertions.matchers;

import su.pernova.assertions.Description;

public abstract class GenericMatcher<E> extends DescriptiveMatcher {

	protected final E expected;

	protected GenericMatcher(CharSequence description, E expected) {
		super(description);
		this.expected = expected;
	}

	@Override
	public Description describe(Description description) {
		return super.describe(description).appendArgument(expected);
	}
}
