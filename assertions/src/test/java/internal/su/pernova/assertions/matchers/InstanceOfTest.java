package internal.su.pernova.assertions.matchers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static su.pernova.assertions.AssertionTestUtils.assertThrowsAssertionErrorWithMessage;
import static su.pernova.assertions.Assertions.assertThat;
import static su.pernova.assertions.Matchers.instanceOf;
import static su.pernova.assertions.Matchers.is;

import org.junit.jupiter.api.Test;

class InstanceOfTest {

	@Test
	void instanceOfDoesNotMatchNull() {
		assertThrowsAssertionErrorWithMessage(
				() -> assertThat(null, is(instanceOf(String.class))),
				String.format("expected that subject is instance of: java.lang.String%nbut was: null")
		);
	}

	@Test
	void instanceOfDoesNotMatchAnyObject() {
		final Object anyObject = new Object();
		assertThrowsAssertionErrorWithMessage(
				() -> assertThat(anyObject, is(instanceOf(Number.class))),
				String.format("expected that subject is instance of: java.lang.Number%nbut was: \"%s\"", anyObject)
		);
	}

	@Test
	void instanceOfMatchesObjectOfClass() {
		assertThat("abc", is(instanceOf(String.class)));
	}

	@Test
	void instanceOfMatchesObjectOfSubclass() {
		assertThat("abc", is(instanceOf(CharSequence.class)));
	}

	@Test
	void stringValue() {
		assertEquals("instance of: java.lang.Void", instanceOf(Void.class).toString());
	}
}
