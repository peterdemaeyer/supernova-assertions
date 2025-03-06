package internal.su.pernova.assertions.matchers;

import internal.su.pernova.assertions.DefaultDescribable;
import su.pernova.assertions.Description;
import su.pernova.assertions.Matcher;

/**
 * This class describes a matcher with an optional custom description.
 * When there is no custom description, the default description is given by
 * {@link su.pernova.assertions.Describable#describe(Description)}.
 */
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

	/**
	 * Appends "was" to a given mismatch description and returns the same mismatch description.
	 *
	 * @param mismatchDescription a mismatch description, not {@code null}.
	 * @return the mismatch description which is given as parameter.
	 */
	@Override
	public Description describeMismatch(Description mismatchDescription) {
		return mismatchDescription.appendText("was");
	}
}
