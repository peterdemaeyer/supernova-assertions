package su.pernova.matchers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public final class MatcherTest {

    @Test
    public void
    describesItselfWithToStringMethod() {
        Matcher<Object> someMatcher = new Matcher<Object>() {
            @Override
            public boolean matches(Object item) {
                throw new UnsupportedOperationException();
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("SOME DESCRIPTION");
            }
        };

        assertEquals("SOME DESCRIPTION", someMatcher.toString());
    }
}
