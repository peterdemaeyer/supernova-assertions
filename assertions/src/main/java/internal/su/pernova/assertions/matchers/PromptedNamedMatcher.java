package internal.su.pernova.assertions.matchers;

import internal.su.pernova.assertions.PromptedNamedDescribable;
import su.pernova.assertions.Matcher;

public abstract class PromptedNamedMatcher extends PromptedNamedDescribable implements Matcher {

	protected PromptedNamedMatcher(CharSequence name, boolean prompt) {
		super(name, prompt);
	}
}
