package internal.su.pernova.assertions.matchers;

import su.pernova.assertions.Description;

/**
 * This class adds an optional prompt to a descriptive matcher.
 * A prompt precedes an argument, and is typically something like ":".
 */
public abstract class PromptDescriptiveMatcher extends DescriptiveMatcher {

	private final boolean prompt;

	protected PromptDescriptiveMatcher(CharSequence description, boolean prompt) {
		super(description);
		this.prompt = prompt;
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
