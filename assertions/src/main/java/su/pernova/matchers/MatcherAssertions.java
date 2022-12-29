package su.pernova.matchers;

public class MatcherAssertions {

	public static final Object NULL = null;

	private MatcherAssertions() {
		// Prevent instantiation.
	}

	public static Assertion assertThat(Object subject) {
		return new Assertion("value ", subject);
	}

	public static ExecutableAssertion assertThat(Executable executable) {
		return new ExecutableAssertion(executable);
	}

	public static <T> void assertThat(T actual, Matcher<? super T> matcher) {
		assertThat(actual, matcher, "");
	}

	public static <T> void assertThat(T actual, Matcher<? super T> matcher, String reason) {
		if (!matcher.matches(actual)) {
			Description description = new StringDescription();
			description.appendText(reason)
					.appendText(System.lineSeparator())
					.appendText("Expected: value ")
					.appendDescriptionOf(matcher)
					.appendText(",")
					.appendText(System.lineSeparator())
					.appendText("     but: value ")
					.appendText(".");
			matcher.describeMismatch(actual, description);
			throw new AssertionError(description.toString());
		}
	}

	public static void assertThat(boolean assertion, String reason) {
		if (!assertion) {
			throw new AssertionError(reason);
		}
	}
}
