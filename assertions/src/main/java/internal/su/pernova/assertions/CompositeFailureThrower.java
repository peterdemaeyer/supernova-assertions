package internal.su.pernova.assertions;

import static java.util.Objects.requireNonNull;

import java.util.Collection;

class CompositeFailureThrower implements FailureThrower {

	private final Collection<? extends FailureThrower> throwers;

	CompositeFailureThrower(Collection<? extends FailureThrower> throwers) {
		this.throwers = requireNonNull(throwers, "throwers are null");
	}

	@Override
	public void throwFailure() {
		for (FailureThrower thrower : throwers) {
			thrower.throwFailure();
		}
	}

	@Override
	public void throwFailure(String message, Object expected, Object actual) {
		for (FailureThrower thrower : throwers) {
			thrower.throwFailure(message, expected, actual);
		}
	}
}
