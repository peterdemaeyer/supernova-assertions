package internal.su.pernova.assertions.matchers;

import static java.lang.System.lineSeparator;
import static java.util.Objects.requireNonNull;

import internal.su.pernova.assertions.DefaultDescribable;
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
	}

	public CompositeMatcher(Matcher... delegates) {
		this(lineSeparator() + "\t", lineSeparator() + "\t", "", delegates);
	}

	@Override
	public Description describe(Description description) {
		super.describe(description).appendPrompt().appendText(startDelimiter);
		int i = 0;
		for (Matcher delegate : delegates) {
			if (i++ > 0) {
				description.appendText(separator);
			}
			delegate.describe(description);
		}
		return description.appendText(endDelimiter);
	}
}
