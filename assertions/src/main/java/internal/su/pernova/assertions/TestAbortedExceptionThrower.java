package internal.su.pernova.assertions;

import static internal.su.pernova.assertions.TestFrameworkUtils.isOpenTest4J;

import org.opentest4j.TestAbortedException;

public class TestAbortedExceptionThrower implements FailureThrower {

	private static class Singleton {

		private static final TestAbortedExceptionThrower INSTANCE = new TestAbortedExceptionThrower();
	}

	private TestAbortedExceptionThrower() {
	}

	public static TestAbortedExceptionThrower getInstance() {
		return Singleton.INSTANCE;
	}

	@Override
	public void throwFailure() {
		if (isOpenTest4J()) {
			throw new TestAbortedException();
		}
	}

	@Override
	public void throwFailure(String message, Object expected, Object actual) {
		if (isOpenTest4J()) {
			throw new TestAbortedException(message);
		}
	}
}
