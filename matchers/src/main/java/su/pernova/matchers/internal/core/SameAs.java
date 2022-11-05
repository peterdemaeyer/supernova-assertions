package su.pernova.matchers.internal.core;

import static java.util.Objects.requireNonNull;

import su.pernova.matchers.Description;
import su.pernova.matchers.Matcher;

public class SameAs<T> extends Matcher<T> {

	private final CharSequence relation;

	private final T expected;

	public SameAs(CharSequence relation, T expected) {
		this.relation = requireNonNull(relation, "relation is null");
		this.expected = expected;
	}

	@Override
	public boolean matches(Object actual) {
		return actual == expected;
	}

	@Override
	public void describeTo(Description description) {
		description.appendText(relation).appendValue(expected);
	}
}
