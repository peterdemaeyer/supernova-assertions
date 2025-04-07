package internal.su.pernova.assertions.matchers;

import static java.lang.Math.nextDown;
import static java.lang.Math.nextUp;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import static su.pernova.assertions.AssertionTestUtils.assertThrowsAssertionErrorWithMessage;
import static su.pernova.assertions.AssertionTestUtils.assertThrowsWithMessage;
import static su.pernova.assertions.Assertions.assertThat;
import static su.pernova.assertions.Matchers.closeTo;
import static su.pernova.assertions.Matchers.is;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicLong;

import org.junit.jupiter.api.Test;

import su.pernova.assertions.Matcher;

class CloseToTest {

	@Test
	void closeToDoesNotMatchNull() {
		assertThrowsAssertionErrorWithMessage(
				() -> assertThat(null, is(closeTo(1d, 0d))),
				String.format("expected that subject is close to: 1.0 ± 0.0 [1.0, 1.0]%nbut was: null")
		);
	}

	@Test
	void closeToDoesNotMatchAnyObject() {
		Object anyObject = new Object();
		assertThrowsAssertionErrorWithMessage(
				() -> assertThat(anyObject, is(closeTo(1d, 0d))),
				String.format("expected that subject is close to: 1.0 ± 0.0 [1.0, 1.0]%nbut was: \"%s\"", anyObject)
		);
	}

	@Test
	void closeToDoesNotMatchAnyNumber() {
		AtomicLong anyNumber = new AtomicLong(0L);
		assertThrowsWithMessage(
				IllegalArgumentException.class,
				() -> assertThat(anyNumber, is(closeTo(new AtomicLong(0L), new AtomicLong(0L)))),
				"illegal number class: java.util.concurrent.atomic.AtomicLong"
		);
	}

	@Test
	void closeToMatches() {
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
	void closeToDoesNotMatchOutOfBoundsValue() {
		assertThrowsAssertionErrorWithMessage(
				() -> assertThat(nextUp(1.1f), is(closeTo(1f, 0.1f))),
				String.format("expected that subject is close to: 1.0 ± 0.1 [0.9, 1.1]%nbut was: 1.1000001")
		);
		assertThrowsAssertionErrorWithMessage(
				() -> assertThat(nextDown(0.9), is(closeTo(1d, 0.1))),
				String.format("expected that subject is close to: 1.0 ± 0.1 [0.9, 1.1]%nbut was: 0.8999999999999999")
		);
	}

	@Test
	void closeToDoesNotMatchNan() {
		assertThrowsAssertionErrorWithMessage(
				() -> assertThat(Float.NaN, is(closeTo(1f, 0.1f))),
				String.format("expected that subject is close to: 1.0 ± 0.1 [0.9, 1.1]%nbut was: NaN")
		);
	}

	@Test
	void constructionThrows() {
		assertThrowsWithMessage(IllegalArgumentException.class, () -> closeTo(1, 0.1), "tolerance is not an instance of java.lang.Integer");
		assertThrowsWithMessage(NullPointerException.class, () -> closeTo(null, 0.1), "expected is null");
		assertThrowsWithMessage(NullPointerException.class, () -> closeTo(1.0, null), "tolerance is null");
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
	void closeToProvidesContext() {
		Matcher isCloseToOneOrTwo = is(closeTo(1., .01).or(2.));
		assertThat(.995, isCloseToOneOrTwo);
		assertThat(2.005, isCloseToOneOrTwo);
		assertThrowsAssertionErrorWithMessage(
				() -> assertThat(1.5, isCloseToOneOrTwo),
				"expected that subject is close to: 1.0 ± 0.01 [0.99, 1.01] or: 2.0",
						"but was: 1.5"
		);
	}
}
