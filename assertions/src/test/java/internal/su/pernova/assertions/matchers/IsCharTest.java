package internal.su.pernova.assertions.matchers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static su.pernova.assertions.AssertionTestUtils.assertThrowsAssertionErrorWithMessage;
import static su.pernova.assertions.Assertions.assertThat;
import static su.pernova.assertions.Matchers.is;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.junit.jupiter.api.Test;

class IsCharTest {

	@Test
	void isCharMatchesDecimalNumber() {
		assertThat(Double.valueOf(5.0)).is('\u0005');
		assertThat(Float.valueOf(8f)).is('\u0008');
		assertThat(BigDecimal.valueOf(321L)).is('\u0141');
	}

	@Test
	void isCharMatchesIntegerNumber() {
		assertThat(Long.valueOf(10245L)).is('\u2805');
		assertThat(Integer.valueOf(56)).is('\u0038');
		assertThat(Short.valueOf((short) 1000)).is('\u03E8');
		assertThat(Byte.valueOf((byte) 75)).is('\u004B');
		assertThat(BigInteger.valueOf(31001L)).is('\u7919');
	}

	@Test
	void isCharMatchesCharacter() {
		assertThat(Character.valueOf('T')).is('T');
		assertThat(Character.valueOf('\uFFFF')).is('\uFFFF');
	}

	@Test
	void isCharDoesNotMatchCharacter() {
		assertThrowsAssertionErrorWithMessage(
				() -> assertThat(Character.valueOf('a')).is('b'),
				String.format("expected that subject is: 'b'%nbut was: 'a'")
		);
	}

	@Test
	void isCharDoesNotMatchDouble() {
		assertThrowsAssertionErrorWithMessage(
				() -> assertThat(Double.valueOf(5.99999999999999)).is('\u0006'),
				String.format("expected that subject is: '\u0006'%nbut was: 5.99999999999999")
		);
	}

	@Test
	void isCharDoesNotMatchFloat() {
		assertThrowsAssertionErrorWithMessage(
				() -> assertThat(Float.valueOf(5f)).is('\u0006'),
				String.format("expected that subject is: '\u0006'%nbut was: 5.0")
		);
	}

	@Test
	void isCharDoesNotMatchNull() {
		assertThrowsAssertionErrorWithMessage(
				() -> assertThat(null).is('x'),
				String.format("expected that subject is: 'x'%nbut was: null")
		);
	}

	@Test
	void isCharDoesNotMatchAnyObject() {
		final Object anyObject = new Object();
		assertThrowsAssertionErrorWithMessage(
				() -> assertThat(anyObject).is('x'),
				String.format("expected that subject is: 'x'%nbut was: \"%s\"", anyObject)
		);
	}

	@Test
	void stringValue() {
		assertEquals("is: 'y'", is('y').toString());
	}
}
