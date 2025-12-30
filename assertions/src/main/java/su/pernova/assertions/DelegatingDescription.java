package su.pernova.assertions;

import static java.util.Objects.requireNonNull;

public class DelegatingDescription implements Description {

	private final Description delegatee;

	public DelegatingDescription(Description delegatee) {
		this.delegatee = requireNonNull(delegatee, "delegatee is null");
	}

	@Override
	public Description appendText(CharSequence text) {
		return delegatee.appendText(text);
	}

	@Override
	public Description appendPrompt() {
		return delegatee.appendPrompt();
	}

	@Override
	public Description appendExpectedValue(Object expectedValue) {
		return delegatee.appendExpectedValue(expectedValue);
	}

	@Override
	public Description appendActualValue(Object actualValue) {
		return delegatee.appendActualValue(actualValue);
	}

	@Override
	public Description appendArgument(Object argument) {
		return delegatee.appendArgument(argument);
	}

	@Override
	public Object getExpectedValue() {
		return delegatee.getExpectedValue();
	}

	@Override
	public Object getActualValue() {
		return delegatee.getActualValue();
	}
}
