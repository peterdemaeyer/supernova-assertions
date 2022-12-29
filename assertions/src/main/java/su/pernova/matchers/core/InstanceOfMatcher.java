package su.pernova.matchers.core;

import static java.util.Objects.requireNonNull;

import su.pernova.matchers.Description;
import su.pernova.matchers.Matcher;

class InstanceOfMatcher<T> extends Matcher<T> {

	private final CharSequence relation;

	private final Class<T> expectedClass;

	InstanceOfMatcher(CharSequence relation, Class<T> expectedClass) {
		this.relation = requireNonNull(relation, "relation is null");
		this.expectedClass = requireNonNull(expectedClass, "expected class is null");
	}

	@Override
	public boolean matches(Object actual) {
		return expectedClass.isInstance(actual);
	}

	@Override
	public void describeTo(Description description) {
		description.appendText(relation).appendText(expectedClass.getName());
	}
}
