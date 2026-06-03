package internal.su.pernova.assertions.matchers;

public abstract class ExpectedValueMatcher extends PromptedNamedMatcher {

	protected ExpectedValueMatcher(CharSequence name, boolean prompt) {
		super(name, prompt);
	}

	@Override
	public boolean match(Object actualValue) {
		throw new IllegalStateException("no context");
	}
}
