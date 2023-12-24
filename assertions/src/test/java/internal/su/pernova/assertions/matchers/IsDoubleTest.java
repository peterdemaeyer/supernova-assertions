package internal.su.pernova.assertions.matchers;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static su.pernova.assertions.AssertionTestUtils.assertThrowsAssertionErrorWithMessage;
import static su.pernova.assertions.Assertions.assertThat;
import static su.pernova.assertions.Matchers.is;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.junit.jupiter.api.Test;

class IsDoubleTest {

	@Test
	void isDoubleMatchesDecimalNumber() {
		assertThat(Double.valueOf(5.2), is(5.2d));
		// Pick a float carefully so that we don't suffer from precision loss.
		assertThat(Float.valueOf(5.5f), is(5.5d));
		assertThat(BigDecimal.valueOf(5.31e123), is(5.31e123d));
	}

	@Test
	void isDoubleMatchesIntegerNumber() {
		assertThat(Long.valueOf(-984985465165L), is(-984985465165d));
		assertThat(Integer.valueOf(-56), is(-56d));
		assertThat(Short.valueOf((short) -1000), is(-1000d));
		assertThat(Byte.valueOf((byte) 75), is(75d));
		assertThat(BigInteger.valueOf(-685464L), is(-685464d));
	}

	@Test
	void isDoubleMatchesCharacter() {
		assertThat(Character.valueOf('T'), is(84d));
		// Hexadecimal character '\u5000' is decimal 20480.
		assertThat(Character.valueOf('\u5000'), is(20480d));
	}

	@Test
	void isDoubleDoesNotMatchCharacter() {
		assertThrowsAssertionErrorWithMessage(
				() -> assertThat(Character.valueOf('z'), is(10d)),
				String.format("expected that subject is: 10.0%nbut was: 'z'")
		);
	}

	@Test
	void isDoubleDoesNotMatchDouble() {
		assertThrowsAssertionErrorWithMessage(
				() -> assertThat(Double.valueOf(5d), is(6d)),
				String.format("expected that subject is: 6.0%nbut was: 5.0")
		);
	}

	@Test
	void isDoubleDoesNotMatchFloat() {
		assertThrowsAssertionErrorWithMessage(
				() -> assertThat(Float.valueOf(5f), is(6d)),
				String.format("expected that subject is: 6.0%nbut was: 5.0")
		);
	}

	@Test
	void isDoubleDoesNotMatchNull() {
		assertThrowsAssertionErrorWithMessage(
				() -> assertThat(null, is(6d)),
				String.format("expected that subject is: 6.0%nbut was: null")
		);
	}

	@Test
	void isDoubleDoesNotMatchAnyObject() {
		final Object anyObject = new Object();
		assertThrowsAssertionErrorWithMessage(
				() -> assertThat(anyObject, is(6d)),
				String.format("expected that subject is: 6.0%nbut was: \"%s\"", anyObject)
		);
	}

	@Test
	void isDoubleMatchesPositiveZeroWithNegativeZero() {
		assertDoesNotThrow(
				() -> assertThat(-0d, is(0d))
		);
		assertDoesNotThrow(
				() -> assertThat(-0f, is(0d))
		);
	}

	@Test
	void isDoubleMatchesFloatInfinity() {
		assertDoesNotThrow(
				() -> assertThat(Float.NEGATIVE_INFINITY, is(Double.NEGATIVE_INFINITY))
		);
		assertDoesNotThrow(
				() -> assertThat(Float.POSITIVE_INFINITY, is(Double.POSITIVE_INFINITY))
		);
	}

	@Test
	void stringValue() {
		assertEquals("is: 5.7", is(5.7).toString());
	}
}
