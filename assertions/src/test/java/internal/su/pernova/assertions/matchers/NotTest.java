package internal.su.pernova.assertions.matchers;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import static su.pernova.assertions.AssertionTestUtils.assertThrowsAssertionErrorWithMessage;
import static su.pernova.assertions.Assertions.assertThat;
import static su.pernova.assertions.Matchers.equalTo;
import static su.pernova.assertions.Matchers.is;
import static su.pernova.assertions.Matchers.not;
import static su.pernova.assertions.Matchers.sameAs;

import org.junit.jupiter.api.Test;

class NotTest {

	@Test
	void notMatchesWhenDelegateDoesNot() {
		assertDoesNotThrow(() -> assertThat(this, is(not(sameAs(null)))));
		assertDoesNotThrow(() -> assertThat(1, is(not(equalTo(2)))));
	}

	@Test
	void notDoesNotMatchWhenDelegateDoes() {
		assertThrowsAssertionErrorWithMessage(
				() -> assertThat(null, is(not(sameAs(null)))),
				String.format("expected that subject is not same as: null%nbut was: null")
		);
		assertThrowsAssertionErrorWithMessage(
				() -> assertThat(2, is(not(equalTo(2)))),
				String.format("expected that subject is not equal to: 2%nbut was: 2")
		);
	}

	@Test
	void notDouble() {
		assertDoesNotThrow(() -> assertThat(5d, is(not(4d))));
		assertThrowsAssertionErrorWithMessage(
				() -> assertThat(5d, is(not(5d))),
				String.format("expected that subject is not: 5.0%nbut was: 5.0")
		);
	}

	@Test
	void notObject() {
		String object = new String("object");
		String equalButNotSameObject = new String("object");
		assertDoesNotThrow(() -> assertThat(object, is(not(equalButNotSameObject))));
		assertThrowsAssertionErrorWithMessage(
				() -> assertThat(object, is(not(equalTo(equalButNotSameObject)))),
				String.format("expected that subject is not equal to: \"%s\"%nbut was: \"%s\"", equalButNotSameObject, object)
		);
	}
}
