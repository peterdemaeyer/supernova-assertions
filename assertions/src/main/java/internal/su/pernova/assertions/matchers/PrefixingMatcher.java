package internal.su.pernova.assertions.matchers;

import static java.util.Objects.requireNonNull;

import su.pernova.assertions.Description;
import su.pernova.assertions.Matcher;

public abstract class PrefixingMatcher extends DescriptiveMatcher {

	protected final Matcher delegate;

	public PrefixingMatcher(CharSequence description, Matcher delegate) {
		super(description);
		this.delegate = requireNonNull(delegate, "delegate is null");
	}

	@Override
	public boolean match(Object actual) {
		return delegate.match(actual);
	}

	@Override
	public Description describe(Description description) {
		return delegate.describe(super.describe(description));
	}
}
