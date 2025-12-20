package internal.su.pernova.assertions.matchers;

public class IsObject extends ObjectMatcher {

	public IsObject(CharSequence name, boolean prompt, Object expectedValue) {
		super(name, prompt, expectedValue);
	}

	public IsObject(CharSequence name, Object expectedValue) {
		this(name, true, expectedValue);
	}

	public IsObject(Object expectedValue) {
		this("is", expectedValue);
	}

	@Override
	public boolean match(Object actualValue) {
		return actualValue == expectedValue;
	}
}
