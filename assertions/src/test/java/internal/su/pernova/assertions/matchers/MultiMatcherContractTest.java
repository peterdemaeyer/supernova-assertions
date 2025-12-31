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
		assertEquals("incomplete matcher",
				assertThrows(IllegalStateException.class,
						() -> getIncompleteInstance(this, this).match(this))
						.getMessage());
	}

	@Test
	default void matchingDoublesThrowsWhenMatcherIncomplete() {
		assertEquals("incomplete matcher",
				assertThrows(IllegalStateException.class,
						() -> getIncompleteInstance(new double[] { 0d, 0d }).match(0d))
						.getMessage());
	}

	@Test
	default void matchingFloatsThrowsWhenMatcherIncomplete() {
		assertEquals("incomplete matcher",
				assertThrows(IllegalStateException.class,
						() -> getIncompleteInstance(new float[] { 0f, 0f }).match(0f))
						.getMessage());
	}

	@Test
	default void matchingLongsThrowsWhenMatcherIncomplete() {
		assertEquals("incomplete matcher",
			assertThrows(IllegalStateException.class,
					() -> getIncompleteInstance(new long[] { 0L, 0L }).match(0L))
					.getMessage());
	}

	@Test
	default void matchingIntegersThrowsWhenMatcherIncomplete() {
		assertEquals("incomplete matcher",
				assertThrows(IllegalStateException.class,
						() -> getIncompleteInstance(new int[] { 0, 0 }).match(0))
						.getMessage());
	}

	@Test
	default void matchingShortsThrowsWhenMatcherIncomplete() {
		assertEquals("incomplete matcher",
				assertThrows(IllegalStateException.class,
						() -> getIncompleteInstance(new short[] { 0, 0 }).match((short) 0))
						.getMessage());
	}

	@Test
	default void matchingBytesThrowsWhenMatcherIncomplete() {
		assertEquals("incomplete matcher",
				assertThrows(IllegalStateException.class,
						() -> getIncompleteInstance(new byte[] { 0, 0 }).match((byte) 0))
						.getMessage());
	}

	@Test
	default void matchingCharactersThrowsWhenMatcherIncomplete() {
		assertEquals("incomplete matcher",
				assertThrows(IllegalStateException.class,
						() -> getIncompleteInstance(new char[] { '0', '0' }).match('0'))
						.getMessage());
	}

	@Test
	default void matchingBooleansThrowsWhenMatcherIncomplete() {
		assertEquals("incomplete matcher",
				assertThrows(IllegalStateException.class,
						() -> getIncompleteInstance(new boolean[] { false, false }).match(false))
						.getMessage());
	}

	@Test
	default void constructionThrowsWhenObjectArrayIsNull() {
		assertEquals("array of expected values is null",
				assertThrows(NullPointerException.class,
						() -> getIncompleteInstance((Object[]) null))
						.getMessage());
	}

	@Test
	default void constructionThrowsWhenDoubleArrayIsNull() {
		assertEquals("array of expected values is null",
				assertThrows(NullPointerException.class,
						() -> getIncompleteInstance((double[]) null))
						.getMessage());
	}

	@Test
	default void constructionThrowsWhenFloatArrayIsNull() {
		assertEquals("array of expected values is null",
				assertThrows(NullPointerException.class,
						() -> getIncompleteInstance((float[]) null))
						.getMessage());
	}

	@Test
	default void constructionThrowsWhenLongArrayIsNull() {
		assertEquals("array of expected values is null",
				assertThrows(NullPointerException.class,
						() -> getIncompleteInstance((long[]) null))
						.getMessage());
	}

	@Test
	default void constructionThrowsWhenIntegerArrayIsNull() {
		assertEquals("array of expected values is null",
				assertThrows(NullPointerException.class,
						() -> getIncompleteInstance((int[]) null))
						.getMessage());
	}

	@Test
	default void constructionThrowsWhenShortArrayIsNull() {
		assertEquals("array of expected values is null",
				assertThrows(NullPointerException.class,
						() -> getIncompleteInstance((short[]) null))
						.getMessage());
	}

	@Test
	default void constructionThrowsWhenByteArrayIsNull() {
		assertEquals("array of expected values is null",
				assertThrows(NullPointerException.class,
						() -> getIncompleteInstance((byte[]) null))
						.getMessage());
	}

	@Test
	default void constructionThrowsWhenCharacterArrayIsNull() {
		assertEquals("array of expected values is null",
				assertThrows(NullPointerException.class,
						() -> getIncompleteInstance((char[]) null))
						.getMessage());
	}

	@Test
	default void constructionThrowsWhenBooleanArrayIsNull() {
		assertEquals("array of expected values is null",
				assertThrows(NullPointerException.class,
						() -> getIncompleteInstance((boolean[]) null))
						.getMessage());
	}

	@Test
	default void matchingEmptyObjectArrayDoesNotThrow() {
		assertDoesNotThrow(() -> is(getIncompleteInstance(new Object[0])).match(null));
	}

	@Test
	default void matchingEmptyDoubleArrayDoesNotThrow() {
		assertDoesNotThrow(() -> is(getIncompleteInstance(new double[0])).match(null));
	}

	@Test
	default void matchingEmptyFloatArrayDoesNotThrow() {
		assertDoesNotThrow(() -> is(getIncompleteInstance(new float[0])).match(null));
	}

	@Test
	default void matchingEmptyLongArrayDoesNotThrow() {
		assertDoesNotThrow(() -> is(getIncompleteInstance(new long[0])).match(null));
	}

	@Test
	default void matchingEmptyIntArrayDoesNotThrow() {
		assertDoesNotThrow(() -> is(getIncompleteInstance(new int[0])).match(null));
	}

	@Test
	default void matchingEmptyShortArrayDoesNotThrow() {
		assertDoesNotThrow(() -> is(getIncompleteInstance(new short[0])).match(null));
	}

	@Test
	default void matchingEmptyByteArrayDoesNotThrow() {
		assertDoesNotThrow(() -> is(getIncompleteInstance(new byte[0])).match(null));
	}

	@Test
	default void matchingEmptyCharArrayDoesNotThrow() {
		assertDoesNotThrow(() -> is(getIncompleteInstance(new char[0])).match(null));
	}

	@Test
	default void matchingEmptyBooleanArrayDoesNotThrow() {
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
