package internal.su.pernova.assertions.matchers;

import static java.util.Arrays.stream;

import su.pernova.assertions.Context;

public class ContextualLongMatcher extends LongMatcher {

	public ContextualLongMatcher(long expected, boolean prompt) {
		super("", prompt, expected);
	}

	@Override
	public boolean match(Object actual) {
		return Context.get(this).matcherFactory().create(expected).match(actual);
	}

	public static ContextualLongMatcher[] arrayOf(long... expected) {
		return stream(expected).mapToObj(value -> new ContextualLongMatcher(value, false)).toArray(ContextualLongMatcher[]::new);
	}
}
