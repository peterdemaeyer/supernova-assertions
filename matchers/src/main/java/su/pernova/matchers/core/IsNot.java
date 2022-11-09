package su.pernova.matchers.core;

import su.pernova.matchers.Matcher;
import su.pernova.matchers.Description;

import static su.pernova.matchers.core.EqualsMatcher.equalTo;


/**
 * Calculates the logical negation of a matcher.
 */
public class IsNot<T> extends Matcher<T> {
    private final Matcher<T> matcher;

    public IsNot(Matcher<T> matcher) {
        this.matcher = matcher;
    }

    @Override
    public boolean matches(Object arg) {
        return !matcher.matches(arg);
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("not ").appendDescriptionOf(matcher);
    }

    
    /**
     * Creates a matcher that wraps an existing matcher, but inverts the logic by which
     * it will match.
     * For example:
     * <pre>assertThat(cheese, is(not(equalTo(smelly))))</pre>
     * 
     * @param matcher
     *     the matcher whose sense should be inverted
     */
    public static <T> Matcher<T> not(Matcher<T> matcher) {
        return new IsNot<T>(matcher);
    }

    /**
     * A shortcut to the frequently used <code>not(equalTo(x))</code>.
     * For example:
     * <pre>assertThat(cheese, is(not(smelly)))</pre>
     * instead of:
     * <pre>assertThat(cheese, is(not(equalTo(smelly))))</pre>
     * 
     * @param value
     *     the value that any examined object should <b>not</b> equal
     */
    public static <T> Matcher<T> not(T value) {
        return not(equalTo(value));
    }
}
