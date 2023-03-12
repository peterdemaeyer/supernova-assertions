package internal.su.pernova.assertions.matchers;

import su.pernova.assertions.Description;

public class SameAs extends GenericMatcher<Object> {

	private final CharSequence customDescription;

	public SameAs(Object expected, CharSequence customDescription) {
		super(expected);
		this.customDescription = customDescription;
	}

	@Override
	public boolean match(Object actual) {
		return actual == expected;
	}

	@Override
	public Description describe(Description description) {
		return (customDescription != null) ? description.appendText(customDescription).appendArgument(expected) : super.describe(description);
	}
}
