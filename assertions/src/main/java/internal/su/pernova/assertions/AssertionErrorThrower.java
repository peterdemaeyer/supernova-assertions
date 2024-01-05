package internal.su.pernova.assertions;

class AssertionErrorThrower implements FailureThrower {

	private static class Singleton {

		private static final AssertionErrorThrower INSTANCE = new AssertionErrorThrower();
	}

	private AssertionErrorThrower() {
	}

	public static AssertionErrorThrower getInstance() {
		return Singleton.INSTANCE;
	}

	@Override
	public void throwFailure() {
		throw new AssertionError();
	}

	@Override
	public void throwFailure(String message, Object expected, Object actual) {
		throw new AssertionError(message);
	}
}
