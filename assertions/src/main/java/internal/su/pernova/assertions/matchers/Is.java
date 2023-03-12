package internal.su.pernova.assertions.matchers;

import static java.util.Objects.requireNonNull;

import su.pernova.assertions.Description;
import su.pernova.assertions.Matcher;

public class Is implements Matcher {

	private final Matcher delegate;

	public Is(Matcher delegate) {
		this.delegate = requireNonNull(delegate, "delegate is null");
	}

	@Override
	public boolean match(Object actual) {
		return delegate.match(actual);
	}

	@Override
	public Description describe(Description description) {
		return delegate.describe(Matcher.super.describe(description));
	}

	@Override
	public Description describeMismatch(Description mismatchDescription) {
		return delegate.describeMismatch(mismatchDescription.appendText(" was"));
	}
}
