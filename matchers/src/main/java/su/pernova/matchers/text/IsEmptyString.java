
package su.pernova.matchers.text;

import static su.pernova.matchers.Matchers.is;
import static su.pernova.matchers.core.AnyOf.anyOf;

import su.pernova.matchers.Description;
import su.pernova.matchers.Matcher;
import su.pernova.matchers.TypeSafeMatcher;
import su.pernova.matchers.core.SameAsMatcher;

/**
 * Matches empty Strings (and null).
 */
public final class IsEmptyString extends TypeSafeMatcher<String> {
    private static final IsEmptyString INSTANCE = new IsEmptyString();
    @SuppressWarnings("unchecked")
    private static final Matcher<String> NULL_OR_EMPTY_INSTANCE = anyOf(new SameAsMatcher<>("", null), INSTANCE);

    private IsEmptyString() { }

    @Override
    public boolean matchesSafely(String item) {
        return item.equals("");
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("an empty string");
    }

    /**
     * Creates a matcher of {@link String} that matches when the examined string has zero length.
     * For example:
     * <pre>assertThat("", isEmptyString())</pre>
     * 
     * @deprecated use is(emptyString()) instead
     */
    @Deprecated
    public static Matcher<String> isEmptyString() {
        return emptyString();
    }

    /**
     * Creates a matcher of {@link String} that matches when the examined string has zero length.
     * For example:
     * <pre>assertThat("", is(emptyString()))</pre>
     * 
     */
    public static Matcher<String> emptyString() {
        return INSTANCE;
    }

    /**
     * Creates a matcher of {@link String} that matches when the examined string is <code>null</code>, or
     * has zero length.
     * For example:
     * <pre>assertThat(((String)null), isEmptyOrNullString())</pre>
     * 
     * @deprecated use is(emptyOrNullString()) instead
     * 
     */
    @Deprecated
    public static Matcher<String> isEmptyOrNullString() {
        return emptyOrNullString();
    }

    /**
     * Creates a matcher of {@link String} that matches when the examined string is <code>null</code>, or
     * has zero length.
     * For example:
     * <pre>assertThat(((String)null), is(emptyOrNullString()))</pre>
     * 
     */
    public static Matcher<String> emptyOrNullString() {
        return NULL_OR_EMPTY_INSTANCE;
    }
}
