package internal.su.pernova.assertions.matchers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static su.pernova.assertions.AssertionTestUtils.assertThrowsAssertionErrorWithMessage;
import static su.pernova.assertions.Assertions.assertThat;
import static su.pernova.assertions.Matchers.is;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.junit.jupiter.api.Test;

class IsIntTest {

	@Test
	void isIntMatchesDecimalNumber() {
		assertThat(Double.valueOf(5.0), is(5));
		// Pick a float carefully so that we don't suffer from precision loss.
		assertThat(Float.valueOf(-8f), is(-8));
		assertThat(BigDecimal.valueOf(321354L), is(321354));
	}

	@Test
	void isIntMatchesIntegerNumber() {
		assertThat(Long.valueOf(-98498565L), is(-98498565));
		assertThat(Integer.valueOf(-56), is(-56));
		assertThat(Short.valueOf((short) -1000), is(-1000));
		assertThat(Byte.valueOf((byte) 75), is(75));
		assertThat(BigInteger.valueOf(-685464L), is(-685464));
	}

	@Test
	void isIntMatchesCharacter() {
		assertThat(Character.valueOf('T'), is(84));
		// Hexadecimal character '\u5000' is decimal 20480.
		assertThat(Character.valueOf('\u5000'), is(20480));
	}

	@Test
	void isIntDoesNotMatchCharacter() {
		assertThrowsAssertionErrorWithMessage(
				() -> assertThat(Character.valueOf('z'), is(10)),
				String.format("expected that subject is: 10%nbut was: 'z'")
		);
	}

	@Test
	void isIntDoesNotMatchDouble() {
		assertThrowsAssertionErrorWithMessage(
				() -> assertThat(Double.valueOf(5.99999999999999), is(6)),
				String.format("expected that subject is: 6%nbut was: 5.99999999999999")
		);
	}

	@Test
	void isIntDoesNotMatchFloat() {
		assertThrowsAssertionErrorWithMessage(
				() -> assertThat(Float.valueOf(5f), is(6)),
				String.format("expected that subject is: 6%nbut was: 5.0")
		);
	}

	@Test
	void isIntDoesNotMatchNull() {
		assertThrowsAssertionErrorWithMessage(
				() -> assertThat(null, is(6)),
				String.format("expected that subject is: 6%nbut was: null")
		);
	}

	@Test
	void isIntDoesNotMatchAnyObject() {
		final Object anyObject = new Object();
		assertThrowsAssertionErrorWithMessage(() -> assertThat(anyObject, is(6)),
				String.format("expected that subject is: 6%nbut was: \"%s\"", anyObject));
	}

	@Test
	void stringValue() {
		assertEquals("is: 885668", is(885668).toString());
	}
}
