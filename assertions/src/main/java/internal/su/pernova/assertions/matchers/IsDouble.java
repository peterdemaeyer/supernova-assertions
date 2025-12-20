package internal.su.pernova.assertions.matchers;

public class IsDouble extends DoubleMatcher {

	public IsDouble(CharSequence name, boolean prompt, double expectedValue) {
		super(name, prompt, expectedValue);
	}

	public IsDouble(double expectedValue) {
		this("is", true, expectedValue);
	}

	@Override
	public boolean match(Object actualValue) {
		if (actualValue instanceof Number) {
			return expectedValue == ((Number) actualValue).doubleValue();
		} else if (actualValue instanceof Character) {
			return expectedValue == (Character) actualValue;
		}
		return false;
	}
}
