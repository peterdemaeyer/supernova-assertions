package internal.su.pernova.assertions.matchers;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import static su.pernova.assertions.AssertionTestUtils.assertThrowsAssertionErrorWithMessage;
import static su.pernova.assertions.Assertions.assertThat;
import static su.pernova.assertions.Matchers.is;
import static su.pernova.assertions.Matchers.sameAs;

import org.junit.jupiter.api.Test;

class SameAsTest {

	@Test
	void sameAsMatchesNull() {
		assertDoesNotThrow(() -> assertThat(null, is(sameAs(null))));
	}

	@Test
	void sameAsDoesNotMatchNull() {
		assertThrowsAssertionErrorWithMessage(
				() -> assertThat(this, is(sameAs(null))),
				String.format("expected that subject is same as: null%nbut was: \"%s\"", this)
		);
	}
}
