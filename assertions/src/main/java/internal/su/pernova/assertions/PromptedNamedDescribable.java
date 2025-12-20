package internal.su.pernova.assertions;

import su.pernova.assertions.Description;

/**
 * This class adds an optional prompt to a named describable.
 * A prompt precedes an argument, and is typically something like ":".
 */
public abstract class PromptedNamedDescribable extends NamedDescribable {

	private final boolean prompt;

	protected PromptedNamedDescribable(CharSequence name, boolean prompt) {
		super(name);
		this.prompt = prompt;
	}

	public boolean prompt() {
		return prompt;
	}

	@Override
	public Description describe(Description description) {
		description = super.describe(description);
		if (prompt) {
			description.appendPrompt();
		}
		return description;
	}
}
