package internal.su.pernova.assertions.matchers;

import su.pernova.assertions.Description;

public abstract class PromptDescriptiveMatcher extends DescriptiveMatcher {

	private boolean prompt;

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
