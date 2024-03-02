package internal.su.pernova.assertions.matchers;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static su.pernova.assertions.AssertionTestUtils.assertThrowsAssertionErrorWithMessage;
import static su.pernova.assertions.Assertions.assertThat;
import static su.pernova.assertions.Matchers.is;

import java.math.BigInteger;
import java.util.List;

import org.junit.jupiter.api.Test;

import su.pernova.assertions.Subject;

class IsObjectTest {

	@Test
	void isMatchesNull() {
		assertDoesNotThrow(() -> assertThat((Subject) null, is(null)));
		assertDoesNotThrow(() -> assertThat((Object) null, is(null)));
	}

	@Test
	void isDoesNotMatchNull() {
		assertThrowsAssertionErrorWithMessage(
				() -> assertThat(this, is(null)),
				String.format("expected that subject is: null%nbut was: \"%s\"", this)
		);
	}

	@Test
	void isDoesNotMatchAnyObject() {
		final Object anyObject = new Object();
		assertThrowsAssertionErrorWithMessage(
				() -> assertThat(anyObject, is(this)),
				String.format("expected that subject is: \"%s\"%nbut was: \"%s\"", this, anyObject)
		);
	}

	@Test
	void isMatchesIdenticalObjects() {
		final Object object = new Object();
		assertThat(object, is(object));
	}

	@Test
	void isDoesNotMatchEqualObjects() {
		final Object object = new String("abc");
		final Object equalObject = new String("abc");
		assertEquals(object, equalObject); // Precondition assertion.
		assertThrowsAssertionErrorWithMessage(
				() -> assertThat(object, is(equalObject)),
				String.format("expected that subject is: \"abc\"%nbut was: \"abc\"")
		);
	}

	@Test
	void stringValue() {
		assertEquals("is: \"Supernova\"", is("Supernova").toString());
		assertEquals("is: MILLISECONDS", is(MILLISECONDS).toString());
		assertEquals("is: ['x', 22, \"abc\"]", is(List.of('x', BigInteger.valueOf(22L), "abc")).toString());
	}
}
