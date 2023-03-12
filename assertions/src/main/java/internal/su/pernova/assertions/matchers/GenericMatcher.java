package internal.su.pernova.assertions.matchers;

import su.pernova.assertions.Description;
import su.pernova.assertions.Matcher;

public abstract class GenericMatcher<E> implements Matcher {

	protected final E expected;

	protected GenericMatcher(E expected) {
		this.expected = expected;
	}

	@Override
	public Description describe(Description description) {
		return Matcher.super.describe(description).appendArgument(expected);
	}
}
