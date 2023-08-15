package internal.su.pernova.assertions.matchers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static su.pernova.assertions.AssertionTestUtils.assertThrowsAssertionErrorWithMessage;
import static su.pernova.assertions.Assertions.assertThat;
import static su.pernova.assertions.Matchers.nan;

import org.junit.jupiter.api.Test;

class NanTest {

	/**
	 * This test illustrates the need for a {@link Nan} matcher.
	 * The identity operator "==" returns {@code false} when both operands are NaN.
	 */
	@Test
	void isDoubleDoesNotMatchNan() {
		assertThrowsAssertionErrorWithMessage(
				() -> assertThat(Double.NaN).is(Double.NaN),
				String.format("expected that subject is: NaN%nbut was: NaN")
		);
	}

	/**
	 * This test illustrates the need for a {@link Nan} matcher.
	 * The identity operator "==" returns {@code false} when both operands are NaN.
	 */
	@Test
	void isFloatDoesNotMatchNan() {
		assertThrowsAssertionErrorWithMessage(
				() -> assertThat(Float.NaN).is(Float.NaN),
				String.format("expected that subject is: NaN%nbut was: NaN")
		);
	}

	@Test
	void nanMatchesNan() {
		assertThat(Double.NaN).is(nan());
		assertThat(Float.NaN).is(nan());
	}

	@Test
	void nanDoesNotMatchNull() {
		assertThrowsAssertionErrorWithMessage(
				() -> assertThat(null).is(nan()),
				String.format("expected that subject is NaN%nbut was: null")
		);
	}

	@Test
	void nanDoesNotMatchAnyObject() {
		assertThrowsAssertionErrorWithMessage(
				() -> assertThat(this).is(nan()),
				String.format("expected that subject is NaN%nbut was: \"%s\"", this)
		);
	}

	@Test
	void stringValue() {
		assertEquals("NaN", nan().toString());
	}
}
