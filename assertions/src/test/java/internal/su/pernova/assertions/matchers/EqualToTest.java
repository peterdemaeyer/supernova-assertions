package internal.su.pernova.assertions.matchers;

import static java.lang.Double.NaN;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static su.pernova.assertions.AssertionTestUtils.assertThrowsAssertionErrorWithMessage;
import static su.pernova.assertions.Assertions.assertThat;
import static su.pernova.assertions.Matchers.equalTo;

import java.util.Date;

import org.junit.jupiter.api.Test;

class EqualToTest {

	@Test
	void equalToMatchesNull() {
		assertDoesNotThrow(
				() -> assertThat(null).is(equalTo(null))
		);
	}

	@Test
	void equalToDoesNotMatchNull() {
		assertThrowsAssertionErrorWithMessage(
				() -> assertThat(null).is(equalTo(this)),
				String.format("expected that subject is equal to: \"%s\"%nbut was: null", this)
		);
	}

	@Test
	void equalToDoesNotMatchAnyObject() {
		final Object anyObject = new Object();
		assertThrowsAssertionErrorWithMessage(
				() -> assertThat(anyObject).is(equalTo(this)),
				String.format("expected that subject is equal to: \"%s\"%nbut was: \"%s\"", this, anyObject)
		);
	}

	@Test
	void equalToMatchesIdenticalObjects() {
		assertDoesNotThrow(
				() -> assertThat(this).is(equalTo(this))
		);
	}

	@Test
	void equalToMatchesEqualObjects() {
		assertDoesNotThrow(
				() -> assertThat(new Date(999_999_999L)).is(equalTo(new Date(999_999_999L)))
		);
	}

	@Test
	void equalToDoesNotMatchObjectsOfDifferentClass() {
		assertThrowsAssertionErrorWithMessage(
				() -> assertThat(Integer.valueOf(1)).is(equalTo(Long.valueOf(1L))),
				String.format("expected that subject is equal to: 1%nbut was: 1")
		);
	}

	@Test
	void equalToDoesNotMatchZeroWithMinusZeroAsObjects() {
		final Object zero = 0d;
		final Object minusZero = -0d;
		assertThrowsAssertionErrorWithMessage(
				() -> assertThat(minusZero).is(equalTo(zero)),
				String.format("expected that subject is equal to: 0.0\nbut was: -0.0")
		);
	}

	@Test
	void equalToMatchesNanAsObjects() {
		final Double nan = NaN;
		assertDoesNotThrow(
				() -> assertThat(nan).is(equalTo(nan))
		);
	}

	@Test
	void stringValue() {
		assertEquals("equal to: \"xyz\"", equalTo("xyz").toString());
	}
}
