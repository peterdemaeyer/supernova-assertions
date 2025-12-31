package internal.su.pernova.assertions.matchers;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static su.pernova.assertions.AssertionTestUtils.assertThrowsAssertionErrorWithMessage;
import static su.pernova.assertions.Assertions.assertThat;
import static su.pernova.assertions.Matchers.is;
import static su.pernova.assertions.Matchers.noneOf;

import org.junit.jupiter.api.Test;

import su.pernova.assertions.ContextSensitiveMatcher;
import su.pernova.assertions.Matcher;

class NoneOfTest implements MultiMatcherContractTest {

	@Override
	public Is getInstance() {
		return assertInstanceOf(Is.class, is(noneOf(new Object(), new Object(), new Object())));
	}

	@Override
	public ContextSensitiveMatcher getInstance(Object... expectedValues) {
		return assertInstanceOf(ContextSensitiveMatcher.class, noneOf(expectedValues));
	}

	@Override
	public ContextSensitiveMatcher getInstance(double... expectedValues) {
		return assertInstanceOf(ContextSensitiveMatcher.class, noneOf(expectedValues));
	}

	@Override
	public ContextSensitiveMatcher getInstance(float... expectedValues) {
		return assertInstanceOf(ContextSensitiveMatcher.class, noneOf(expectedValues));
	}

	@Override
	public ContextSensitiveMatcher getInstance(long... expectedValues) {
		return assertInstanceOf(ContextSensitiveMatcher.class, noneOf(expectedValues));
	}

	@Override
	public ContextSensitiveMatcher getInstance(int... expectedValues) {
		return assertInstanceOf(ContextSensitiveMatcher.class, noneOf(expectedValues));
	}

	@Override
	public ContextSensitiveMatcher getInstance(short... expectedValues) {
		return assertInstanceOf(ContextSensitiveMatcher.class, noneOf(expectedValues));
	}

	@Override
	public ContextSensitiveMatcher getInstance(byte... expectedValues) {
		return assertInstanceOf(ContextSensitiveMatcher.class, noneOf(expectedValues));
	}

	@Override
	public ContextSensitiveMatcher getInstance(char... expectedValues) {
		return assertInstanceOf(ContextSensitiveMatcher.class, noneOf(expectedValues));
	}

	@Override
	public ContextSensitiveMatcher getInstance(boolean... expectedValues) {
		return assertInstanceOf(ContextSensitiveMatcher.class, noneOf(expectedValues));
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
