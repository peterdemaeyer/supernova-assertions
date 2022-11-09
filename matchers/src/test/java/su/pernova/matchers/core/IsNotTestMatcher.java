package su.pernova.matchers.core;

import su.pernova.matchers.Matcher;
import org.junit.jupiter.api.Test;

import static su.pernova.matchers.AbstractMatcherTest.*;
import static su.pernova.matchers.core.EqualsMatcher.equalTo;
import static su.pernova.matchers.core.IsInstanceOf.instanceOf;
import static su.pernova.matchers.core.IsNot.not;

public final class IsNotTestMatcher {

    @Test public void
    copesWithNullsAndUnknownTypes() {
        Matcher<String> matcher = not("something");

        assertNullSafe(matcher);
        assertUnknownTypeSafe(matcher);
    }

    @Test public void
    evaluatesToTheTheLogicalNegationOfAnotherMatcher() {
        final Matcher<String> matcher = not(equalTo("A"));

        assertMatches(matcher, "B");
        assertDoesNotMatch(matcher, "A");
    }

    @Test public void
    providesConvenientShortcutForNotEqualTo() {
        final Matcher<String> matcher = not("A");

        assertMatches(matcher, "B");
        assertDoesNotMatch(matcher, "A");
    }

    @Test public void
    usesDescriptionOfNegatedMatcherWithPrefix() {
        assertDescription("not an instance of java.lang.String", not(instanceOf(String.class)));
        assertDescription("not \"A\"", not("A"));
    }
}
