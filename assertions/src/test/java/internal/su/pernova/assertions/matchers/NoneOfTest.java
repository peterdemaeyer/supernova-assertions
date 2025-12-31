package internal.su.pernova.assertions.matchers;

import static su.pernova.assertions.Matchers.is;
import static su.pernova.assertions.Matchers.noneOf;

import su.pernova.assertions.Matcher;

class NoneOfTest implements MultiMatcherContractTest {

	@Override
	public Matcher getInstance() {
		return is(noneOf(new Object(), new Object(), new Object()));
	}

	@Override
	public Matcher getIncompleteInstance(Object... expectedValues) {
		return noneOf(expectedValues);
	}

	@Override
	public Matcher getIncompleteInstance(double... expectedValues) {
		return noneOf(expectedValues);
	}

	@Override
	public Matcher getIncompleteInstance(float... expectedValues) {
		return noneOf(expectedValues);
	}

	@Override
	public Matcher getIncompleteInstance(long... expectedValues) {
		return noneOf(expectedValues);
	}

	@Override
	public Matcher getIncompleteInstance(int... expectedValues) {
		return noneOf(expectedValues);
	}

	@Override
	public Matcher getIncompleteInstance(short... expectedValues) {
		return noneOf(expectedValues);
	}

	@Override
	public Matcher getIncompleteInstance(byte... expectedValues) {
		return noneOf(expectedValues);
	}

	@Override
	public Matcher getIncompleteInstance(char... expectedValues) {
		return noneOf(expectedValues);
	}

	@Override
	public Matcher getIncompleteInstance(boolean... expectedValues) {
		return noneOf(expectedValues);
	}
}
