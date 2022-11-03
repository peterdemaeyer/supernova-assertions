package su.pernova.matchers;

/**
 * TODO(ngd): Document.
 *
 * @param <T>
 */
public abstract class DiagnosingMatcher<T> extends Matcher<T> {

    @Override
    public final boolean matches(Object item) {
        return matches(item, Description.NONE);
    }

    @Override
    public final void describeMismatch(Object actual, Description mismatchDescription) {
        matches(actual, mismatchDescription);
    }

    protected abstract boolean matches(Object item, Description mismatchDescription);
}
