package su.pernova.matchers.collection;

import su.pernova.matchers.AbstractMatcherTest;
import su.pernova.matchers.Matcher;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import static su.pernova.matchers.collection.IsMapContaining.hasEntry;
import static su.pernova.matchers.core.IsAnything.anything;
import static su.pernova.matchers.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class IsMapContainingTest extends AbstractMatcherTest {

    @Override
    protected Matcher<?> createMatcher() {
        return IsMapContaining.hasEntry("irrelevant", "irrelevant");
    }

    public void testMatchesMapContainingMatchingKeyAndValue() {
        Map<String,Integer> map = new TreeMap<>();
        map.put("a", 1);
        map.put("b", 2);

        assertMatches("matcherA", hasEntry(equalTo("a"), equalTo(1)), map);
        assertMatches("matcherB", hasEntry(equalTo("b"), equalTo(2)), map);
        assertMismatchDescription("map was [<a=1>, <b=2>]", hasEntry(equalTo("c"), equalTo(3)), map);
    }

    @SuppressWarnings("unchecked")
    public void testMatchesMapContainingMatchingKeyAndValueWithoutGenerics() {
        Map map = new HashMap();
        map.put("a", 1);
        map.put("b", 2);

        assertMatches("matcherA", hasEntry(equalTo("a"), equalTo(1)), map);
        assertMatches("matcherB", hasEntry(equalTo("b"), equalTo(2)), map);
        assertFalse(hasEntry(equalTo("c"), equalTo(3)).matches(map), "matcherC"); // working around generics problem
    }

    public void testDoesNotMatchNull() {
        assertMismatchDescription("was null", hasEntry(anything(), anything()), null);
    }

    public void testHasReadableDescription() {
        assertDescription("map containing [\"a\"-><2>]", hasEntry(equalTo("a"), (equalTo(2))));
    }
}
