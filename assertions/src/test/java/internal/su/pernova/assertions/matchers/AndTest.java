package internal.su.pernova.assertions.matchers;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import static su.pernova.assertions.AssertionTestUtils.assertThrowsAssertionErrorWithMessage;
import static su.pernova.assertions.Assertions.assertThat;
import static su.pernova.assertions.Matchers.equalTo;
import static su.pernova.assertions.Matchers.instanceOf;
import static su.pernova.assertions.Matchers.is;

import java.util.Date;

import org.junit.jupiter.api.Test;

class AndTest {

	@Test
	void andMatcher() {
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
	void contextSensitiveMatching() {
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
	void contextSensitiveMatchingWithDualContext() {
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
}
