package internal.su.pernova.assertions.matchers;

public class IsLong extends LongMatcher {

	public IsLong(CharSequence name, boolean prompt, long expectedValue) {
		super(name, prompt, expectedValue);
	}

	public IsLong(long expectedValue) {
		this("is", true, expectedValue);
	}

	@Override
	public boolean match(Object actualValue) {
		if (actualValue instanceof Number) {
			return expectedValue == ((Number) actualValue).longValue();
		}
		if (actualValue instanceof Character) {
			return expectedValue == (Character) actualValue;
		}
		return false;
	}
}
