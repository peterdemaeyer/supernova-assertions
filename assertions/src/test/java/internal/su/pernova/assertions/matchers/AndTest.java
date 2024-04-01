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
}
