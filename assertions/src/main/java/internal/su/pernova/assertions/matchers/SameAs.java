package internal.su.pernova.assertions.matchers;

public class SameAs extends GenericMatcher<Object> {

	public SameAs(CharSequence customText, Object expected) {
		super(customText, expected);
	}

	@Override
	public boolean match(Object actual) {
		return actual == expected;
	}
}
