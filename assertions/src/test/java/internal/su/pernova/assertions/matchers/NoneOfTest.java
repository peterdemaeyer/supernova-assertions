package internal.su.pernova.assertions.matchers;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import static su.pernova.assertions.AssertionTestUtils.assertThrowsAssertionErrorWithMessage;
import static su.pernova.assertions.Assertions.assertThat;
import static su.pernova.assertions.Matchers.is;
import static su.pernova.assertions.Matchers.noneOf;

import org.junit.jupiter.api.Test;

import su.pernova.assertions.Matcher;

class NoneOfTest implements MultiMatcherContractTest {

	@Override
	public NoneOf getInstance() {
		return assertInstanceOf(NoneOf.class, noneOf(new Object(), new Object(), new Object()));
	}

	@Override
	public NoneOf getInstance(Object... expectedValues) {
		return assertInstanceOf(NoneOf.class, noneOf(expectedValues));
	}

	@Override
	public NoneOf getInstance(double... expectedValues) {
		return assertInstanceOf(NoneOf.class, noneOf(expectedValues));
	}

	@Override
	public NoneOf getInstance(float... expectedValues) {
		return assertInstanceOf(NoneOf.class, noneOf(expectedValues));
	}

	@Override
	public NoneOf getInstance(long... expectedValues) {
		return assertInstanceOf(NoneOf.class, noneOf(expectedValues));
	}

	@Override
	public NoneOf getInstance(int... expectedValues) {
		return assertInstanceOf(NoneOf.class, noneOf(expectedValues));
	}

	@Override
	public NoneOf getInstance(short... expectedValues) {
		return assertInstanceOf(NoneOf.class, noneOf(expectedValues));
	}

	@Override
	public NoneOf getInstance(byte... expectedValues) {
		return assertInstanceOf(NoneOf.class, noneOf(expectedValues));
	}

	@Override
	public NoneOf getInstance(char... expectedValues) {
		return assertInstanceOf(NoneOf.class, noneOf(expectedValues));
	}

	@Override
	public NoneOf getInstance(boolean... expectedValues) {
		return assertInstanceOf(NoneOf.class, noneOf(expectedValues));
	}

	@Test
	void matchingNoneOfObjects() {
		final Object one = new Object();
		final Object two = new Object();
		final Object three = new Object();
		final Object four = new Object();
		final Matcher isNoneOfOneTwoThree = is(noneOf(one, two, three));
		assertThat(four, isNoneOfOneTwoThree);
		assertThrowsAssertionErrorWithMessage(
				() -> assertThat(one, isNoneOfOneTwoThree),
				String.format("expected that subject is none of: [\"%s\", \"%s\", \"%s\"]", one, two, three),
				String.format("but was: \"%s\"", one)
		);
	}
}
