
package su.pernova.matchers.text;

import static su.pernova.matchers.Matchers.is;
import static su.pernova.matchers.core.AnyOf.anyOf;

import java.util.regex.Pattern;

import su.pernova.matchers.Description;
import su.pernova.matchers.Matcher;
import su.pernova.matchers.TypeSafeMatcher;
import su.pernova.matchers.internal.core.SameAs;

/**
 * Matches blank Strings (and null).
 */
public final class IsBlankString extends TypeSafeMatcher<String> {
	private static final IsBlankString BLANK_INSTANCE = new IsBlankString();

	private static final Matcher<String> NULL_OR_BLANK_INSTANCE = anyOf(new SameAs<>("", null), BLANK_INSTANCE);

	private static final Pattern REGEX_WHITESPACE = Pattern.compile("\\s*");

	private IsBlankString() {
	}

	@Override
	public boolean matchesSafely(String item) {
		return REGEX_WHITESPACE.matcher(item).matches();
	}

	@Override
	public void describeTo(Description description) {
		description.appendText("a blank string");
	}

	/**
	 * Creates a matcher of {@link String} that matches when the examined string contains
	 * zero or more whitespace characters and nothing else.
	 * For example:
	 * <pre>assertThat("  ", is(blankString()))</pre>
	 */
	public static Matcher<String> blankString() {
		return BLANK_INSTANCE;
	}

	/**
	 * Creates a matcher of {@link String} that matches when the examined string is <code>null</code>, or
	 * contains zero or more whitespace characters and nothing else.
	 * For example:
	 * <pre>assertThat(((String)null), is(blankOrNullString()))</pre>
	 */
	public static Matcher<String> blankOrNullString() {
		return NULL_OR_BLANK_INSTANCE;
	}
}
