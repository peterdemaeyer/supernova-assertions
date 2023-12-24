package su.pernova.assertions;

import static java.lang.System.lineSeparator;

import static su.pernova.assertions.Matchers.is;
import static su.pernova.assertions.Subjects.condition;
import static su.pernova.assertions.Subjects.subject;

import internal.su.pernova.assertions.FailureProvider;

/**
 * This is the main utility class for writing assertions.
 * Use it with static imports for readability.
 *
 * @since 1.0.0
 */
public final class Assertions {

	private Assertions() {
	}

	/**
	 * Asserts that a given object matches given matchers using the object as default subject.
	 * If the given matchers are empty, the default subject is {@code condition(actual)} and the default matcher is
	 * {@linkplain Matchers#is(boolean) is(true)}.
	 * Else, the default subject is {@linkplain Subjects#subject(Object) subject(actual)}.
	 *
	 * @param actual a given object, which may be {@code null}.
	 * @param matchers given matchers, which may be empty but which must not be {@code null}.
	 * @since 2.0.0
	 */
	public static void assertThat(Object actual, Matcher... matchers) {
		assertThat(defaultSubject(actual, matchers), matchers);
	}

	/**
	 * Asserts that a given subject matches given matchers.
	 * The given subject may be {@code null}, which is equivalent to {@code assertThat((Object) null, matchers);}.
	 * The given matchers may be empty but must not be {@code null}.
	 * If the given matchers are empty, then the assertion succeeds.
	 *
	 * @param subject a given subject, which must not be {@code null}.
	 * @param matchers given matchers, which may be empty but which must not be {@code null}.
	 * @since 2.0.0
	 */
	public static void assertThat(Subject subject, Matcher... matchers) {
		if (subject == null) {
			subject = defaultSubject(null, matchers);
		}
		if (matchers.length == 0) {
			matchers = new Matcher[]{ is(true) };
		}
		for (Matcher matcher: matchers) {
			if (!subject.match(matcher)) {
				final Description description = new AppendableDescription(new StringBuilder())
						.appendText("expected that");
				subject.describe(description);
				matcher.describe(description);
				description.appendText(lineSeparator())
						.appendText("but");
				matcher.describeMismatch(description);
				subject.describeMismatch(description);
				throw FailureProvider.getInstance().newFailure(description.toString(), description.getExpected(), description.getActual());
			}
		}
	}

	/**
	 * Fails instantly.
	 *
	 * @since 2.0.0
	 */
	public static void fail() {
		throw FailureProvider.getInstance().newFailure();
	}

	private static Subject defaultSubject(Object actual, Matcher... matchers) {
		return (matchers.length == 0) ? condition(actual) : subject(actual);
	}
}
