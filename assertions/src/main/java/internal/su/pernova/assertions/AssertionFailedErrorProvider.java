package internal.su.pernova.assertions;

import org.opentest4j.AssertionFailedError;

class AssertionFailedErrorProvider extends FailureProvider {

	private static class Singleton {

		private static final AssertionFailedErrorProvider INSTANCE = new AssertionFailedErrorProvider();
	}

	private AssertionFailedErrorProvider() {
	}

	public static AssertionFailedErrorProvider getInstance() {
		return Singleton.INSTANCE;
	}

	@Override
	public AssertionFailedError newFailure() {
		return new AssertionFailedError();
	}

	@Override
	public AssertionFailedError newFailure(String message, Object expected, Object actual) {
		return new AssertionFailedError(message, expected, actual);
	}
}
