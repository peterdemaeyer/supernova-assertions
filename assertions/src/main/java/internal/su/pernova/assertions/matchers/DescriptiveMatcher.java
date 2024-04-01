package internal.su.pernova.assertions.matchers;

import internal.su.pernova.assertions.DefaultDescribable;
import su.pernova.assertions.Description;
import su.pernova.assertions.Matcher;

public abstract class DescriptiveMatcher extends DefaultDescribable implements Matcher {

	protected final CharSequence customDescription;

	protected DescriptiveMatcher(CharSequence description) {
		customDescription = description;
	}

	@Override
	public Description describe(Description description) {
		return (customDescription != null)
				? ((customDescription.length() > 0)
				? description.appendText(customDescription)
				: description)
				: Matcher.super.describe(description);
	}

	@Override
	public Description describeMismatch(Description mismatchDescription) {
		return mismatchDescription.appendText("was");
	}
}
