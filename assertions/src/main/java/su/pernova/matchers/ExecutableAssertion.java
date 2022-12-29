package su.pernova.matchers;

import static java.util.Objects.requireNonNull;

public class ExecutableAssertion {

	private final Executable executable;

	ExecutableAssertion(Executable executable) {
		this.executable = requireNonNull(executable);
	}

	public ExecutableAssertion doesThrow() {
		return this;
	}
}
