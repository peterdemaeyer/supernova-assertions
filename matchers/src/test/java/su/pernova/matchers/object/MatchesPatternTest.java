package su.pernova.matchers.object;

import su.pernova.matchers.Matcher;
import su.pernova.matchers.text.MatchesPattern;
import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

import static su.pernova.matchers.AbstractMatcherTest.*;
import static su.pernova.matchers.MatcherAssertions.assertThat;

public class MatchesPatternTest {
    @Test
    public void copesWithNullsAndUnknownTypes() {
        Matcher<String> matcher = new MatchesPattern(Pattern.compile("."));

        assertNullSafe(matcher);
        assertUnknownTypeSafe(matcher);
    }

    @Test
    public void matchesExactString() {
        assertThat("a", new MatchesPattern(Pattern.compile("a")));
    }

    @Test
    public void doesNotMatchADifferentString() {
        assertDoesNotMatch("A different string does not match", new MatchesPattern(Pattern.compile("a")), "b");
    }

    @Test
    public void doesNotMatchSubstring() {
        assertDoesNotMatch("A substring does not match", new MatchesPattern(Pattern.compile("a")), "ab");
    }

    @Test
    public void hasAReadableDescription() {
        Matcher<?> m = new MatchesPattern(Pattern.compile("a[bc](d|e)"));
        assertDescription("a string matching the pattern 'a[bc](d|e)'", m );
    }

    @Test
    public void describesAMismatch() {
        final Matcher<String> matcher = new MatchesPattern(Pattern.compile("a"));
        assertMismatchDescription("was \"Cheese\"", matcher, "Cheese");
    }

    @Test
    public void factoryMethodAllowsCreationWithPattern() {
        Matcher<?> m = MatchesPattern.matchesPattern(Pattern.compile("a[bc](d|e)"));
        assertDescription("a string matching the pattern 'a[bc](d|e)'", m );
    }

    @Test
    public void factoryMethodAllowsCreationWithString() {
        Matcher<?> m = MatchesPattern.matchesPattern("a[bc](d|e)");
        assertDescription("a string matching the pattern 'a[bc](d|e)'", m );
    }
}
