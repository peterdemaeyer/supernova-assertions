package su.pernova.matchers;

import static java.util.Objects.requireNonNull;

import su.pernova.matchers.core.CoreMatchers;

public class Assertion {

	private final CharSequence subject;

	private final Object actual;

	private int depth = 0;

	private Matcher chainedMatcher;

	protected Assertion(CharSequence subject, Object actual) {
		this.subject = requireNonNull(subject, "subject is null");
		this.actual = actual;
	}

	public Assertion is(Object expected) {
		return stack(CoreMatchers.is(expected));
	}

	public Assertion is(Matcher matcher) {
		return stack(CoreMatchers.is(matcher));
	}

	private Assertion stack(Matcher matcher) {
		try {
			chainedMatcher = matcher;
			depth++;
			return this;
		} finally {
			if (--depth == 0) {
				if (!matcher.matches(actual)) {
					Description description = new StringDescription();
					description.appendText(System.lineSeparator())
							.appendText("Expected that value ")
							.appendDescriptionOf(matcher)
							.appendText(",")
							.appendText(System.lineSeparator())
							.appendText("but actual value was ")
							.appendValue(actual)
							.appendValue(".");
					matcher.describeMismatch(actual, description);
					throw new AssertionError(description.toString());
				}
			}
		}
	}

	@Override
	public String toString() {
		Description description = new StringDescription();
		description.appendText("Asserts that value ");
		description.appendDescriptionOf(chainedMatcher);
		description.appendText(".");
		return description.toString();
	}
}
