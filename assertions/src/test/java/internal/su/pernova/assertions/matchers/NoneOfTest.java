package internal.su.pernova.assertions.matchers;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static su.pernova.assertions.AssertionTestUtils.assertThrowsAssertionErrorWithMessage;
import static su.pernova.assertions.Assertions.assertThat;
import static su.pernova.assertions.Matchers.is;
import static su.pernova.assertions.Matchers.noneOf;

import org.junit.jupiter.api.Test;

import su.pernova.assertions.Matcher;

class NoneOfTest implements MultiMatcherContractTest {

	@Override
	public Matcher getInstance() {
		return is(noneOf(new Object(), new Object(), new Object()));
	}

	@Override
	public Matcher getInstance(Object... expectedValues) {
		return noneOf(expectedValues);
	}

	@Override
	public Matcher getInstance(double... expectedValues) {
		return noneOf(expectedValues);
	}

	@Override
	public Matcher getInstance(float... expectedValues) {
		return noneOf(expectedValues);
	}

	@Override
	public Matcher getInstance(long... expectedValues) {
		return noneOf(expectedValues);
	}

	@Override
	public Matcher getInstance(int... expectedValues) {
		return noneOf(expectedValues);
	}

	@Override
	public Matcher getInstance(short... expectedValues) {
		return noneOf(expectedValues);
	}

	@Override
	public Matcher getInstance(byte... expectedValues) {
		return noneOf(expectedValues);
	}

	@Override
	public Matcher getInstance(char... expectedValues) {
		return noneOf(expectedValues);
	}

	@Override
	public Matcher getInstance(boolean... expectedValues) {
		return noneOf(expectedValues);
	}

	@Test
	void matchingNoneOf() {
		final String one = "one";
		final Matcher isNoneOfTheExpectedValues = is(noneOf(one, "two", "three"));
		assertTrue(isNoneOfTheExpectedValues.match("four"));
		assertFalse(isNoneOfTheExpectedValues.match(one));
		assertThrowsAssertionErrorWithMessage(() -> assertThat(one, isNoneOfTheExpectedValues),
				"expected that subject is none of: [\"one\", \"two\", \"three\"]\nbut was: \"one\"");
	}
}
