package internal.su.pernova.assertions.matchers;

import static java.util.Arrays.stream;

import su.pernova.assertions.Context;

public class ContextualIntMatcher extends IntMatcher {

	public ContextualIntMatcher(int expected, boolean prompt) {
		super("", prompt, expected);
	}

	@Override
	public boolean match(Object actual) {
		return Context.get(this).matcherFactory().create(expected).match(actual);
	}

	public static ContextualIntMatcher[] arrayOf(int... expected) {
		return stream(expected).mapToObj(value -> new ContextualIntMatcher(value, false)).toArray(ContextualIntMatcher[]::new);
	}
}
