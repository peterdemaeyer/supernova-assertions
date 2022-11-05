package su.pernova.matchers.core;

import su.pernova.matchers.Matcher;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static su.pernova.matchers.AbstractMatcherTest.*;
import static su.pernova.matchers.core.StringContains.containsString;

public final class EveryTest {

    private final Matcher<Iterable<? extends String>> matcher = Every.everyItem(containsString("a"));

    @Test public void
    copesWithNullsAndUnknownTypes() {
        assertNullSafe(matcher);
        assertUnknownTypeSafe(matcher);
    }

    @Test public void
    matchesOnlyWhenEveryItemMatches() {
        assertMatches(matcher, asList("AaA", "BaB", "CaC"));
        assertDoesNotMatch(matcher, asList("AaA", "BXB", "CaC"));
    }

    @Test public void
    matchesEmptyLists() {
        assertMatches("didn't match empty list", matcher, new ArrayList<String>());
    }

    @Test public void
    describesItself() {
        assertDescription("every item is a string containing \"a\"", matcher);
    }

    @Test public void
    describesAMismatch() {
        assertMismatchDescription("an item was \"BXB\"", matcher, singletonList("BXB"));
    }
}
