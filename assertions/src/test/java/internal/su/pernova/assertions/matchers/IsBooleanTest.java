package internal.su.pernova.assertions.matchers;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static su.pernova.assertions.AssertionTestUtils.assertThrowsAssertionErrorWithMessage;
import static su.pernova.assertions.Assertions.assertThat;
import static su.pernova.assertions.Matchers.is;

import org.junit.jupiter.api.Test;

class IsBooleanTest {

	@Test
	void isBooleanMatches() {
		assertDoesNotThrow(
				() -> assertThat(TRUE).is(true)
		);
		assertDoesNotThrow(
				() -> assertThat(false).is(false)
		);
	}

	@Test
	void isBooleanDoesNotMatch() {
		assertThrowsAssertionErrorWithMessage(
				() -> assertThat(FALSE).is(true),
				String.format("expected that subject is: true%nbut was: false")
		);
		assertThrowsAssertionErrorWithMessage(
				() -> assertThat(true).is(false),
				String.format("expected that subject is: false%nbut was: true")
		);
	}

	@Test
	void isBooleanDoesNotMatchNull() {
		assertThrowsAssertionErrorWithMessage(
				() -> assertThat(null).is(true),
				String.format("expected that subject is: true%nbut was: null")
		);
	}

	@Test
	void isBooleanDoesNotMatchAnyObject() {
		final Object anyObject = new Object();
		assertThrowsAssertionErrorWithMessage(
				() -> assertThat(anyObject).is(true),
				String.format("expected that subject is: true%nbut was: \"%s\"", anyObject)
		);
	}

	@Test
	void stringValue() {
		assertEquals("is: true", is(true).toString());
	}
}
