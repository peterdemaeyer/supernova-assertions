package su.pernova.assertions;

/**
 * @since 2.0.0
 */
public interface MatcherFactory {

	Matcher create(Object expectedValue);

	default Matcher create(double expectedValue) {
		return create(Double.valueOf(expectedValue));
	}

	default Matcher create(float expectedValue) {
		return create(Float.valueOf(expectedValue));
	}

	default Matcher create(long expectedValue) {
		return create(Long.valueOf(expectedValue));
	}

	default Matcher create(int expectedValue) {
		return create(Integer.valueOf(expectedValue));
	}

	default Matcher create(short expectedValue) {
		return create(Short.valueOf(expectedValue));
	}

	default Matcher create(byte expectedValue) {
		return create(Byte.valueOf(expectedValue));
	}

	default Matcher create(char expectedValue) {
		return create(Character.valueOf(expectedValue));
	}

	default Matcher create(boolean expectedValue) {
		return create(Boolean.valueOf(expectedValue));
	}
}
