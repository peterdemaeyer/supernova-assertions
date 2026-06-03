package internal.su.pernova.assertions.matchers;

import static java.util.Objects.requireNonNull;

import static internal.su.pernova.assertions.DescribableUtils.getDefaultName;
import static internal.su.pernova.assertions.DescribableUtils.toLowerCamelCase;

import su.pernova.assertions.Context;
import su.pernova.assertions.Description;
import su.pernova.assertions.Matcher;

/**
 * A which combines two other "left" and "right" matchers from left to right.
 */
public abstract class BiMatcher implements Matcher {

	protected final Matcher destination1;

	protected final Matcher destination2;

	public BiMatcher(Matcher destination1, Matcher destination2) {
		this.destination1 = requireNonNull(destination1, "destination 1 is null");
		this.destination2 = requireNonNull(destination2, "destination 2 is null");
	}

	@Override
	public Description describe(Description description) {
		return destination2.describe(Matcher.super.describe(destination1.describe(description)));
	}

	@Override
	public String toString() {
		return toLowerCamelCase(getDefaultName(this)) + "{" + destination1.toString() + "}{" + destination2.toString() + "}";
	}

	@Override
	public final Matcher contextualize(Context context) {
		return context.fork(this, destination1, destination2, this::newInstance);
	}

	protected abstract Matcher newInstance(Matcher contextualizedDestination1, Matcher contextualizedDestination2);
}
