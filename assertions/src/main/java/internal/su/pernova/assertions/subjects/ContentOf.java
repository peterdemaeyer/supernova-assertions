package internal.su.pernova.assertions.subjects;

import su.pernova.assertions.Context;
import su.pernova.assertions.Description;
import su.pernova.assertions.Subject;

public class ContentOf extends ObjectSubject {

	public ContentOf(Object actualValue) {
		super("content", actualValue);
	}

	@Override
	public Description describe(Description description) {
		return super.describe(description).appendPrompt().appendArgument(actualValue);
	}

	@Override
	public Subject contextualize(Context context) {
		return context.forwardValueContextualizer(Content::of)
				.contextualize(this, actualValue, ContentOf::new);
	}
}
