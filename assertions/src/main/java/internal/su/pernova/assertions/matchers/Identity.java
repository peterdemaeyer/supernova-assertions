package internal.su.pernova.assertions.matchers;

import static java.util.Objects.requireNonNull;

import su.pernova.assertions.Description;
import su.pernova.assertions.Matcher;

public class Identity extends GenericMatcher<Matcher> {

	public Identity(CharSequence description, Matcher delegate) {
		super(requireNonNull(description, "description is null"), requireNonNull(delegate, "delegate is null"));
	}

	@Override
	public boolean match(Object actual) {
		return expected.match(actual);
	}

	@Override
	public Description describe(Description description) {
		return expected.describe(description.appendText(" " + descriptionText));
	}
}
