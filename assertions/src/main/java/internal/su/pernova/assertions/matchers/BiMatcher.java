package internal.su.pernova.assertions.matchers;

import static java.util.Objects.requireNonNull;

import static internal.su.pernova.assertions.DescribableUtils.getDefaultName;
import static internal.su.pernova.assertions.DescribableUtils.toLowerCamelCase;

import su.pernova.assertions.Context;
import su.pernova.assertions.Description;
import su.pernova.assertions.Matcher;

/**
 * A forked matcher combines a left and right destination.
 * Upon contextualization, this matches forks the left matcher's context into the right.
 * Consider for example {@code equalTo(1).or(2)}.
 * Such a statement returns a forked "or" matcher that matches the "equal to" context of the left "equal to 1" into the right contextless "2" matcher.
 * "2" alone is not enough to match anything, but the "equal to" context the "2" was forked from provides the context so that it effectively means "equal to 1 or (equal to) 2".
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
