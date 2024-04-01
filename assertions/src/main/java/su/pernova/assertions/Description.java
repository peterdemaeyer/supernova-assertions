package su.pernova.assertions;

/**
 * This builder interface defines a description where text and arguments can be appended to when building an assertion
 * failure message.
 * Users typically won't implement nor call this interface themselves.
 * The default implementation {@link AppendableDescription} is instantiated and called by the framework.
 * Implementations do not need to be thread-safe.
 *
 * @since 1.0.0
 */
public interface Description {

	/**
	 * Appends a given text to this description.
	 *
	 * @param text a text to append to this description, not {@code null}.
	 * @return this description, not {@code null}.
	 * @since 1.0.0
	 */
	Description appendText(CharSequence text);

	/**
	 * Appends a prompt to this description, which is ':' by default.
	 * A prompt is used before an argument in an assertion failure expression such as:
	 * "expected that subject is equal to: 1 but was: 2"
	 *
	 * @return this description, not {@code null}.
	 * @since 2.0.0
	 */
	default Description appendPrompt() {
		return appendText(":");
	}

	/**
	 * Appends a given argument to this description.
	 *
	 * @param argument an argument to append to this description, maybe {@code null}.
	 * @return this description, not {@code null}.
	 * @since 1.0.0
	 */
	Description appendArgument(Object argument);

	/**
	 * Appends an expected value to this description.
	 *
	 * @param expected an expected value to append to this description, possibly {@code null}.
	 * @return this description.
	 * @since 2.0.0
	 */
	default Description appendExpected(Object expected) {
		return appendArgument(expected);
	}

	/**
	 * Appends an actual value to this description.
	 *
	 * @param actual an actual value to append to this description, possibly {@code null}.
	 * @return this description.
	 * @since 2.0.0
	 */
	default Description appendActual(Object actual) {
		return appendArgument(actual);
	}

	/**
	 * Gets the expected value(s) appended to this description so far.
	 * The purpose of the expected value is for it to be used in the {@link org.opentest4j.AssertionFailedError} when
	 * the optional OpenTest4J is on the class path, for example in JUnit 5 assertion failures.
	 * In other cases, where a plain {@link AssertionError} is used, the expected value is irrelevant.
	 *
	 * @return the expected value(s), possibly {@code null}, or possibly a list of multiple values.
	 * @since 2.0.0
	 */
	Object getExpected();

	/**
	 * Gets the actual value(s) appended to this description so far.
	 * The purpose of the actual value is for it to be used in the {@link org.opentest4j.AssertionFailedError} when
	 * the optional OpenTest4J is on the class path, for example in JUnit 5 assertion failures.
	 * In other cases, where a plain {@link AssertionError} is used, the actual value is irrelevant.
	 *
	 * @return the actual values(s), possibly {@code null}, or possibly a list of multiple values.
	 * @since 2.0.0
	 */
	Object getActual();
}
