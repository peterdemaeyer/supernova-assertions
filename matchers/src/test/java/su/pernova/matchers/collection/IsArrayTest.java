package su.pernova.matchers.collection;

import su.pernova.matchers.AbstractMatcherTest;
import su.pernova.matchers.Matcher;
import su.pernova.matchers.Description;

import static su.pernova.matchers.collection.IsArray.array;
import static su.pernova.matchers.core.EqualsMatcher.equalTo;

@SuppressWarnings("unchecked")
public class IsArrayTest extends AbstractMatcherTest {

    @Override
    protected Matcher<?> createMatcher() {
        return array(equalTo("irrelevant"));
    }

    public void testMatchesAnArrayThatMatchesAllTheElementMatchers() {
        assertMatches("should match array with matching elements",
                array(equalTo("a"), equalTo("b"), equalTo("c")), new String[]{"a", "b", "c"});
    }
    
    public void testDoesNotMatchAnArrayWhenElementsDoNotMatch() {
        assertDoesNotMatch("should not match array with different elements",
                array(equalTo("a"), equalTo("b")), new String[]{"b", "c"});
    }
    
    public void testDoesNotMatchAnArrayOfDifferentSize() {
        assertDoesNotMatch("should not match larger array",
                           array(equalTo("a"), equalTo("b")), new String[]{"a", "b", "c"});
        assertDoesNotMatch("should not match smaller array",
                           array(equalTo("a"), equalTo("b")), new String[]{"a"});
    }
    
    public void testDoesNotMatchNull() {
        assertDoesNotMatch("should not match null",
                array(equalTo("a")), null);
    }
    
    public void testHasAReadableDescription() {
        assertDescription("[\"a\", \"b\"]", array(equalTo("a"), equalTo("b")));
    }
    
    public void testHasAReadableMismatchDescriptionUsing() {
        assertMismatchDescription("element <0> was \"c\"", array(equalTo("a"), equalTo("b")), new String[]{"c", "b"});
    }
    
    public void testHasAReadableMismatchDescriptionUsingCustomMatchers() {
        final Matcher<String> m = new Matcher<String>() {
            @Override public boolean matches(Object item) { return false; }
            @Override public void describeTo(Description description) { description.appendText("c"); }
            @Override public void describeMismatch(Object actual, Description description) {
                description.appendText("didn't match");
            }
        };
        assertMismatchDescription("element <0> didn't match", array(m, equalTo("b")), new String[]{"c", "b"});
    }
}
