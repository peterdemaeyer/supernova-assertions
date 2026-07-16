package internal.su.pernova.assertions.matchers;

import static java.util.Objects.toIdentityString;
import static java.util.concurrent.TimeUnit.MILLISECONDS;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static su.pernova.assertions.AssertionTestUtils.assertThrowsAssertionErrorWithMessage;
import static su.pernova.assertions.Assertions.assertThat;
import static su.pernova.assertions.Matchers.is;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.junit.jupiter.api.Test;

import su.pernova.assertions.Matcher;
import su.pernova.assertions.MatcherContractTest;
import su.pernova.assertions.Subject;

class IsObjectTest implements MatcherContractTest {

	@Override
	public IsObject getInstance() {
		return (IsObject) is(new Object());
	}

	@Test
	void nullMatchesNull() {
		assertDoesNotThrow(() -> assertThat((Object) null, is((Object) null)));
	}

	@Test
	void nullSubjectMatchesNull() {
		assertDoesNotThrow(() -> assertThat((Subject) null, is((Object) null)));
	}

	@Test
	void nullMatchesNullMatcher() {
		assertDoesNotThrow(() -> assertThat((Object) null, is((Matcher) null)));
	}

	@Test
	void nonNullDoesNotMatchNull() {
		final Object nonNull = new Object();
		assertEquals("expected that subject is: null%nbut was: %s".formatted(nonNull),
				assertThrows(AssertionError.class,
						() -> assertThat(nonNull, is((Object) null))).getMessage());
	}

	@Test
	void mismatchDescriptionDoesNotQuoteObjectValues() {
		final Object object = new Object();
		final Object anotherObject = new Object();
		assertEquals("expected that subject is: %s%nbut was: %s".formatted(object, anotherObject),
				assertThrows(AssertionError.class,
						() -> assertThat(anotherObject, is(object))).getMessage());
	}

	@Test
	void identicalObjectsMatch() {
		final Object object = new Object();
		assertThat(object, is(object));
	}

	@Test
	void equalObjectsDoNotMatch() {
		final Object object = new Date(1784193306391L);
		final Object equalObject = new Date(1784193306391L);
		assertEquals(object, equalObject); // Precondition assertion.
		assertEquals("expected that subject is: %s%nbut was: %s".formatted(toIdentityString(equalObject), toIdentityString(object)),
				assertThrows(AssertionError.class,
						() -> assertThat(object, is(equalObject))).getMessage());
	}

	@Test
	void stringValue() {
		assertEquals("is(Supernova)", is("Supernova").toString());
		assertEquals("is(MILLISECONDS)", is(MILLISECONDS).toString());
		assertEquals("is([x, 22, abc])", is(List.of('x', BigInteger.valueOf(22L), "abc")).toString());
	}
}
