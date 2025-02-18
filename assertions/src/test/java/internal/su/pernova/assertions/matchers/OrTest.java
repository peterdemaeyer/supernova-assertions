package internal.su.pernova.assertions.matchers;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static su.pernova.assertions.AssertionTestUtils.assertThrowsAssertionErrorWithMessage;
import static su.pernova.assertions.Assertions.assertThat;
import static su.pernova.assertions.Matchers.equalTo;
import static su.pernova.assertions.Matchers.instanceOf;
import static su.pernova.assertions.Matchers.is;

import java.util.Date;

import org.junit.jupiter.api.Test;

import su.pernova.assertions.AssertionTestUtils;

class OrTest {

	@Test
	void orMatcher() {
		assertDoesNotThrow(() -> assertThat("abc",
				is(instanceOf(Date.class).or(is(equalTo("abc"))))));
		assertDoesNotThrow(() -> assertThat("abc",
				is(instanceOf(String.class).or(is(equalTo("def"))))));
		assertThrowsAssertionErrorWithMessage(
				() -> assertThat("abc", is(instanceOf(Date.class).or(is(equalTo("def"))))),
				String.format("expected that subject is instance of: %s or is equal to: \"def\"", Date.class.getName()),
				"but was: \"abc\""
		);
	}

	@Test
	void contextSensitiveMatching() {
		assertThat("this", is("this").or("that"));
		assertThat(1d, is(2d).or(1d));
		assertThrowsAssertionErrorWithMessage(
				() -> assertThat(1d, is(2d).or(3d)),
				"expected that subject is: 2.0 or: 3.0",
				"but was: 1.0"
		);
		assertThat(1f, is(2f).or(1f));
		assertThat(1L, is(2L).or(1L));
		assertThat(1, is(2).or(1));
		assertThat((short) 1, is((short) 2).or((short) 1));
		assertThat((byte) 1, is((byte) 2).or((byte) 1));
		assertThat('1', is('2').or('1'));
		assertThat(false, is(true).or(false));
	}
}
