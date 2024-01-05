package internal.su.pernova.assertions;

import static internal.su.pernova.assertions.TestFrameworkUtils.isOpenTest4J;

import org.opentest4j.AssertionFailedError;

class AssertionFailedErrorThrower implements FailureThrower {

	private static class Singleton {

		private static final AssertionFailedErrorThrower INSTANCE = new AssertionFailedErrorThrower();
	}

	private AssertionFailedErrorThrower() {
	}

	public static AssertionFailedErrorThrower getInstance() {
		return Singleton.INSTANCE;
	}

	@Override
	public void throwFailure() {
		if (isOpenTest4J()) {
			throw new AssertionFailedError();
		}
	}

	@Override
	public void throwFailure(String message, Object expected, Object actual) {
		if (isOpenTest4J()) {
			throw new AssertionFailedError(message, expected, actual);
		}
	}
}
