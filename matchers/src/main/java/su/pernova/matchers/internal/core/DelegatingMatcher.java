package su.pernova.matchers.internal.core;

import static java.util.Objects.requireNonNull;

import su.pernova.matchers.Description;
import su.pernova.matchers.Matcher;

public class DelegatingMatcher<T> extends Matcher<T> {

	private final CharSequence relation;

	protected final Matcher<T> delegate;

	public DelegatingMatcher(CharSequence relation, Matcher<T> delegate) {
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
