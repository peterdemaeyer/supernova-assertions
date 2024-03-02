package internal.su.pernova.assertions.matchers;

import static java.util.Objects.requireNonNull;

import su.pernova.assertions.Description;
import su.pernova.assertions.Matcher;

public class IsMatcher extends DecoratingMatcher {

	public IsMatcher(CharSequence description, Matcher delegate) {
		super(requireNonNull(description, "description is null"), delegate);
	}

	@Override
	public Description describe(Description description) {
		return delegate.describe(description.appendText(" " + customDescription));
	}
}
