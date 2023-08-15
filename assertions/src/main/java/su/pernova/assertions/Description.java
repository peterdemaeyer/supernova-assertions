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
	 * Appends a given argument to this description.
	 *
	 * @param argument an argument to append to this description, maybe {@code null}.
	 * @return this description, not {@code null}.
	 * @since 1.0.0
	 */
	Description appendArgument(Object argument);
}
