package internal.su.pernova.assertions.matchers;

import static java.util.Objects.requireNonNull;

import su.pernova.assertions.Description;
import su.pernova.assertions.Matcher;

/**
 * An abstract matcher which combines two other "left" and "right" matchers from left to right.
 */
public abstract class BiMatcher extends DescriptiveMatcher {

	protected final Matcher left;

	protected final Matcher right;

	public BiMatcher(CharSequence description, Matcher left, Matcher right) {
		super(description);
		this.left = requireNonNull(left, "left is null");
		this.right = requireNonNull(right, "right is null");
	}

	@Override
	public Description describe(Description description) {
		return right.describe(super.describe(left.describe(description)));
	}
}
