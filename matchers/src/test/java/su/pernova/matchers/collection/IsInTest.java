package su.pernova.matchers.collection;

import static org.junit.jupiter.api.Assertions.assertEquals;

import su.pernova.matchers.AbstractMatcherTest;
import su.pernova.matchers.Matcher;
import su.pernova.matchers.StringDescription;

import java.util.Arrays;
import java.util.Collection;

public class IsInTest extends AbstractMatcherTest {
    String[] elements = {"a", "b", "c"};

    @Override
    protected Matcher<?> createMatcher() {
        return new IsIn<String>(elements);
    }

    public void testReturnsTrueIfArgumentIsInCollection() {
        Collection<String> collection = Arrays.asList(elements);
        Matcher<String> isIn = new IsIn<String>(collection);
        
        assertMatches("a", isIn, "a");
        assertMatches("b", isIn, "b");
        assertMatches("c", isIn, "c");
        assertDoesNotMatch("d", isIn, "d");
    }
    
    public void testReturnsTrueIfArgumentIsInArray() {
        Matcher<String> isIn = new IsIn<String>(elements);
        
        assertMatches("a", isIn, "a");
        assertMatches("b", isIn, "b");
        assertMatches("c", isIn, "c");
        assertDoesNotMatch("d", isIn, "d");
    }
    
    public void testHasReadableDescription() {
        Matcher<String> isIn = new IsIn<String>(elements);
        
        assertEquals("description", 
            "one of {\"a\", \"b\", \"c\"}", 
            StringDescription.toString(isIn));
    }
}
