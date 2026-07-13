package internal.su.pernova.assertions.matchers;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import static su.pernova.assertions.AssertionTestUtils.assertThrowsAssertionErrorWithMessage;
import static su.pernova.assertions.Assertions.assertThat;
import static su.pernova.assertions.Matchers.equalTo;
import static su.pernova.assertions.Matchers.instanceOf;
import static su.pernova.assertions.Matchers.is;

import java.util.Date;

import org.junit.jupiter.api.Test;

import su.pernova.assertions.Context;
import su.pernova.assertions.MatcherContractTest;

class AndTest implements MatcherContractTest {

	@Override
	public And getInstance() {
		return assertInstanceOf(And.class, is(1).and(2));
	}

	@Test
	void isPrimitiveValueAndIntMatches() {
		assertThat(5, is(5).and(5));
		assertThat(5, is(5L).and(5));
		assertThat(5, is((short) 5).and(5));
		assertThat(5, is((byte) 5).and(5));
		assertThat(5, is(5f).and(5));
		assertThat(5, is(5d).and(5));
	}

	@Test
	void isPrimitiveValueAndIntMismatches() {
		assertThrowsAssertionErrorWithMessage(
				() -> assertThat(5, is(5).and(6)),
				"expected that subject is: 5 and: 6", "but was: 5");
		assertThrowsAssertionErrorWithMessage(
				() -> assertThat(5, is(5f).and(6)),
				"expected that subject is: 5.0 and: 6", "but was: 5");
	}

	@Test
	void isPrimitiveValueAndLongMatches() {
		assertThat(5, is(5).and(5L));
		assertThat(5, is(5L).and(5L));
		assertThat(5, is((short) 5).and(5L));
		assertThat(5, is((byte) 5).and(5L));
		assertThat(5, is(5f).and(5L));
		assertThat(5, is(5d).and(5L));
	}

	@Test
	void isPrimitiveValueAndLongMismatches() {
		assertThrowsAssertionErrorWithMessage(
				() -> assertThat(5L, is(5L).and(6L)),
				"expected that subject is: 5 and: 6", "but was: 5");
		assertThrowsAssertionErrorWithMessage(
				() -> assertThat(5, is(5d).and(6L)),
				"expected that subject is: 5.0 and: 6", "but was: 5");
	}

	@Test
	void isPrimitiveValueAndShortMatches() {
		assertThat(5, is(5).and((short) 5));
		assertThat(5, is(5L).and((short) 5));
		assertThat(5, is((short) 5).and((short) 5));
		assertThat(5, is((byte) 5).and((short) 5));
		assertThat(5, is(5f).and((short) 5));
		assertThat(5, is(5d).and((short) 5));
	}

	@Test
	void isPrimitiveValueAndShortMismatches() {
		assertThrowsAssertionErrorWithMessage(
				() -> assertThat(5, is(5).and((short) 6)),
				"expected that subject is: 5 and: 6", "but was: 5");
		assertThrowsAssertionErrorWithMessage(
				() -> assertThat(5, is(5).and((short) 6)),
				"expected that subject is: 5 and: 6", "but was: 5");
	}

	@Test
	void isPrimitiveValueAndByteMatches() {
		assertThat(5, is(5).and((byte) 5));
		assertThat(5, is(5L).and((byte) 5));
		assertThat(5, is((short) 5).and((byte) 5));
		assertThat(5, is((byte) 5).and((byte) 5));
		assertThat(5, is(5f).and((byte) 5));
		assertThat(5, is(5d).and((byte) 5));
	}

	@Test
	void isPrimitiveValueAndByteMismatches() {
		assertThrowsAssertionErrorWithMessage(
				() -> assertThat(5, is(5).and((byte) 6)),
				"expected that subject is: 5 and: 6", "but was: 5");
		assertThrowsAssertionErrorWithMessage(
				() -> assertThat(5, is(5).and((byte) 6)),
				"expected that subject is: 5 and: 6", "but was: 5");
	}

	@Test
	void isPrimitiveValueAndCharMatches() {
		assertThat(97, is(97).and('a'));
		assertThat(97, is(97L).and('a'));
		assertThat('a', is((short) 97).and('a'));
		assertThat('a', is((byte) 97).and('a'));
		assertThat(97, is(97f).and('a'));
		assertThat(97, is(97d).and('a'));
	}

	@Test
	void isCharAndCharMismatches() {
		assertThrowsAssertionErrorWithMessage(
				() -> assertThat('a', is('a').and('b')),
				"expected that subject is: 'a' and: 'b'", "but was: 'a'");
	}

	@Test
	void isBooleanAndBooleanMatches() {
		assertThat(false, is(false).and(false));
		assertThat(true, is(true).and(true));
	}

	@Test
	void isBooleanAndBooleanMismatches() {
		assertThrowsAssertionErrorWithMessage(
				() -> assertThat(true, is(true).and(false)),
				"expected that subject is: true and: false", "but was: true");
	}

	@Test
	void andMatchesOrNot() {
		assertDoesNotThrow(() -> assertThat("abc",
				is(instanceOf(String.class).and(is(equalTo("abc"))))));
		assertThrowsAssertionErrorWithMessage(
				() -> assertThat("abc",
						is(instanceOf(Date.class).and(is(equalTo("abc"))))),
				String.format("expected that subject is instance of: %s and is equal to: \"abc\"%nbut was: \"abc\"",
						Date.class.getName())
		);
		assertThrowsAssertionErrorWithMessage(
				() -> assertThat("abc",
						is(instanceOf(String.class).and(is(equalTo("def"))))),
				String.format("expected that subject is instance of: %s and is equal to: \"def\"%nbut was: \"abc\"",
						String.class.getName())
		);
	}

	@Test
	void andMatchesContextually() {
		assertThat("this", is("this").and("this"));
		assertThat(1d, is(1d).and(1d));
		assertThrowsAssertionErrorWithMessage(
				() -> assertThat(1d, is(1d).and(2d)),
				"expected that subject is: 1.0 and: 2.0",
				"but was: 1.0"
		);
		assertThat(1f, is(1f).and(1f));
		assertThat(1L, is(1L).and(1L));
		assertThat(1, is(1).and(1));
		assertThat((short) 1, is((short) 1).and((short) 1));
		assertThat((byte) 1, is((byte) 1).and((byte) 1));
		assertThat('1', is('1').and('1'));
		assertThat(false, is(false).and(false));
	}

	@Test
	void andMatchesContextuallyWithDualContext() {
		assertThat("this", is(equalTo("this")).and(instanceOf(Object.class).and(CharSequence.class)));
		assertThrowsAssertionErrorWithMessage(
				() -> assertThat("this", is(equalTo("this")).and(instanceOf(Object.class)).and(CharSequence.class)),
				"expected that subject is equal to: \"this\" and instance of: java.lang.Object and: java.lang.CharSequence",
				"but was: \"this\""
		);
		assertThat("this", is(equalTo("this")).and(is(instanceOf(Object.class).and(CharSequence.class))));
		assertThrowsAssertionErrorWithMessage(
				() -> assertThat("this", is(equalTo("this")).and(is(instanceOf(Object.class))).and(CharSequence.class)),
				"expected that subject is equal to: \"this\" and is instance of: java.lang.Object and: java.lang.CharSequence",
				"but was: \"this\""
		);
	}

	@Test
	void stringValue() {
		assertEquals("and{is(8)}{(9)}", is(8).and(9).toString());
	}

	@Test
	void contextualizedStringValue() {
		assertEquals("and{is(8)}{i̶s̶(9)}", is(8).and(9).contextualize(new Context()).toString());
	}
}
