package internal.su.pernova.assertions;

class AssertionErrorProvider extends FailureProvider {

	private static class Singleton {

		private static final AssertionErrorProvider INSTANCE = new AssertionErrorProvider();
	}

	private AssertionErrorProvider() {
	}

	public static AssertionErrorProvider getInstance() {
		return Singleton.INSTANCE;
	}

	@Override
	public AssertionError newFailure() {
		return new AssertionError();
	}

	@Override
	public AssertionError newFailure(String message, Object expected, Object actual) {
		return new AssertionError(message);
	}
}
