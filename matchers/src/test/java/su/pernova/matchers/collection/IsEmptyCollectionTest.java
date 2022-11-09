package su.pernova.matchers.collection;

import static java.util.Arrays.asList;

import static su.pernova.matchers.collection.IsEmptyCollection.empty;
import static su.pernova.matchers.core.CoreMatchers.is;

import java.util.ArrayList;
import java.util.Collection;

import su.pernova.matchers.AbstractMatcherTest;
import su.pernova.matchers.Matcher;

public class IsEmptyCollectionTest extends AbstractMatcherTest {

    @Override
    protected Matcher<Collection<?>> createMatcher() {
        return empty();
    }

    public void testMatchesAnEmptyCollection() {
        assertMatches("empty collection", createMatcher(), emptyCollection());
    }

    public void testDoesNotMatchACollectionWithAnItem() {
        assertMismatchDescription("<[one, three]>", is(createMatcher()), collectionOfValues());
    }

    public void testHasAReadableDescription() {
        assertDescription("an empty collection", createMatcher());
    }

    public void testCompiles() {
        needs(IsEmptyCollection.emptyCollectionOf(String.class));
    }

    private void needs(@SuppressWarnings("unused") Matcher<Collection<String>> bar) { }
    
    private static Collection<String> collectionOfValues() {
        return new ArrayList<String>(asList("one", "three"));
    }

    private static Collection<Integer> emptyCollection() {
        return new ArrayList<Integer>();
    }
}
