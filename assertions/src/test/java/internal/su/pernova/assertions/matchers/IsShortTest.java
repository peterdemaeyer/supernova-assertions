package internal.su.pernova.assertions.matchers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static su.pernova.assertions.AssertionTestUtils.assertThrowsAssertionErrorWithMessage;
import static su.pernova.assertions.Assertions.assertThat;
import static su.pernova.assertions.Matchers.is;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.junit.jupiter.api.Test;

class IsShortTest {

	@Test
	void isShortMatchesDecimalNumber() {
		assertThat(Double.valueOf(5.0), is((short) 5));
		// Pick a float carefully so that we don't suffer from precision loss.
		assertThat(Float.valueOf(-8f), is((short) -8));
		assertThat(BigDecimal.valueOf(321354L), is(321354));
	}

	@Test
	void isShortMatchesIntegerNumber() {
		assertThat(Long.valueOf(-98498565L), is((short) -98498565));
		assertThat(Integer.valueOf(-56), is((short) -56));
		assertThat(Short.valueOf((short) -1000), is((short) -1000));
		assertThat(Byte.valueOf((byte) 75), is((short) 75));
		assertThat(BigInteger.valueOf(-685464L), is((short) -685464));
	}

	@Test
	void isShortMatchesCharacter() {
		assertThat(Character.valueOf('T'), is((short) 84));
		// Hexadecimal character '\u5000' is decimal 20480.
		assertThat(Character.valueOf('\u5000'), is((short) 20480));
	}

	@Test
	void isShortDoesNotMatchCharacter() {
		assertThrowsAssertionErrorWithMessage(
				() -> assertThat(Character.valueOf('z'), is((short) 10)),
				String.format("expected that subject is: 10%nbut was: 'z'")
		);
	}

	@Test
	void isShortDoesNotMatchDouble() {
		assertThrowsAssertionErrorWithMessage(
				() -> assertThat(Double.valueOf(5.99999999999999), is((short) 6)),
				String.format("expected that subject is: 6%nbut was: 5.99999999999999")
		);
	}

	@Test
	void isShortDoesNotMatchFloat() {
		assertThrowsAssertionErrorWithMessage(
				() -> assertThat(Float.valueOf(5f), is((short) 6)),
				String.format("expected that subject is: 6%nbut was: 5.0")
		);
	}

	@Test
	void isShortDoesNotMatchNull() {
		assertThrowsAssertionErrorWithMessage(
				() -> assertThat(null, is((short) 6)),
				String.format("expected that subject is: 6%nbut was: null")
		);
	}

	@Test
	void isShortDoesNotMatchAnyObject() {
		final Object anyObject = new Object();
		assertThrowsAssertionErrorWithMessage(
				() -> assertThat(anyObject, is((short) 6)),
				String.format("expected that subject is: 6%nbut was: \"%s\"", anyObject)
		);
	}

	@Test
	void stringValue() {
		assertEquals("is: 20000", is((short) 20000).toString());
	}
}
