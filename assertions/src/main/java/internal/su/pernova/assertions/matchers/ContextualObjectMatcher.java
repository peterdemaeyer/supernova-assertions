package internal.su.pernova.assertions.matchers;

import static java.util.Arrays.stream;

import su.pernova.assertions.Context;

public class ContextualObjectMatcher extends ObjectMatcher {

	public ContextualObjectMatcher(Object expected, boolean prompt) {
		super("", prompt, expected);
	}

	@Override
	public boolean match(Object actual) {
		return Context.get(this).matcherFactory().create(expected).match(actual);
	}

	public static ContextualObjectMatcher[] arrayOf(Object... expected) {
		return stream(expected).map(value -> new ContextualObjectMatcher(value, false)).toArray(ContextualObjectMatcher[]::new);
	}
}
