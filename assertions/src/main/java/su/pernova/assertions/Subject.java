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
	 * @param matcher a matcher to match against, not {@code null}.
	 * @return {@code true} if the matcher matches, {@code false} otherwise.
	 * @since 1.0.0
	 */
	boolean match(Matcher matcher);
}
