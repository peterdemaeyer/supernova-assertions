package su.pernova.matchers.core;

import su.pernova.matchers.Matcher;
import org.junit.jupiter.api.Test;

import static su.pernova.matchers.AbstractMatcherTest.*;
import static su.pernova.matchers.core.IsNull.notNullValue;
import static su.pernova.matchers.core.IsNull.nullValue;


public final class IsNullTest {

    private final Matcher<Object> nullMatcher = nullValue();
    private final Matcher<Object> notNullMatcher = notNullValue();

    @Test public void
    copesWithNullsAndUnknownTypes() {
        assertNullSafe(nullMatcher);
        assertUnknownTypeSafe(nullMatcher);
        
        assertNullSafe(notNullMatcher);
        assertUnknownTypeSafe(notNullMatcher);
    }

    @Test public void
    evaluatesToTrueIfArgumentIsNull() {
        assertMatches(nullMatcher, null);
        assertDoesNotMatch(nullMatcher, new Object());
        
        assertMatches(notNullMatcher, new Object());
        assertDoesNotMatch(notNullMatcher, null);
    }
    
    @Test public void
    supportsStaticTyping() {
        requiresStringMatcher(nullValue(String.class));
        requiresStringMatcher(notNullValue(String.class));
    }

    private void requiresStringMatcher(@SuppressWarnings("unused") Matcher<String> arg) {
        // no-op
    }
}
