package su.pernova.matchers;


public class MatcherAssertions {

    private MatcherAssertions() {
        // Prevent instantiation.
    }

    public static <T> void assertThat(T actual, Matcher<? super T> matcher) {
        assertThat(actual, matcher, "");
    }
    
    public static <T> void assertThat(T actual, Matcher<? super T> matcher, String reason) {
        if (!matcher.matches(actual)) {
            Description description = new StringDescription();
            description.appendText(reason)
                       .appendText(System.lineSeparator())
                       .appendText("Expected: ")
                       .appendDescriptionOf(matcher)
                       .appendText(System.lineSeparator())
                       .appendText("     but: ");
            matcher.describeMismatch(actual, description);
            
            throw new AssertionError(description.toString());
        }
    }
    
    public static void assertThat(boolean assertion, String reason) {
        if (!assertion) {
            throw new AssertionError(reason);
        }
    }
}
