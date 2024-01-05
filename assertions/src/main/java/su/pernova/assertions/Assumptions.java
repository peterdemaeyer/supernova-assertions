package su.pernova.assertions;

import static su.pernova.assertions.Assertions.verifyThat;
import static su.pernova.assertions.Subjects.defaultSubject;

import internal.su.pernova.assertions.AssumptionFailureThrower;

/**
 * A factory of matcher-based assumptions for those test frameworks that support them, such as JUnit 4 and JUnit 5.
 * If assumptions are not supported by the test framework, such as JUnit 3, the assumptions become assertions.
 * It is recommended to use static imports for the static factory methods of this class, for readability.
 *
 * @since 2.0.0
 */
public final class Assumptions {

	private Assumptions() {
	}

	/**
	 * Assumes that a given subject matches given matchers using the default identity subject.
	 * If the matchers are empty, then the subject is assumed to be a (boolean) condition.
	 *
	 * @param subject a given subject, which may be {@code null}.
	 * @param matchers given matchers, which may be empty but not {@code null}.
	 * @since 2.0.0
	 */
	public static void assumeThat(Object subject, Matcher... matchers) {
		assumeThat(defaultSubject(subject, matchers), matchers);
	}

	/**
	 * Assumes that a given subject matches given matchers.
	 * If the subject is {@code null}, then the default identity subject for {@code null} is used.
	 * If the matchers are empty, then the subject is assumed to be a (boolean) condition.
	 *
	 * @param subject a given subject, which may be {@code null}.
	 * @param matchers given matchers, which may be empty but not {@code null}.
	 * @since 2.0.0
	 */
	public static void assumeThat(Subject subject, Matcher... matchers) {
		verifyThat(AssumptionFailureThrower.getInstance(), subject, matchers);
	}

	/**
	 * Fails an assumption instantly.
	 *
	 * @since 2.0.0
	 */
	public static void fail() {
		Assertions.fail(AssumptionFailureThrower.getInstance());
	}
}
