package internal.su.pernova.assertions.matchers;

public class IsChar extends CharMatcher {

	public IsChar(CharSequence name, boolean prompt, char expectedValue) {
		super(name, prompt, expectedValue);
	}

	public IsChar(char expectedValue) {
		this("is", true, expectedValue);
	}

	@Override
	public boolean match(Object actualValue) {
		if (actualValue instanceof Number) {
			return expectedValue == ((Number) actualValue).intValue();
		}
		if (actualValue instanceof Character) {
			return expectedValue == (Character) actualValue;
		}
		return false;
	}
}
