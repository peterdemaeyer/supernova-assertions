package internal.su.pernova.assertions.matchers;

import static java.util.Objects.requireNonNull;

import su.pernova.assertions.Context;
import su.pernova.assertions.Describable;
import su.pernova.assertions.Description;
import su.pernova.assertions.Matcher;

public abstract class CompositeMatcher<M extends CompositeMatcher<M>> implements Matcher {

	protected final CharSequence startDelimiter;

	protected final CharSequence separator;

	protected final CharSequence endDelimiter;

	protected final Matcher[] delegates;

	public CompositeMatcher(CharSequence startDelimiter, CharSequence separator, CharSequence endDelimiter, Matcher... delegates) {
		this.delegates = requireNonNull(delegates, "array of delegates is null");
		this.startDelimiter = startDelimiter;
		this.separator = separator;
		this.endDelimiter = endDelimiter;
	}

	@Override
	public Description describe(Description description) {
		Matcher.super.describe(description).appendPrompt().appendText(startDelimiter);
		int index = 0;
		for (Matcher delegate : delegates) {
			if (index++ > 0) {
				description.appendText(separator);
			}
			delegate.describe(description);
		}
		return description.appendText(endDelimiter);
	}

	@SuppressWarnings("unchecked")
	public M contextualize() {
		return (M) Context.set(this).forward(delegates).get();
	}
}
