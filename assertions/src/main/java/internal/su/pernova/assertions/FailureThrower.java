package internal.su.pernova.assertions;

public interface FailureThrower {

	void throwFailure();

	void throwFailure(String message, Object expected, Object actual);
}
