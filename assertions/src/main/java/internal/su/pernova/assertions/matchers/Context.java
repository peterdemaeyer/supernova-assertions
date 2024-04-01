package internal.su.pernova.assertions.matchers;

import su.pernova.assertions.Matcher;

public interface Context {

	Matcher apply(Object expected);

	default Matcher apply(double expected) {
		return apply((Object) expected);
	}
}
