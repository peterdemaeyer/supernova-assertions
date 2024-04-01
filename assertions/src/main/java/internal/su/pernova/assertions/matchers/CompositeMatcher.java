package internal.su.pernova.assertions.matchers;

import static java.lang.System.lineSeparator;
import static java.util.Arrays.stream;
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

	static Matcher[] apply(Context context, Object... values) {
		return stream(values).map(context::apply).toArray(Matcher[]::new);
	}

	static Matcher[] apply(Context context, double... values) {
		return stream(values).mapToObj(context::apply).toArray(Matcher[]::new);
	}

	static Matcher[] apply(Context context, float... values) {
		final Matcher[] matchers = new Matcher[values.length];
		int i = 0;
		for (float value : values) {
			matchers[i++] = context.apply(value);
		}
		return matchers;
	}

	static Matcher[] apply(Context context, long... values) {
		return stream(values).mapToObj(context::apply).toArray(Matcher[]::new);
	}

	static Matcher[] apply(Context context, int... values) {
		return stream(values).mapToObj(context::apply).toArray(Matcher[]::new);
	}

	static Matcher[] apply(Context context, short... values) {
		final Matcher[] matchers = new Matcher[values.length];
		int i = 0;
		for (short value : values) {
			matchers[i++] = context.apply(value);
		}
		return matchers;
	}

	static Matcher[] apply(Context context, byte... values) {
		final Matcher[] matchers = new Matcher[values.length];
		int i = 0;
		for (byte value : values) {
			matchers[i++] = context.apply(value);
		}
		return matchers;
	}

	static Matcher[] apply(Context context, char... values) {
		final Matcher[] matchers = new Matcher[values.length];
		int i = 0;
		for (char value : values) {
			matchers[i++] = context.apply(value);
		}
		return matchers;
	}

	static Matcher[] apply(Context context, boolean... values) {
		final Matcher[] matchers = new Matcher[values.length];
		int i = 0;
		for (boolean value : values) {
			matchers[i++] = context.apply(value);
		}
		return matchers;
	}
}
