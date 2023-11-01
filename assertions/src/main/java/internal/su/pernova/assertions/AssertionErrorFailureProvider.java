package internal.su.pernova.assertions;

class AssertionErrorFailureProvider extends FailureProvider {

	@Override
	public Error newAssertionFailure() {
		return new AssertionError();
	}

	@Override
	public Error newAssertionFailure(String message, Object expected, Object actual) {
		return new AssertionError(message);
	}
}
