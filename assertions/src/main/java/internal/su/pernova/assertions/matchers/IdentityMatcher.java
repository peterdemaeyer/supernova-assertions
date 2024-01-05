package internal.su.pernova.assertions.matchers;

import static java.util.Objects.requireNonNull;

import su.pernova.assertions.Description;
import su.pernova.assertions.Matcher;

public class IdentityMatcher extends DescriptiveMatcher {

	protected final Matcher delegate;

	public IdentityMatcher(CharSequence description, Matcher delegate) {
		super(description);
		this.delegate = requireNonNull(delegate, "delegate is null");
	}

	@Override
	public boolean match(Object actual) {
		return delegate.match(actual);
	}

	@Override
	public Description describe(Description description) {
		return delegate.describe(description.appendText(" " + customDescription));
	}
}