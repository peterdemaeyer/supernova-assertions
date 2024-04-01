package internal.su.pernova.assertions.matchers;

import su.pernova.assertions.Matcher;

public interface Context {

	Matcher apply(Object expected);

	default Matcher apply(double expected) {
		return apply((Object) expected);
	}

	default Matcher apply(float expected) {
		return apply((Object) expected);
	}

	default Matcher apply(long expected) {
		return apply((Object) expected);
	}

	default Matcher apply(int expected) {
		return apply((Object) expected);
	}

	default Matcher apply(short expected) {
		return apply((Object) expected);
	}

	default Matcher apply(byte expected) {
		return apply((Object) expected);
	}

	default Matcher apply(char expected) {
		return apply((Object) expected);
	}

	default Matcher apply(boolean expected) {
		return apply((Object) expected);
	}
}
