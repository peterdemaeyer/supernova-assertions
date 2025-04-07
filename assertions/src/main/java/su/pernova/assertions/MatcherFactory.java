package su.pernova.assertions;

public interface MatcherFactory {

	Matcher create(Object expected);

	default Matcher create(double expected) {
		return create((Object) expected);
	}

	default Matcher create(float expected) {
		return create((Object) expected);
	}

	default Matcher create(long expected) {
		return create((Object) expected);
	}

	default Matcher create(int expected) {
		return create((Object) expected);
	}

	default Matcher create(short expected) {
		return create((Object) expected);
	}

	default Matcher create(byte expected) {
		return create((Object) expected);
	}

	default Matcher create(char expected) {
		return create((Object) expected);
	}

	default Matcher create(boolean expected) {
		return create((Object) expected);
	}
}
