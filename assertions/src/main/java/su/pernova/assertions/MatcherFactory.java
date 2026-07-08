package su.pernova.assertions;

/**
 * This interface defines a matcher factory for value matchers and forwarding matchers.
 * A factory for value matchers must implement at least {@link #create(Object)}.
 * Primitive values are auto-boxed by default, and further delegated to {@link #create(Object)}.
 * If primitive values need to be treated differently, the implementation must override the corresponding {@link #create} method(s).
 * A factory for forwarding matchers must implement {@link #create(Matcher)}.
 *
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

	default Matcher create(Matcher matcher) {
		return matcher;
	}
}
