package internal.su.pernova.assertions.matchers;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static su.pernova.assertions.Matchers.is;

import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;

import su.pernova.assertions.Matcher;
import su.pernova.assertions.MatcherContractTest;

/// @since 2.0.0
interface MultiMatcherContractTest extends MatcherContractTest {

	/// @return an incomplete matcher, not `null`.
	/// @param expectedValues expected values, not `null`.
	Matcher getIncompleteInstance(Object... expectedValues);

	/// @return an incomplete matcher, not `null`.
	/// @param expectedValues expected values, not `null`.
	Matcher getIncompleteInstance(double... expectedValues);

	/// @return an incomplete matcher, not `null`.
	/// @param expectedValues expected values, not `null`.
	Matcher getIncompleteInstance(float... expectedValues);

	/// @return an incomplete matcher, not `null`.
	/// @param expectedValues expected values, not `null`.
	Matcher getIncompleteInstance(long... expectedValues);

	/// @return an incomplete matcher, not `null`.
	/// @param expectedValues expected values, not `null`.
	Matcher getIncompleteInstance(int... expectedValues);

	/// @return an incomplete matcher, not `null`.
	/// @param expectedValues expected values, not `null`.
	Matcher getIncompleteInstance(short... expectedValues);

	/// @return an incomplete matcher, not `null`.
	/// @param expectedValues expected values, not `null`.
	Matcher getIncompleteInstance(byte... expectedValues);

	/// @return an incomplete matcher, not `null`.
	/// @param expectedValues expected values, not `null`.
	Matcher getIncompleteInstance(char... expectedValues);

	/// @return an incomplete matcher, not `null`.
	/// @param expectedValues expected values, not `null`.
	Matcher getIncompleteInstance(boolean... expectedValues);

	@Test
	default void matchingObjectsThrowsWhenMatcherIncomplete() {
		assertThrows(IllegalStateException.class, () -> getIncompleteInstance(this, this).match(this));
	}

	@Test
	default void matchingDoublesThrowsWhenMatcherIncomplete() {
		assertThrows(IllegalStateException.class, () -> getIncompleteInstance(new double[] { 0d, 0d }).match(0d));
	}

	@Test
	default void matchingFloatsThrowsWhenMatcherIncomplete() {
		assertThrows(IllegalStateException.class, () -> getIncompleteInstance(new float[] { 0f, 0f }).match(0f));
	}

	@Test
	default void matchingLongsThrowsWhenMatcherIncomplete() {
		assertThrows(IllegalStateException.class, () -> getIncompleteInstance(new long[] { 0L, 0L }).match(0L));
	}

	@Test
	default void matchingIntegersThrowsWhenMatcherIncomplete() {
		assertThrows(IllegalStateException.class, () -> getIncompleteInstance(new int[] { 0, 0 }).match(0));
	}

	@Test
	default void matchingShortsThrowsWhenMatcherIncomplete() {
		assertThrows(IllegalStateException.class, () -> getIncompleteInstance(new short[] { 0, 0 }).match((short) 0));
	}

	@Test
	default void matchingBytesThrowsWhenMatcherIncomplete() {
		assertThrows(IllegalStateException.class, () -> getIncompleteInstance(new byte[] { 0, 0 }).match((byte) 0));
	}

	@Test
	default void matchingCharactersThrowsWhenMatcherIncomplete() {
		assertThrows(IllegalStateException.class, () -> getIncompleteInstance(new char[] { '0', '0' }).match('0'));
	}

	@Test
	default void matchingBooleansThrowsWhenMatcherIncomplete() {
		assertThrows(IllegalStateException.class, () -> getIncompleteInstance(new boolean[] { false, false }).match(false));
	}

	@Test
	default void constructionThrowsWhenObjectArrayIsNull() {
		assertThrows(NullPointerException.class, () -> getIncompleteInstance((Object[]) null));
	}

	@Test
	default void constructionThrowsWhenDoubleArrayIsNull() {
		assertThrows(NullPointerException.class, () -> getIncompleteInstance((double[]) null));
	}

	@Test
	default void constructionThrowsWhenFloatArrayIsNull() {
		assertThrows(NullPointerException.class, () -> getIncompleteInstance((float[]) null));
	}

	@Test
	default void constructionThrowsWhenLongsIsNull() {
		assertThrows(NullPointerException.class, () -> getIncompleteInstance((long[]) null));
	}

	@Test
	default void constructionThrowsWhenIntegersIsNull() {
		assertThrows(NullPointerException.class, () -> getIncompleteInstance((int[]) null));
	}

	@Test
	default void constructionThrowsWhenShortsIsNull() {
		assertThrows(NullPointerException.class, () -> getIncompleteInstance((short[]) null));
	}

	@Test
	default void constructionThrowsWhenBytesIsNull() {
		assertThrows(NullPointerException.class, () -> getIncompleteInstance((byte[]) null));
	}

	@Test
	default void constructionThrowsWhenCharactersIsNull() {
		assertThrows(NullPointerException.class, () -> getIncompleteInstance((char[]) null));
	}

	@Test
	default void constructionThrowsWhenBooleansIsNull() {
		assertThrows(NullPointerException.class, () -> getIncompleteInstance((boolean[]) null));
	}

	@Test
	default void matchingEmptyObjectArrayDoesNotThrow() {
		assertDoesNotThrow(() -> is(getIncompleteInstance(new Object[0])).match(null));
	}

	@Test
	default void matchingEmptyDoublesDoesNotThrow() {
		assertDoesNotThrow(() -> is(getIncompleteInstance(new double[0])).match(null));
	}

	@Test
	default void matchingEmptyFloatsDoesNotThrow() {
		assertDoesNotThrow(() -> is(getIncompleteInstance(new float[0])).match(null));
	}

	@Test
	default void matchingEmptyLongsDoesNotThrow() {
		assertDoesNotThrow(() -> is(getIncompleteInstance(new long[0])).match(null));
	}

	@Test
	default void matchingEmptyIntegersDoesNotThrow() {
		assertDoesNotThrow(() -> is(getIncompleteInstance(new int[0])).match(null));
	}

	@Test
	default void matchingEmptyShortsDoesNotThrow() {
		assertDoesNotThrow(() -> is(getIncompleteInstance(new short[0])).match(null));
	}

	@Test
	default void matchingEmptyBytesDoesNotThrow() {
		assertDoesNotThrow(() -> is(getIncompleteInstance(new byte[0])).match(null));
	}

	@Test
	default void matchingEmptyCharactersDoesNotThrow() {
		assertDoesNotThrow(() -> is(getIncompleteInstance(new char[0])).match(null));
	}

	@Test
	default void matchingEmptyBooleansDoesNotThrow() {
		assertDoesNotThrow(() -> is(getIncompleteInstance(new boolean[0])).match(null));
	}

	@Test
	default void orPropagatesIncompletenessOfMatcher() {
		final Matcher incompleteMatcher = getIncompleteInstance("a", "b").or(getIncompleteInstance("c", "d"));
		assertThrows(IllegalStateException.class, () -> incompleteMatcher.match("e"));
		assertEquals("...", incompleteMatcher.toString());
		final Matcher matcher = is(incompleteMatcher); // Completes the incomplete matcher
		assertDoesNotThrow(() -> matcher.match("e"));
		final Pattern pattern = Pattern.compile("is\\{or\\{\\w+\\{i̶s̶O̶b̶j̶e̶c̶t̶\\(a\\)}\\{i̶s̶O̶b̶j̶e̶c̶t̶\\(b\\)}}\\{\\w+\\{i̶s̶O̶b̶j̶e̶c̶t̶\\(c\\)}\\{i̶s̶O̶b̶j̶e̶c̶t̶\\(d\\)}}}");
		final String stringValue = matcher.toString();
		assertTrue(pattern.matcher(stringValue).matches(), stringValue);
	}

	@Test
	default void orPropagatesCompletenessOfMatcher() {
		// The difference is in the placement of the parenthesis.
		// This time, the "or" is called on the COMPLETED matcher.
		final Matcher matcher = is(getIncompleteInstance("a", "b")).or(getIncompleteInstance("c", "d"));
		assertDoesNotThrow(() -> matcher.match("e"));
		final Pattern pattern = Pattern.compile("or\\{is\\{\\w+\\{i̶s̶O̶b̶j̶e̶c̶t̶\\(a\\)}\\{i̶s̶O̶b̶j̶e̶c̶t̶\\(b\\)}}}\\{\\w+\\{i̶s̶O̶b̶j̶e̶c̶t̶\\(c\\)}\\{i̶s̶O̶b̶j̶e̶c̶t̶\\(d\\)}}");
		final String stringValue = matcher.toString();
		assertTrue(pattern.matcher(stringValue).matches(), stringValue);
	}

	@Test
	default void andPropagatesIncompletenessOfMatcher() {
		final Matcher incompleteMatcher = getIncompleteInstance("a", "b").and(getIncompleteInstance("c", "d"));
		assertThrows(IllegalStateException.class, () -> incompleteMatcher.match("e"));
		assertEquals("...", incompleteMatcher.toString());
		final Matcher matcher = is(incompleteMatcher); // Complete the incomplete matcher
		assertDoesNotThrow(() -> matcher.match("e"));
		final Pattern pattern = Pattern.compile("is\\{and\\{\\w+\\{i̶s̶O̶b̶j̶e̶c̶t̶\\(a\\)}\\{i̶s̶O̶b̶j̶e̶c̶t̶\\(b\\)}}\\{\\w+\\{i̶s̶O̶b̶j̶e̶c̶t̶\\(c\\)}\\{i̶s̶O̶b̶j̶e̶c̶t̶\\(d\\)}}}");
		final String stringValue = matcher.toString();
		assertTrue(pattern.matcher(stringValue).matches(), stringValue);
	}

	@Test
	default void andPropagatesCompletenessOfMatcher() {
		// The difference is in the placement of the parenthesis.
		// This time, the "or" is called on the COMPLETED matcher.
		final Matcher matcher = is(getIncompleteInstance("a", "b")).and(getIncompleteInstance("c", "d"));
		assertDoesNotThrow(() -> matcher.match("e"));
		final Pattern pattern = Pattern.compile("and\\{is\\{\\w+\\{i̶s̶O̶b̶j̶e̶c̶t̶\\(a\\)}\\{i̶s̶O̶b̶j̶e̶c̶t̶\\(b\\)}}}\\{\\w+\\{i̶s̶O̶b̶j̶e̶c̶t̶\\(c\\)}\\{i̶s̶O̶b̶j̶e̶c̶t̶\\(d\\)}}");
		final String stringValue = matcher.toString();
		assertTrue(pattern.matcher(stringValue).matches(), stringValue);
	}
}
