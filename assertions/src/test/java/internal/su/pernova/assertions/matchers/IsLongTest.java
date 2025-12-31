package internal.su.pernova.assertions.matchers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import static su.pernova.assertions.AssertionTestUtils.assertThrowsAssertionErrorWithMessage;
import static su.pernova.assertions.Assertions.assertThat;
import static su.pernova.assertions.Matchers.is;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.junit.jupiter.api.Test;

import su.pernova.assertions.MatcherContractTest;

class IsLongTest implements MatcherContractTest {

	@Override
	public IsLong getInstance() {
		return assertInstanceOf(IsLong.class, is(456L));
	}

	@Test
	void isLongMatchesDecimalNumber() {
		assertThat(Double.valueOf(5.0), is(5L));
		// Pick a float carefully so that we don't suffer from precision loss.
		assertThat(Float.valueOf(-8f), is(-8L));
		assertThat(BigDecimal.valueOf(321354L), is(321354L));
	}

	@Test
	void isLongMatchesIntegerNumber() {
		assertThat(Long.valueOf(-984985465165L), is(-984985465165L));
		assertThat(Integer.valueOf(-56), is(-56L));
		assertThat(Short.valueOf((short) -1000), is(-1000L));
		assertThat(Byte.valueOf((byte) 75), is(75L));
		assertThat(BigInteger.valueOf(-685464L), is(-685464L));
	}

	@Test
	void isLongMatchesCharacter() {
		assertThat(Character.valueOf('T'), is(84L));
		// Hexadecimal character '\u5000' is decimal 20480.
		assertThat(Character.valueOf('\u5000'), is(20480L));
	}

	@Test
	void isLongDoesNotMatchCharacter() {
		assertThrowsAssertionErrorWithMessage(
				() -> assertThat(Character.valueOf('z'), is(10L)),
				String.format("expected that subject is: 10%nbut was: 'z'")
		);
	}

	@Test
	void isLongDoesNotMatchDouble() {
		assertThrowsAssertionErrorWithMessage(
				() -> assertThat(Double.valueOf(5.99999999999999), is(6L)),
				String.format("expected that subject is: 6%nbut was: 5.99999999999999")
		);
	}

	@Test
	void isLongDoesNotMatchFloat() {
		assertThrowsAssertionErrorWithMessage(
				() -> assertThat(Float.valueOf(5f), is(6L)),
				String.format("expected that subject is: 6%nbut was: 5.0")
		);
	}

	@Test
	void isLongDoesNotMatchNull() {
		assertThrowsAssertionErrorWithMessage(
				() -> assertThat(null, is(6L)),
				String.format("expected that subject is: 6%nbut was: null")
		);
	}

	@Test
	void isLongDoesNotMatchAnyObject() {
		final Object anyObject = new Object();
		assertThrowsAssertionErrorWithMessage(
				() -> assertThat(anyObject, is(6L)),
				String.format("expected that subject is: 6%nbut was: \"%s\"", anyObject)
		);
	}

	@Test
	void stringValue() {
		assertEquals("is(8856688888851863511)", is(8856688888851863511L).toString());
	}
}
