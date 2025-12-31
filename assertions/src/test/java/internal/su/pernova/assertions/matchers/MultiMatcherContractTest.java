package internal.su.pernova.assertions.matchers;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static su.pernova.assertions.Matchers.is;

import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import su.pernova.assertions.ContextSensitiveMatcher;
import su.pernova.assertions.Matcher;
import su.pernova.assertions.MatcherContractTest;
import su.pernova.assertions.NoContextException;

/// @since 2.0.0
interface MultiMatcherContractTest extends MatcherContractTest {

	/// @param expectedValues expected values, not `null`.
	/// @return a context-sensitive matcher, not `null`.
	ContextSensitiveMatcher getInstance(Object... expectedValues);

	/// @param expectedValues expected values, not `null`.
	/// @return a context-sensitive matcher, not `null`.
	ContextSensitiveMatcher getInstance(double... expectedValues);

	/// @param expectedValues expected values, not `null`.
	/// @return a context-sensitive matcher, not `null`.
	ContextSensitiveMatcher getInstance(float... expectedValues);

	/// @param expectedValues expected values, not `null`.
	/// @return a context-sensitive matcher, not `null`.
	ContextSensitiveMatcher getInstance(long... expectedValues);

	/// @param expectedValues expected values, not `null`.
	/// @return a context-sensitive matcher, not `null`.
	ContextSensitiveMatcher getInstance(int... expectedValues);

	/// @param expectedValues expected values, not `null`.
	/// @return a context-sensitive matcher, not `null`.
	ContextSensitiveMatcher getInstance(short... expectedValues);

	/// @param expectedValues expected values, not `null`.
	/// @return a context-sensitive matcher, not `null`.
	ContextSensitiveMatcher getInstance(byte... expectedValues);

	/// @param expectedValues expected values, not `null`.
	/// @return a context-sensitive matcher, not `null`.
	ContextSensitiveMatcher getInstance(char... expectedValues);

	/// @param expectedValues expected values, not `null`.
	/// @return a context-sensitive matcher, not `null`.
	ContextSensitiveMatcher getInstance(boolean... expectedValues);

	@Test
	default void matchingObjectsThrowsWhenMatcherHasNoContext() {
		assertThrows(NoContextException.class, () -> getInstance(this, this).match(this));
	}

	@Test
	default void matchingDoublesThrowsWhenMatcherHasNoContext() {
		assertThrows(NoContextException.class, () -> getInstance(new double[] { 0d, 0d }).match(0d));
	}

	@Test
	default void matchingFloatsThrowsWhenMatcherHasNoContext() {
		assertThrows(NoContextException.class, () -> getInstance(new float[] { 0f, 0f }).match(0f));
	}

	@Test
	default void matchingLongsThrowsWhenMatcherHasNoContext() {
		assertThrows(NoContextException.class, () -> getInstance(new long[] { 0L, 0L }).match(0L));
	}

	@Test
	default void matchingIntegersThrowsWhenMatcherHasNoContext() {
		assertThrows(NoContextException.class, () -> getInstance(new int[] { 0, 0 }).match(0));
	}

	@Test
	default void matchingShortsThrowsWhenMatcherHasNoContext() {
		assertThrows(NoContextException.class, () -> getInstance(new short[] { 0, 0 }).match((short) 0));
	}

	@Test
	default void matchingBytesThrowsWhenMatcherHasNoContext() {
		assertThrows(NoContextException.class, () -> getInstance(new byte[] { 0, 0 }).match((byte) 0));
	}

	@Test
	default void matchingCharactersThrowsWhenMatcherHasNoContext() {
		assertThrows(NoContextException.class, () -> getInstance(new char[] { '0', '0' }).match('0'));
	}

	@Test
	default void matchingBooleansThrowsWhenMatcherHasNoContext() {
		assertThrows(NoContextException.class, () -> getInstance(new boolean[] { false, false }).match(false));
	}

	@Test
	default void constructionThrowsWhenObjectArrayIsNull() {
		assertEquals("array of expected values is null",
				assertThrows(NullPointerException.class,
						() -> getInstance((Object[]) null))
						.getMessage());
	}

	@Test
	default void constructionThrowsWhenDoubleArrayIsNull() {
		assertEquals("array of expected values is null",
				assertThrows(NullPointerException.class,
						() -> getInstance((double[]) null))
						.getMessage());
	}

	@Test
	default void constructionThrowsWhenFloatArrayIsNull() {
		assertEquals("array of expected values is null",
				assertThrows(NullPointerException.class,
						() -> getInstance((float[]) null))
						.getMessage());
	}

	@Test
	default void constructionThrowsWhenLongArrayIsNull() {
		assertEquals("array of expected values is null",
				assertThrows(NullPointerException.class,
						() -> getInstance((long[]) null))
						.getMessage());
	}

	@Test
	default void constructionThrowsWhenIntegerArrayIsNull() {
		assertEquals("array of expected values is null",
				assertThrows(NullPointerException.class,
						() -> getInstance((int[]) null))
						.getMessage());
	}

	@Test
	default void constructionThrowsWhenShortArrayIsNull() {
		assertEquals("array of expected values is null",
				assertThrows(NullPointerException.class,
						() -> getInstance((short[]) null))
						.getMessage());
	}

	@Test
	default void constructionThrowsWhenByteArrayIsNull() {
		assertEquals("array of expected values is null",
				assertThrows(NullPointerException.class,
						() -> getInstance((byte[]) null))
						.getMessage());
	}

	@Test
	default void constructionThrowsWhenCharacterArrayIsNull() {
		assertEquals("array of expected values is null",
				assertThrows(NullPointerException.class,
						() -> getInstance((char[]) null))
						.getMessage());
	}

	@Test
	default void constructionThrowsWhenBooleanArrayIsNull() {
		assertEquals("array of expected values is null",
				assertThrows(NullPointerException.class,
						() -> getInstance((boolean[]) null))
						.getMessage());
	}

	@ParameterizedTest
	@ValueSource(ints = { 0, 1, 2, 3 })
	default void matchingObjectsWithNullDoesNotThrow(int size) {
		assertDoesNotThrow(() -> is(getInstance(new Object[size])).match(null));
	}

	@ParameterizedTest
	@ValueSource(ints = { 0, 1, 2, 3 })
	default void matchingDoublesWithNullDoesNotThrow(int size) {
		assertDoesNotThrow(() -> is(getInstance(new double[size])).match(null));
	}

	@ParameterizedTest
	@ValueSource(ints = { 0, 1, 2, 3 })
	default void matchingFloatsWithNullDoesNotThrow(int size) {
		assertDoesNotThrow(() -> is(getInstance(new float[size])).match(null));
	}

	@ParameterizedTest
	@ValueSource(ints = { 0, 1, 2, 3 })
	default void matchingLongsWithNullDoesNotThrow(int size) {
		assertDoesNotThrow(() -> is(getInstance(new long[size])).match(null));
	}

	@ParameterizedTest
	@ValueSource(ints = { 0, 1, 2, 3 })
	default void matchingIntsWithNullDoesNotThrow(int size) {
		assertDoesNotThrow(() -> is(getInstance(new int[size])).match(null));
	}

	@ParameterizedTest
	@ValueSource(ints = { 0, 1, 2, 3 })
	default void matchingShortsWithNullDoesNotThrow(int size) {
		assertDoesNotThrow(() -> is(getInstance(new short[size])).match(null));
	}

	@ParameterizedTest
	@ValueSource(ints = { 0, 1, 2, 3 })
	default void matchingBytesWithNullDoesNotThrow(int size) {
		assertDoesNotThrow(() -> is(getInstance(new byte[size])).match(null));
	}

	@ParameterizedTest
	@ValueSource(ints = { 0, 1, 2, 3 })
	default void matchingCharsWithNullDoesNotThrow(int size) {
		assertDoesNotThrow(() -> is(getInstance(new char[size])).match(null));
	}

	@ParameterizedTest
	@ValueSource(ints = { 0, 1, 2, 3 })
	default void matchingBooleansWithNullDoesNotThrow(int size) {
		assertDoesNotThrow(() -> is(getInstance(new boolean[size])).match(null));
	}

	@Test
	default void orPropagatesLackOfMatcherContext() {
		final Matcher incompleteMatcher = getInstance("a", "b").or(getInstance("c", "d"));
		assertThrows(IllegalStateException.class, () -> incompleteMatcher.match("e"));
		assertEquals("...", incompleteMatcher.toString());
		final Matcher matcher = is(incompleteMatcher); // Completes the incomplete matcher
		assertDoesNotThrow(() -> matcher.match("e"));
		final Pattern pattern = Pattern.compile("is\\{or\\{\\w+\\{i̶s̶O̶b̶j̶e̶c̶t̶\\(a\\)}\\{i̶s̶O̶b̶j̶e̶c̶t̶\\(b\\)}}\\{\\w+\\{i̶s̶O̶b̶j̶e̶c̶t̶\\(c\\)}\\{i̶s̶O̶b̶j̶e̶c̶t̶\\(d\\)}}}");
		final String stringValue = matcher.toString();
		assertTrue(pattern.matcher(stringValue).matches(), stringValue);
	}

	@Test
	default void orPropagatesMatcherContext() {
		// The difference is in the placement of the parenthesis.
		// This time, the "or" is called on the COMPLETED matcher.
		final Matcher matcher = is(getInstance("a", "b")).or(getInstance("c", "d"));
		assertDoesNotThrow(() -> matcher.match("e"));
		final Pattern pattern = Pattern.compile("or\\{is\\{\\w+\\{i̶s̶O̶b̶j̶e̶c̶t̶\\(a\\)}\\{i̶s̶O̶b̶j̶e̶c̶t̶\\(b\\)}}}\\{\\w+\\{i̶s̶O̶b̶j̶e̶c̶t̶\\(c\\)}\\{i̶s̶O̶b̶j̶e̶c̶t̶\\(d\\)}}");
		final String stringValue = matcher.toString();
		assertTrue(pattern.matcher(stringValue).matches(), stringValue);
	}

	@Test
	default void andPropagatesLackOfMatcherContext() {
		final Matcher incompleteMatcher = getInstance("a", "b").and(getInstance("c", "d"));
		assertThrows(IllegalStateException.class, () -> incompleteMatcher.match("e"));
		assertEquals("...", incompleteMatcher.toString());
		final Matcher matcher = is(incompleteMatcher); // Complete the incomplete matcher
		assertDoesNotThrow(() -> matcher.match("e"));
		final Pattern pattern = Pattern.compile("is\\{and\\{\\w+\\{i̶s̶O̶b̶j̶e̶c̶t̶\\(a\\)}\\{i̶s̶O̶b̶j̶e̶c̶t̶\\(b\\)}}\\{\\w+\\{i̶s̶O̶b̶j̶e̶c̶t̶\\(c\\)}\\{i̶s̶O̶b̶j̶e̶c̶t̶\\(d\\)}}}");
		final String stringValue = matcher.toString();
		assertTrue(pattern.matcher(stringValue).matches(), stringValue);
	}

	@Test
	default void andPropagatesMatcherContext() {
		// The difference is in the placement of the parenthesis.
		// This time, the "or" is called on the COMPLETED matcher.
		final Matcher matcher = is(getInstance("a", "b")).and(getInstance("c", "d"));
		assertDoesNotThrow(() -> matcher.match("e"));
		final Pattern pattern = Pattern.compile("and\\{is\\{\\w+\\{i̶s̶O̶b̶j̶e̶c̶t̶\\(a\\)}\\{i̶s̶O̶b̶j̶e̶c̶t̶\\(b\\)}}}\\{\\w+\\{i̶s̶O̶b̶j̶e̶c̶t̶\\(c\\)}\\{i̶s̶O̶b̶j̶e̶c̶t̶\\(d\\)}}");
		final String stringValue = matcher.toString();
		assertTrue(pattern.matcher(stringValue).matches(), stringValue);
	}
}
