package su.pernova.assertions;

/**
 * This interface defines the subject of an assertion of the form
 * "assert that &lt;(actual) subject&gt; &lt;holds&gt; &lt;relation with&gt; &lt;(expected) object&gt;".
 * A subject transforms an actual object into the subject of a relation.
 * The default subject "is" the actual object, not transforming it, but for example a "content of" subject transformq an
 * actual object into its content before subjecting it to the relation test.
 *
 * @since 1.0.0
 */
public interface Subject extends Describable {

	/**
	 * Matches this subject against a given matcher.
	 *
	 * @param matcher a matcher to evaluate this subject against, not {@code null}.
	 * @return {@code true} if the matcher matches, {@code false} otherwise.
	 * @since 1.0.0
	 */
	boolean match(Matcher matcher);

	/**
	 * Returns this subject by default, ignoring any context.
	 * Contextualizable implementations must override this method.
	 * Recommendations:
	 * <ul>
	 *     <li>Be prepared for this method to be called multiple times, potentially concurrently.</li>
	 *     <li>Return an immutable object.</li>
	 *     <li>Be stateless.</li>
	 * </ul>
	 *
	 * @param context a context, not {@code null}.
	 * @return this subject.
	 * @since 2.0.0
	 */
	default Subject contextualize(Context context) {
		return this;
	}
}
