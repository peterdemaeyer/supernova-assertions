package internal.su.pernova.assertions.matchers;

import static java.util.Objects.requireNonNull;

import static internal.su.pernova.assertions.DescribableUtils.getDefaultName;
import static internal.su.pernova.assertions.DescribableUtils.toLowerCamelCase;

import su.pernova.assertions.Context;
import su.pernova.assertions.Description;
import su.pernova.assertions.Matcher;

/**
 * An abstract matcher which combines two other "left" and "right" matchers from left to right.
 */
public abstract class BiMatcher implements Matcher {

	protected final Matcher left;

	protected final Matcher right;

	public BiMatcher(Matcher left, Matcher right) {
		this.left = requireNonNull(left, "left is null");
		this.right = requireNonNull(right, "right is null");
		Context.set(this).forward(left, right).reroute(left);
	}

	@Override
	public Description describe(Description description) {
		return right.describe(Matcher.super.describe(left.describe(description)));
	}

	@Override
	public String toString() {
		return toLowerCamelCase(getDefaultName(this)) + "{" + left.toString() + "}{" + right.toString() + "}";
	}
}
