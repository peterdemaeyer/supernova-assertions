package internal.su.pernova.assertions.matchers;

public class IsBoolean extends BooleanMatcher {

	public IsBoolean(CharSequence name, boolean prompt, boolean expectedValue) {
		super(name, prompt, expectedValue);
	}

	public IsBoolean(boolean expectedValue) {
		this("is", true, expectedValue);
	}

	@Override
	public boolean match(Object actualValue) {
		if (actualValue instanceof Boolean) {
			return expectedValue == (Boolean) actualValue;
		}
		return false;
	}
}
