package su.pernova.assertions;

import static su.pernova.assertions.Subjects.subject;

import internal.su.pernova.assertions.FailureProvider;

/**
 * This utility class provides factory methods for creating {@link Assertion}s.
 *
 * @since 1.0.0
 */
public final class Assertions {

	private Assertions() {
	}

	/**
	 * Creates an assertion for a default subject
	 *
	 * @param actual an actual object, which may be {@code null}.
	 * @return an assertion to be built further, not {@code null}.
	 * @since 1.0.0
	 */
	public static Assertion assertThat(Object actual) {
		return assertThat(subject(actual));
	}

	/**
	 * Creates an assertion for a given subject, which may be {@code null}.
	 *
	 * @param subject a subject, which may be {@code null}.
	 * @return an assertion to be built further, not {@code null}.
	 * @since 1.0.0
	 */
	public static Assertion assertThat(Subject subject) {
		return new Assertion((subject != null) ? subject : subject(null));
	}

	/**
	 * Fails instantly.
	 *
	 * @since 2.0.0
	 */
	public static void fail() {
		throw FailureProvider.getInstance().newAssertionFailure();
	}
}
