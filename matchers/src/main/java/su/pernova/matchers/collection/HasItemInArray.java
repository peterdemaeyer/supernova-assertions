package su.pernova.matchers.collection;

import su.pernova.matchers.Description;
import su.pernova.matchers.Matcher;
import su.pernova.matchers.TypeSafeDiagnosingMatcher;
import su.pernova.matchers.TypeSafeMatcher;
import su.pernova.matchers.core.IsIterableContaining;

import static java.util.Arrays.asList;

/**
 * Matches if an array contains an item satisfying a nested matcher.
 */
public class HasItemInArray<T> extends TypeSafeMatcher<T[]> {
    private final Matcher<? super T> elementMatcher;
    private final TypeSafeDiagnosingMatcher<Iterable<? super T>> collectionMatcher;

    public HasItemInArray(Matcher<? super T> elementMatcher) {
        this.elementMatcher = elementMatcher;
        this.collectionMatcher = new IsIterableContaining<>(elementMatcher);
    }

    @Override
    public boolean matchesSafely(T[] actual) {
        return collectionMatcher.matches(asList(actual));
    }
    
    @Override
    public void describeMismatchSafely(T[] actual, Description mismatchDescription) {
        collectionMatcher.describeMismatch(asList(actual), mismatchDescription);
    }

    @Override
    public void describeTo(Description description) {
        description
            .appendText("an array containing ")
            .appendDescriptionOf(elementMatcher);
    }

}
