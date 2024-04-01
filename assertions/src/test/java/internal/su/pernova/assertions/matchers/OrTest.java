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

class OrTest {

	@Test
	void orMatcher() {
		assertDoesNotThrow(() -> assertThat("abc",
				is(instanceOf(Date.class).or(is(equalTo("abc"))))));
		assertDoesNotThrow(() -> assertThat("abc",
				is(instanceOf(String.class).or(is(equalTo("def"))))));
		assertThrowsAssertionErrorWithMessage(
				() -> assertThat("abc",
						is(instanceOf(Date.class).or(is(equalTo("def"))))),
				String.format("expected that subject is instance of: %s or is equal to: \"def\"%nbut was: \"abc\"",
						Date.class.getName())
		);
	}
}
