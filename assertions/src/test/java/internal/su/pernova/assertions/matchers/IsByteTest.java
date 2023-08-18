package internal.su.pernova.assertions.matchers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static su.pernova.assertions.AssertionTestUtils.assertThrowsAssertionErrorWithMessage;
import static su.pernova.assertions.Assertions.assertThat;
import static su.pernova.assertions.Matchers.is;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.junit.jupiter.api.Test;

import su.pernova.assertions.AssertionTestUtils;
import su.pernova.assertions.Matchers;

class IsByteTest {

	@Test
	void isByteMatchesDecimalNumber() {
		assertThat(Double.valueOf(5.0)).is((byte) 5);
		// Pick a float carefully so that we don't suffer from precision loss.
		assertThat(Float.valueOf(-8f)).is((byte) -8);
		assertThat(BigDecimal.valueOf(321354L)).is(321354);
	}

	@Test
	void isByteMatchesIntegerNumber() {
		assertThat(Long.valueOf(-98498565L)).is((byte) -98498565);
		assertThat(Integer.valueOf(-56)).is((byte) -56);
		assertThat(Short.valueOf((byte) -1000)).is((byte) -1000);
		assertThat(Byte.valueOf((byte) 75)).is((byte) 75);
		assertThat(BigInteger.valueOf(-685464L)).is((byte) -685464);
	}

	@Test
	void isByteMatchesCharacter() {
		assertThat(Character.valueOf('T')).is((byte) 84);
		// Hexadecimal character '\u007F' is decimal 127.
		assertThat(Character.valueOf('\u007F')).is((byte) 127);
	}

	@Test
	void isByteDoesNotMatchCharacter() {
		assertThrowsAssertionErrorWithMessage(
				() -> assertThat(Character.valueOf('\u00FF')).is((byte) -1), 
				String.format("expected that subject is: -1%nbut was: '\u00FF'")
		);
	}

	@Test
	void isByteDoesNotMatchDouble() {
		assertThrowsAssertionErrorWithMessage(
				() -> assertThat(Double.valueOf(5.99999999999999)).is((byte) 6),
				String.format("expected that subject is: 6%nbut was: 5.99999999999999")
		);
	}

	@Test
	void isByteDoesNotMatchFloat() {
		assertThrowsAssertionErrorWithMessage(
				() -> assertThat(Float.valueOf(5f)).is((byte) 6),
				String.format("expected that subject is: 6%nbut was: 5.0")
		);
	}

	@Test
	void isByteDoesNotMatchNull() {
		assertThrowsAssertionErrorWithMessage(
				() -> assertThat(null).is((byte) 6),
				String.format("expected that subject is: 6%nbut was: null")
		);
	}

	@Test
	void isByteDoesNotMatchAnyObject() {
		final Object anyObject = new Object();
		assertThrowsAssertionErrorWithMessage(
				() -> assertThat(anyObject).is((byte) 6),
				String.format("expected that subject is: 6%nbut was: \"%s\"", anyObject)
		);
	}

	@Test
	void stringValue() {
		assertEquals("is: 77", is((byte) 77).toString());
	}
}
