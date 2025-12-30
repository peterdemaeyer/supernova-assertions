package internal.su.pernova.assertions.matchers;

import static java.util.Objects.requireNonNull;

import static internal.su.pernova.assertions.DescribableUtils.getDefaultName;
import static internal.su.pernova.assertions.DescribableUtils.toLowerCamelCase;

import su.pernova.assertions.Description;
import su.pernova.assertions.Matcher;

/**
 * An abstract matcher which combines two other "left" and "right" matchers from left to right.
 */
public abstract class BiMatcher implements Matcher {

	protected final Matcher leftDestination;

	protected final Matcher rightDestination;

	public BiMatcher(Matcher... destinations) {
		requireNonNull(destinations, "array of destinations is null");
		if (destinations.length != 2) {
			throw new IllegalArgumentException("# destinations is not 2");
		}
		this.leftDestination = requireNonNull(destinations[0], "destination[0] is null");
		this.rightDestination = requireNonNull(destinations[1], "destination[1] is null");
	}

	@Override
	public Description describe(Description description) {
		return rightDestination.describe(Matcher.super.describe(leftDestination.describe(description)));
	}

	@Override
	public String toString() {
		return toLowerCamelCase(getDefaultName(this)) + "{" + leftDestination.toString() + "}{" + rightDestination.toString() + "}";
	}
}
