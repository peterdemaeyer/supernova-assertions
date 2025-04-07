package internal.su.pernova.assertions.matchers;

import static java.util.Arrays.stream;

import su.pernova.assertions.Context;

public class ContextualDoubleMatcher extends DoubleMatcher {

	public ContextualDoubleMatcher(double expected, boolean prompt) {
		super("", prompt, expected);
	}

	@Override
	public boolean match(Object actual) {
		return Context.get(this).matcherFactory().create(expected).match(actual);
	}

	public static ContextualDoubleMatcher[] arrayOf(double... expected) {
		return stream(expected).mapToObj(value -> new ContextualDoubleMatcher(value, false)).toArray(ContextualDoubleMatcher[]::new);
	}
}
