package internal.su.pernova.assertions.matchers;

import static java.util.Objects.requireNonNull;

import internal.su.pernova.assertions.DefaultDescribable;
import su.pernova.assertions.Context;
import su.pernova.assertions.Description;
import su.pernova.assertions.Matcher;

public abstract class CompositeMatcher extends DefaultDescribable implements Matcher {

	protected final CharSequence startDelimiter;

	protected final CharSequence separator;

	protected final CharSequence endDelimiter;

	protected final Matcher[] delegates;

	public CompositeMatcher(CharSequence startDelimiter, CharSequence separator, CharSequence endDelimiter, Matcher... delegates) {
		this.delegates = requireNonNull(delegates, "array of delegates is null");
		this.startDelimiter = startDelimiter;
		this.separator = separator;
		this.endDelimiter = endDelimiter;
		Context.set(this).forwardTo(delegates);
	}

	@Override
	public Description describe(Description description) {
		super.describe(description).appendPrompt().appendText(startDelimiter);
		int index = 0;
		for (Matcher delegate : delegates) {
			if (index++ > 0) {
				description.appendText(separator);
			}
			delegate.describe(description);
		}
		return description.appendText(endDelimiter);
	}
}
