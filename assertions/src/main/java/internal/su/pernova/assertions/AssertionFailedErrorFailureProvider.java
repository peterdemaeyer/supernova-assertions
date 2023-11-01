package internal.su.pernova.assertions;

import org.opentest4j.AssertionFailedError;

class AssertionFailedErrorFailureProvider extends FailureProvider {

	@Override
	public Error newAssertionFailure() {
		if (anyStackTraceMatches("org.junit.runners.", "org.junit.jupiter.")) {
			return new AssertionFailedError();
		}
		return null;
	}

	@Override
	public AssertionFailedError newAssertionFailure(String message, Object expected, Object actual) {
		if (anyStackTraceMatches("org.junit.runners.", "org.junit.jupiter.")) {
			return new AssertionFailedError(message, expected, actual);
		}
		return null;
	}
}
