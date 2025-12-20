package internal.su.pernova.assertions.matchers;

public class IsFloat extends FloatMatcher {

	public IsFloat(CharSequence name, boolean prompt, float expectedValue) {
		super(name, prompt, expectedValue);
	}

	public IsFloat(float expectedValue) {
		this("is", true, expectedValue);
	}

	@Override
	public boolean match(Object actualValue) {
		if (actualValue instanceof Number) {
			return expectedValue == ((Number) actualValue).floatValue();
		} else if (actualValue instanceof Character) {
			return expectedValue == (Character) actualValue;
		}
		return false;
	}
}
