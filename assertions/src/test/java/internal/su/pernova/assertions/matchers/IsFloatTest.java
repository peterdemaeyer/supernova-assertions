package internal.su.pernova.assertions.matchers;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import static su.pernova.assertions.AssertionTestUtils.assertThrowsAssertionErrorWithMessage;
import static su.pernova.assertions.Assertions.assertThat;
import static su.pernova.assertions.Matchers.is;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.junit.jupiter.api.Test;

import su.pernova.assertions.MatcherContractTest;

class IsFloatTest implements MatcherContractTest {

	@Override
	public IsFloat getInstance() {
		return assertInstanceOf(IsFloat.class, is(065f));
	}

	@Test
	void isFloatMatchesDecimalNumber() {
		assertThat(Double.valueOf(5.2), is(5.2f));
		// Pick a float carefully so that we don't suffer from precision loss.
		assertThat(Float.valueOf(5.5f), is(5.5f));
		assertThat(BigDecimal.valueOf(5.31e2), is(5.31e2f));
	}

	@Test
	void isFloatMatchesIntegerNumber() {
		assertThat(Long.valueOf(-984985465165L), is(-984985465165f));
		assertThat(Integer.valueOf(-56), is(-56f));
		assertThat(Short.valueOf((short) -1000), is(-1000f));
		assertThat(Byte.valueOf((byte) 75), is(75f));
		assertThat(BigInteger.valueOf(-685464L), is(-685464f));
	}

	@Test
	void isFloatMatchesCharacter() {
		assertThat(Character.valueOf('T'), is(84f));
		// Hexadecimal character '\u5000' is decimal 20480.
		assertThat(Character.valueOf('\u5000'), is(20480f));
	}

	@Test
	void isFloatDoesNotMatchCharacter() {
		assertThrowsAssertionErrorWithMessage(
				() -> assertThat(Character.valueOf('z'), is(10f)),
				String.format("expected that subject is: 10.0%nbut was: 'z'")
		);
	}

	@Test
	void isFloatDoesNotMatchDouble() {
		assertThrowsAssertionErrorWithMessage(
				() -> assertThat(Double.valueOf(5d), is(6f)),
				String.format("expected that subject is: 6.0%nbut was: 5.0")
		);
	}

	@Test
	void isFloatDoesNotMatchFloat() {
		assertThrowsAssertionErrorWithMessage(
				() -> assertThat(Float.valueOf(5f), is(6f)),
				String.format("expected that subject is: 6.0%nbut was: 5.0")
		);
	}

	@Test
	void isFloatDoesNotMatchNull() {
		assertThrowsAssertionErrorWithMessage(
				() -> assertThat(null, is(6f)),
				String.format("expected that subject is: 6.0%nbut was: null")
		);
	}

	@Test
	void isFloatDoesNotMatchAnyObject() {
		final Object anyObject = new Object();
		assertThrowsAssertionErrorWithMessage(
				() -> assertThat(anyObject, is(6f)),
				String.format("expected that subject is: 6.0%nbut was: \"%s\"", anyObject)
		);
	}

	@Test
	void isFloatMatchesPositiveZeroWithNegativeZero() {
		assertDoesNotThrow(
				() -> assertThat(-0d, is(0d))
		);
		assertDoesNotThrow(
				() -> assertThat(-0f, is(0d))
		);
	}

	@Test
	void isFloatMatchesDoubleInfinity() {
		assertDoesNotThrow(
				() -> assertThat(Double.NEGATIVE_INFINITY, is(Float.NEGATIVE_INFINITY))
		);
		assertDoesNotThrow(
				() -> assertThat(Double.POSITIVE_INFINITY, is(Float.POSITIVE_INFINITY))
		);
	}

	@Test
	void stringValue() {
		assertEquals("is(-5.75)", is(-5.75f).toString());
	}
}
