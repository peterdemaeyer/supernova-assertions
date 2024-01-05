package internal.su.pernova.assertions;

import static internal.su.pernova.assertions.TestFrameworkUtils.isJUnit4;

import org.junit.AssumptionViolatedException;

public class AssumptionViolatedExceptionThrower implements FailureThrower {

	private static final class Singleton {

		private static final AssumptionViolatedExceptionThrower INSTANCE = new AssumptionViolatedExceptionThrower();
	}

	private AssumptionViolatedExceptionThrower() {
	}

	public static AssumptionViolatedExceptionThrower getInstance() {
		return Singleton.INSTANCE;
	}

	@Override
	public void throwFailure() {
		if (isJUnit4()) {
			throw new AssumptionViolatedException(null);
		}
	}

	@Override
	public void throwFailure(String message, Object expected, Object actual) {
		if (isJUnit4()) {
			throw new AssumptionViolatedException(message);
		}
	}
}
