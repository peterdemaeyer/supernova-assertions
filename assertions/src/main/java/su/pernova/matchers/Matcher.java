package su.pernova.matchers;

/**
 * <p>
 * This abstract superclass defines an expectation to evaluate an actual value against.
 * Concrete subclasses are provided as an essential part of this library.
 * These can be complemented by the user's custom concrete subclasses, which is another essential part of this library.
 * </p>
 *
 * <p>
 * A matcher is self-describing such that it can report upon mismatch.
 * </p>
 *
 * <p>
 * <b>Properties of good matchers</b>
 * <ul>
 *     <li>
 *         Matchers should not modify the state of the actual parameter.
 *         When comparing streams for example, it is good practice to compare stream suppliers (or factories)
 *         rather than the streams themselves.
 *     </li>
 *     <li>Matchers should be immutable.</li>
 * </ul>
 * </p>
 *
 */
public abstract class Matcher<T> implements SelfDescribing {

    /**
     * Matches against a given actual value, which is possibly {@code null}.
     *
     * <p>
     *     This method has a parameter of type {@code Object} rather than the generic type {@code T} because the caller
     *     does not knw the runtime type (because of type erasure with Java generics).
     *     It is down to the implementations to check the correct type.
     * </p>
     *
     * @param actual a given actual value to match against, possibly {@code null}.
     * @return {@code true} upon match, {@code false} upon mismatch.
     */
    public abstract boolean matches(Object actual);

    /**
     * Describes the mismatch of a given item to a given description.
     * The description will be part of a larger description of a mismatch, so it should be concise.
     * This method assumes that {@link #matches} is {@code false}, but does will not check this.
     *
     * @param actual the actual object that mismatches, possibly {@code null}.
     * @param description a description to be built or appended to, never {@code null}.
     */
    public void describeMismatch(Object actual, Description description) {
        description.appendText("was ").appendValue(actual);
    }

    @Override
    public String toString() {
        return StringDescription.toString(this);
    }

    /**
     * Useful null-check method. Writes a mismatch description if the actual object is null
     * @param actual the object to check
     * @param mismatch where to write the mismatch description, if any
     * @return false iff the actual object is null
     */
    protected static boolean isNotNull(Object actual, Description mismatch) {
        if (actual == null) {
            mismatch.appendText("was null");
            return false;
        }
        return true;
    }
}
