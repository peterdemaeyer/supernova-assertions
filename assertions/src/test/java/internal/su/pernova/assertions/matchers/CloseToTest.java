package internal.su.pernova.assertions.matchers;

import static java.lang.Math.nextDown;
import static java.lang.Math.nextUp;
import static java.lang.String.format;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static su.pernova.assertions.AssertionTestUtils.assertThrowsWithMessage;
import static su.pernova.assertions.Assertions.assertThat;
import static su.pernova.assertions.Matchers.anyOf;
import static su.pernova.assertions.Matchers.closeTo;
import static su.pernova.assertions.Matchers.is;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicLong;

import org.junit.jupiter.api.Test;

import su.pernova.assertions.Matcher;
import su.pernova.assertions.MatcherContractTest;

class CloseToTest implements MatcherContractTest {

	@Override
	public CloseTo getInstance() {
		return assertInstanceOf(CloseTo.class, closeTo(1d, .01));
	}

	@Test
	void nullDoesNotMatch() {
		assertEquals(format("expected that subject is close to: 1.0 ± 0.0 [1.0, 1.0]%nbut was: null"),
				assertThrows(AssertionError.class,
						() -> assertThat(null, is(closeTo(1d, 0d)))).getMessage());
	}

	@Test
	void objectDoesNotMatch() {
		final Object object = new Object();
		assertEquals(format("expected that subject is close to: 1.0 ± 0.0 [1.0, 1.0]%nbut was: \"%s\"", object),
				assertThrows(AssertionError.class,
						() -> assertThat(object, is(closeTo(1d, 0d)))).getMessage());
	}

	@Test
	void illegalNumberClass() {
		final AtomicLong number = new AtomicLong(0L);
		assertEquals("illegal number class: java.util.concurrent.atomic.AtomicLong",
				assertThrows(IllegalArgumentException.class,
						() -> is(closeTo(number, number))).getMessage());
	}

	@Test
	void toleranceNotInstanceOfValueClass() {
		assertEquals("tolerance is not an instance of java.lang.Long",
				assertThrows(IllegalArgumentException.class,
						() -> is(closeTo(0L, 0d))).getMessage());
		assertEquals("tolerance is not an instance of java.lang.Double",
				assertThrows(IllegalArgumentException.class,
						() -> is(closeTo(0d, 0L))).getMessage());
	}

	@Test
	void valueMatchesWithinTolerance() {
		assertThat(1d, is(closeTo(1d, 0.1)));
		assertThat(1.1, is(closeTo(1d, 0.1)));
		assertThat(0.9, is(closeTo(1d, 0.1)));
		assertThat(5L, is(closeTo(0L, 10L)));
		assertThat(5, is(closeTo(5, 0)));
		assertThat((short) 7, is(closeTo((short) 9, (short) 2)));
		assertThat((byte) 2, is(closeTo((byte) 0, (byte) 4)));
		assertThat(BigInteger.valueOf(0L), is(closeTo(BigInteger.valueOf(0L), BigInteger.valueOf(1L))));
		assertThat(BigDecimal.valueOf(0d), is(closeTo(BigDecimal.valueOf(0d), BigDecimal.valueOf(0d))));
		assertThat(1f, is(closeTo(1f, 0f)));
	}

	@Test
	void valueDoesNotMatchWhenOutOfBounds() {
		assertEquals(format("expected that subject is close to: 1.0 ± 0.1 [0.9, 1.1]%nbut was: 1.1000001"),
				assertThrows(AssertionError.class,
						() -> assertThat(nextUp(1.1f), is(closeTo(1f, 0.1f)))).getMessage());
		assertEquals(format("expected that subject is close to: 1.0 ± 0.1 [0.9, 1.1]%nbut was: 0.8999999999999999"),
				assertThrows(AssertionError.class,
						() -> assertThat(nextDown(0.9), is(closeTo(1d, 0.1)))).getMessage());
	}

	@Test
	void nanDoesNotMatchFloat() {
		assertEquals(format("expected that subject is close to: 1.0 ± 0.1 [0.9, 1.1]%nbut was: NaN"),
				assertThrows(AssertionError.class,
						() -> assertThat(Float.NaN, is(closeTo(1f, 0.1f)))).getMessage());
	}

	@Test
	void nanDoesNotMatchDouble() {
		assertEquals(format("expected that subject is close to: 1.0 ± 0.1 [0.9, 1.1]%nbut was: NaN"),
				assertThrows(AssertionError.class,
						() -> assertThat(Double.NaN, is(closeTo(1d, 0.1d)))).getMessage());
	}

	@Test
	void constructionThrows() {
		assertThrowsWithMessage(IllegalArgumentException.class,
				() -> closeTo(1, 0.1),
				"tolerance is not an instance of java.lang.Integer");
		assertThrowsWithMessage(NullPointerException.class,
				() -> closeTo(null, 0.1),
				"expected value is null");
		assertThrowsWithMessage(NullPointerException.class,
				() -> closeTo(1.0, null),
				"tolerance is null");
	}

	@Test
	void closeToIsRobustAgainstOutOfBoundsValues() {
		assertDoesNotThrow(() -> assertThat(Long.MAX_VALUE, is(closeTo(Long.MAX_VALUE, 1L))));
		assertDoesNotThrow(() -> assertThat(Integer.MAX_VALUE, is(closeTo(Integer.MAX_VALUE, 1))));
		assertDoesNotThrow(() -> assertThat(Short.MAX_VALUE, is(closeTo(Short.MAX_VALUE, (short) 1))));
		assertDoesNotThrow(() -> assertThat(Byte.MAX_VALUE, is(closeTo(Byte.MAX_VALUE, (byte) 1))));
		assertDoesNotThrow(() -> assertThat(Double.NEGATIVE_INFINITY, is(closeTo(Double.NEGATIVE_INFINITY, 1d))));
		assertDoesNotThrow(() -> assertThat(Float.POSITIVE_INFINITY, is(closeTo(Float.POSITIVE_INFINITY, 1f))));
		assertDoesNotThrow(() -> assertThat(Long.MIN_VALUE, is(closeTo(Long.MIN_VALUE, 1L))));
		assertDoesNotThrow(() -> assertThat(Integer.MIN_VALUE, is(closeTo(Integer.MIN_VALUE, 1))));
		assertDoesNotThrow(() -> assertThat(Short.MIN_VALUE, is(closeTo(Short.MIN_VALUE, (short) 1))));
		assertDoesNotThrow(() -> assertThat(Byte.MIN_VALUE, is(closeTo(Byte.MIN_VALUE, (byte) 1))));
	}

	@Test
	void closeToProvidesContextForBiMatcher() {
		Matcher isCloseToOneOrTwo = is(closeTo(1., .01).or(2.));
		assertThat(.995, isCloseToOneOrTwo);
		assertThat(2.005, isCloseToOneOrTwo);
		assertEquals(format("expected that subject is close to: 1.0 ± 0.01 [0.99, 1.01] or: 2.0%nbut was: 1.5"),
				assertThrows(AssertionError.class,
						() -> assertThat(1.5, isCloseToOneOrTwo)).getMessage());
	}
}
