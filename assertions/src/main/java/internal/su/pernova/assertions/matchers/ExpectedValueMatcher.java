package internal.su.pernova.assertions.matchers;

/// Contextless matcher for an expected value.
/// This matcher has no context of its own, it needs another matcher to provide it.
public abstract class ExpectedValueMatcher extends PromptedNamedMatcher {

	protected ExpectedValueMatcher(CharSequence name, boolean prompt) {
		super(name, prompt);
	}

	@Override
	public boolean match(Object actualValue) {
		throw new IllegalStateException("no context");
	}
}
