package internal.su.pernova.assertions.matchers;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static internal.su.pernova.assertions.matchers.MultiMatcherTestUtils.contextualize;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import su.pernova.assertions.Context;
import su.pernova.assertions.Matcher;
import su.pernova.assertions.MatcherContractTest;

/// @since 2.0.0
interface MultiMatcherContractTest extends MatcherContractTest {

	@Override
	default Matcher getContextualizedInstance(Context context) {
		return contextualize(getInstance(), context);
	}

	/// @param expectedValues expected values, not `null`.
	/// @return a context-sensitive matcher, not `null`.
	MultiMatcher getInstance(Object... expectedValues);

	/// @param expectedValues expected values, not `null`.
	/// @return a context-sensitive matcher, not `null`.
	MultiMatcher getInstance(double... expectedValues);

	/// @param expectedValues expected values, not `null`.
	/// @return a context-sensitive matcher, not `null`.
	MultiMatcher getInstance(float... expectedValues);

	/// @param expectedValues expected values, not `null`.
	/// @return a context-sensitive matcher, not `null`.
	MultiMatcher getInstance(long... expectedValues);

	/// @param expectedValues expected values, not `null`.
	/// @return a context-sensitive matcher, not `null`.
	MultiMatcher getInstance(int... expectedValues);

	/// @param expectedValues expected values, not `null`.
	/// @return a context-sensitive matcher, not `null`.
	MultiMatcher getInstance(short... expectedValues);

	/// @param expectedValues expected values, not `null`.
	/// @return a context-sensitive matcher, not `null`.
	MultiMatcher getInstance(byte... expectedValues);

	/// @param expectedValues expected values, not `null`.
	/// @return a context-sensitive matcher, not `null`.
	MultiMatcher getInstance(char... expectedValues);

	/// @param expectedValues expected values, not `null`.
	/// @return a context-sensitive matcher, not `null`.
	MultiMatcher getInstance(boolean... expectedValues);

	@Test
	default void matchingObjectsThrowsWhenMatcherHasNoContext() {
		assertThrows(IllegalStateException.class, () -> getInstance(this, this).match(this));
	}

	@Test
	default void matchingDoublesThrowsWhenMatcherHasNoContext() {
		assertThrows(IllegalStateException.class, () -> getInstance(new double[] { 0d, 0d }).match(0d));
	}

	@Test
	default void matchingFloatsThrowsWhenMatcherHasNoContext() {
		assertThrows(IllegalStateException.class, () -> getInstance(new float[] { 0f, 0f }).match(0f));
	}

	@Test
	default void matchingLongsThrowsWhenMatcherHasNoContext() {
		assertThrows(IllegalStateException.class, () -> getInstance(new long[] { 0L, 0L }).match(0L));
	}

	@Test
	default void matchingIntegersThrowsWhenMatcherHasNoContext() {
		assertThrows(IllegalStateException.class, () -> getInstance(new int[] { 0, 0 }).match(0));
	}

	@Test
	default void matchingShortsThrowsWhenMatcherHasNoContext() {
		assertThrows(IllegalStateException.class, () -> getInstance(new short[] { 0, 0 }).match((short) 0));
	}

	@Test
	default void matchingBytesThrowsWhenMatcherHasNoContext() {
		assertThrows(IllegalStateException.class, () -> getInstance(new byte[] { 0, 0 }).match((byte) 0));
	}

	@Test
	default void matchingCharactersThrowsWhenMatcherHasNoContext() {
		assertThrows(IllegalStateException.class, () -> getInstance(new char[] { '0', '0' }).match('0'));
	}

	@Test
	default void matchingBooleansThrowsWhenMatcherHasNoContext() {
		assertThrows(IllegalStateException.class, () -> getInstance(new boolean[] { false, false }).match(false));
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
		final Matcher matcher = contextualize(getInstance(new Object[size]));
		assertDoesNotThrow(() -> matcher.match(null));
	}

	@ParameterizedTest
	@ValueSource(ints = { 0, 1, 2, 3 })
	default void matchingDoublesWithNullDoesNotThrow(int size) {
		final Matcher matcher = contextualize(getInstance(new double[size]));
		assertDoesNotThrow(() -> matcher.match(null));
	}

	@ParameterizedTest
	@ValueSource(ints = { 0, 1, 2, 3 })
	default void matchingFloatsWithNullDoesNotThrow(int size) {
		final Matcher matcher = contextualize(getInstance(new float[size]));
		assertDoesNotThrow(() -> matcher.match(null));
	}

	@ParameterizedTest
	@ValueSource(ints = { 0, 1, 2, 3 })
	default void matchingLongsWithNullDoesNotThrow(int size) {
		final Matcher matcher = contextualize(getInstance(new long[size]));
		assertDoesNotThrow(() -> matcher.match(null));
	}

	@ParameterizedTest
	@ValueSource(ints = { 0, 1, 2, 3 })
	default void matchingIntsWithNullDoesNotThrow(int size) {
		final Matcher matcher = contextualize(getInstance(new int[size]));
		assertDoesNotThrow(() -> matcher.match(null));
	}

	@ParameterizedTest
	@ValueSource(ints = { 0, 1, 2, 3 })
	default void matchingShortsWithNullDoesNotThrow(int size) {
		final Matcher matcher = contextualize(getInstance(new short[size]));
		assertDoesNotThrow(() -> matcher.match(null));
	}

	@ParameterizedTest
	@ValueSource(ints = { 0, 1, 2, 3 })
	default void matchingBytesWithNullDoesNotThrow(int size) {
		final Matcher matcher = contextualize(getInstance(new byte[size]));
		assertDoesNotThrow(() -> matcher.match(null));
	}

	@ParameterizedTest
	@ValueSource(ints = { 0, 1, 2, 3 })
	default void matchingCharsWithNullDoesNotThrow(int size) {
		final Matcher matcher = contextualize(getInstance(new char[size]));
		assertDoesNotThrow(() -> matcher.match(null));
	}

	@ParameterizedTest
	@ValueSource(ints = { 0, 1, 2, 3 })
	default void matchingBooleansWithNullDoesNotThrow(int size) {
		final Matcher matcher = contextualize(getInstance(new boolean[size]));
		assertDoesNotThrow(() -> matcher.match(null));
	}
}
