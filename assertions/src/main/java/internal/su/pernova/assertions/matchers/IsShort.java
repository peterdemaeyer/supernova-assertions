package internal.su.pernova.assertions.matchers;

public class IsShort extends ShortMatcher {

	public IsShort(CharSequence name, boolean prompt, short expectedValue) {
		super(name, prompt, expectedValue);
	}

	public IsShort(short expectedValue) {
		this("is", true, expectedValue);
	}

	@Override
	public boolean match(Object actualValue) {
		if (actualValue instanceof Number) {
			return expectedValue == ((Number) actualValue).shortValue();
		}
		if (actualValue instanceof Character) {
			return expectedValue == (Character) actualValue;
		}
		return false;
	}
}
