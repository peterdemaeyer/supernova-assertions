package su.pernova.matchers.collection;

import su.pernova.matchers.AbstractMatcherTest;
import su.pernova.matchers.Matcher;
import su.pernova.matchers.MatcherAssert;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.util.Arrays.asList;
import static su.pernova.matchers.collection.IsCollectionWithSize.hasSize;
import static su.pernova.matchers.core.IsEqual.equalTo;

public class IsCollectionWithSizeTest extends AbstractMatcherTest {

    @Override
    protected Matcher<?> createMatcher() {
        return hasSize(7);
    }

    public void testMatchesWhenSizeIsCorrect() {
        assertMatches("correct size", hasSize(equalTo(2)), asList(null, null));
        assertMismatchDescription("collection size was <3>", hasSize(equalTo(2)), asList(null, null, null));
    }

    public void testMatchesCollectionWhenSizeIsCorrectUsingObjectElementType() {
        Collection<Object> list = asList(null, null);
        assertMatches("correct size", hasSize(equalTo(2)), list);
        assertMismatchDescription("collection size was <2>", hasSize(equalTo(3)), list);
    }

    public void testMatchesCollectionWhenSizeIsCorrectUsingStringElementType() {
        Collection<String> list = asList("a", "b");
        assertMatches("correct size", hasSize(equalTo(2)), list);
        assertMismatchDescription("collection size was <2>", hasSize(equalTo(3)), list);
    }

    public void testMatchesCollectionWhenSizeIsCorrectUsingWildcardElementType() {
        Collection<?> list = asList("a", "b");
        assertMatches("correct size", hasSize(equalTo(2)), list);
        assertMismatchDescription("collection size was <2>", hasSize(equalTo(3)), list);
    }

    public void testMatchesListWhenSizeIsCorrectUsingObjectElementType() {
        List<Object> list = asList(null, null);
        assertMatches("correct size", hasSize(equalTo(2)), list);
        assertMismatchDescription("collection size was <2>", hasSize(equalTo(3)), list);
    }

    public void testMatchesListWhenSizeIsCorrectUsingStringElementType() {
        List<String> list = asList("a", "b");
        assertMatches("correct size", hasSize(equalTo(2)), list);
        assertMismatchDescription("collection size was <2>", hasSize(equalTo(3)), list);
    }

    public void testMatchesListWhenSizeIsCorrectUsingWildcardElementType() {
        List<?> list = asList("a", "b");
        assertMatches("correct size", hasSize(equalTo(2)), list);
        assertMismatchDescription("collection size was <2>", hasSize(equalTo(3)), list);
    }

    public void testProvidesConvenientShortcutForHasSizeEqualTo() {
        assertMatches("correct size", hasSize(2), asList(null, null));
        assertMismatchDescription("collection size was <3>", hasSize(2), asList(null, null, null));
    }

    public void testHasAReadableDescription() {
        assertDescription("a collection with size <3>", hasSize(equalTo(3)));
    }
    
    public void testCompilesWithATypedCollection() {
      // To prove Issue 43
      ArrayList<String> arrayList = new ArrayList<String>();
      MatcherAssert.assertThat(arrayList, hasSize(0));
    }
}
