package su.pernova.matchers.core;

import static java.util.Objects.requireNonNull;

import su.pernova.matchers.Description;
import su.pernova.matchers.Matcher;

/**
 * The identity matcher decorates another matcher, preserving its behavior, and prefixing its description with a
 * relation such as "is" or "identical to" to allow writing more intuitive assertions and descriptions.
 */
class IdentityMatcher<T> extends Matcher<T> {

	private final CharSequence relation;

	protected final Matcher<T> delegate;

	IdentityMatcher(CharSequence relation, Matcher<T> delegate) {
		this.relation = requireNonNull(relation, "relation is null");
		this.delegate = requireNonNull(delegate, "delegate is null");
	}

	@Override
	public boolean matches(Object actual) {
		return delegate.matches(actual);
	}

	@Override
	public void describeTo(Description description) {
		delegate.describeTo(description.appendText(relation));
	}
}
