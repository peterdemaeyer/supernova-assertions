package internal.su.pernova.assertions.matchers;

public class IsObject extends ObjectMatcher {

	public IsObject(CharSequence description, boolean prompt, Object expected) {
		super(description, prompt, expected);
	}

	public IsObject(Object expected) {
		this("is", true, expected);
	}

	@Override
	public boolean match(Object actual) {
		return actual == expected;
	}
}
