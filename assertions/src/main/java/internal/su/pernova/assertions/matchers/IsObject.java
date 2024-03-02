package internal.su.pernova.assertions.matchers;

public class IsObject extends ExpectedMatcher {

	public IsObject(CharSequence description, Object expected) {
		super(description, expected);
	}

	public IsObject(Object expected) {
		this("is", expected);
	}

	@Override
	public boolean match(Object actual) {
		return actual == expected;
	}
}
