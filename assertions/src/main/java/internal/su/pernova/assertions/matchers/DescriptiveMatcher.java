package internal.su.pernova.assertions.matchers;

import su.pernova.assertions.Description;
import su.pernova.assertions.Matcher;

public abstract class DescriptiveMatcher implements Matcher {

	protected final CharSequence descriptionText;

	protected DescriptiveMatcher(CharSequence description) {
		this.descriptionText = description;
	}

	@Override
	public Description describe(Description description) {
		return (descriptionText != null) ? description.appendText(" " + descriptionText) : Matcher.super.describe(description);
	}

	@Override
	public Description describeMismatch(Description mismatchDescription) {
		return mismatchDescription.appendText(" was");
	}
}
