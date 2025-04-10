package su.pernova.assertions;

/**
 * This interface defines the subject of an assertion of the form
 * "assert that &lt;(actual) subject&gt; &lt;holds&gt; &lt;relation with&gt; &lt;(expected) object&gt;".
 * A subject transforms an actual object into the subject of a relation.
 * The default subject "is" the actual object, not transforming it, but for example a "content of" subject transformq an
 * actual object into its content before subjecting it to the relation test.
 *
 * @since 2.0.0
 */
public class Subject implements Describable {

	protected final Object actual;

	public Subject(Object actual) {
		this.actual = actual;
	}

	/**
	 * Matches this subject against a given matcher.
	 *
	 * @param matcher a matcher to match against, not {@code null}.
	 * @return {@code true} if the matcher matches, {@code false} otherwise.
	 * @since 1.0.0
	 */
	public boolean match(Matcher matcher) {
		return matcher.match(Context.get(this).actualTransformation().apply(actual));
	}

	@Override
	public Description describeMismatch(Description mismatchDescription) {
		return mismatchDescription.appendPrompt().appendActual(actual);
	}
}
