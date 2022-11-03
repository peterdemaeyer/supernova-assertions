package su.pernova.matchers.core;

import su.pernova.matchers.Matcher;
import su.pernova.matchers.Description;

/**
 * A matcher that always returns <code>true</code>.
 */
public class IsAnything<T> extends Matcher<T> {

    private final String message;

    public IsAnything() {
        this("ANYTHING");
    }

    public IsAnything(String message) {
        this.message = message;
    }

    @Override
    public boolean matches(Object o) {
        return true;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText(message);
    }

    /**
     * Creates a matcher that always matches, regardless of the examined object.
     */
    public static Matcher<Object> anything() { return new IsAnything<>(); }

    /**
     * Creates a matcher that always matches, regardless of the examined object, but describes
     * itself with the specified {@link String}.
     *
     * @param description
     *     a meaningful {@link String} used when describing itself
     */
    public static Matcher<Object> anything(String description) {
        return new IsAnything<>(description);
    }
}
