package internal.su.pernova.assertions.matchers;

public class IsByte extends ByteMatcher {

	public IsByte(CharSequence name, boolean prompt, byte expectedValue) {
		super(name, prompt, expectedValue);
	}

	public IsByte(byte expectedValue) {
		this("is", true, expectedValue);
	}

	@Override
	public boolean match(Object actualValue) {
		if (actualValue instanceof Number) {
			return expectedValue == ((Number) actualValue).byteValue();
		}
		if (actualValue instanceof Character) {
			return expectedValue == (Character) actualValue;
		}
		return false;
	}
}
