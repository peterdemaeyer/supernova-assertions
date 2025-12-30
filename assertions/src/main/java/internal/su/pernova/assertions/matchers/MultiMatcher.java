package internal.su.pernova.assertions.matchers;

import static java.util.Arrays.stream;
import static java.util.Objects.requireNonNull;

import static internal.su.pernova.assertions.DescribableUtils.getDefaultName;
import static internal.su.pernova.assertions.DescribableUtils.toLowerCamelCase;

import su.pernova.assertions.DelegatingDescription;
import su.pernova.assertions.Description;
import su.pernova.assertions.Matcher;
import su.pernova.assertions.MatcherFactory;

public abstract class MultiMatcher implements Matcher {

	protected final CharSequence startDelimiter;

	protected final CharSequence separator;

	protected final CharSequence endDelimiter;

	protected final Matcher[] destinations;

	public MultiMatcher(CharSequence startDelimiter, CharSequence separator, CharSequence endDelimiter, Matcher... destinations) {
		this.destinations = requireNonNull(destinations, "array of destinations is null");
		this.startDelimiter = startDelimiter;
		this.separator = separator;
		this.endDelimiter = endDelimiter;
	}

	@Override
	public Description describe(Description description) {
		Matcher.super.describe(description).appendPrompt().appendText(startDelimiter);
		Description inhibitedDescription = new DelegatingDescription(description) {

			@Override
			public Description appendText(CharSequence text) {
				return this; // Inhibit text.
			}

			@Override
			public Description appendPrompt() {
				return this; // Inhibit prompt.
			}
		};
		int index = 0;
		for (Matcher delegate : destinations) {
			if (index++ > 0) {
				description.appendText(separator);
			}
			delegate.describe(inhibitedDescription);
		}
		return description.appendText(endDelimiter);
	}

	public static Matcher create(Constructor constructor, MatcherFactory matcherFactory, Object[] expectedValues) {
		return createSingleLine(constructor, stream(expectedValues).map(matcherFactory::create).toArray(Matcher[]::new));
	}

	public static Matcher create(Constructor constructor, MatcherFactory matcherFactory, double[] expectedValues) {
		return createSingleLine(constructor, stream(expectedValues).mapToObj(matcherFactory::create).toArray(Matcher[]::new));
	}

	public static Matcher create(Constructor constructor, MatcherFactory matcherFactory, float[] expectedValues) {
		final Matcher[] matchers = new Matcher[expectedValues.length];
		for (int i = 0, n = matchers.length; i != n; i++) {
			matchers[i] = matcherFactory.create(expectedValues[i]);
		}
		return createSingleLine(constructor, matchers);
	}

	public static Matcher create(Constructor constructor, MatcherFactory matcherFactory, long[] expectedValues) {
		return createSingleLine(constructor, stream(expectedValues).mapToObj(matcherFactory::create).toArray(Matcher[]::new));
	}

	public static Matcher create(Constructor constructor, MatcherFactory matcherFactory, int[] expectedValues) {
		return createSingleLine(constructor, stream(expectedValues).mapToObj(matcherFactory::create).toArray(Matcher[]::new));
	}

	public static Matcher create(Constructor constructor, MatcherFactory matcherFactory, short[] expectedValues) {
		final Matcher[] matchers = new Matcher[expectedValues.length];
		for (int i = 0, n = matchers.length; i != n; i++) {
			matchers[i] = matcherFactory.create(expectedValues[i]);
		}
		return createSingleLine(constructor, matchers);
	}

	public static Matcher create(Constructor constructor, MatcherFactory matcherFactory, byte[] expectedValues) {
		final Matcher[] matchers = new Matcher[expectedValues.length];
		for (int i = 0, n = matchers.length; i != n; i++) {
			matchers[i] = matcherFactory.create(expectedValues[i]);
		}
		return createSingleLine(constructor, matchers);
	}

	public static Matcher create(Constructor constructor, MatcherFactory matcherFactory, char[] expectedValues) {
		final Matcher[] matchers = new Matcher[expectedValues.length];
		for (int i = 0, n = matchers.length; i != n; i++) {
			matchers[i] = matcherFactory.create(expectedValues[i]);
		}
		return createSingleLine(constructor, matchers);
	}

	public static Matcher create(Constructor constructor, MatcherFactory matcherFactory, boolean[] expectedValues) {
		final Matcher[] matchers = new Matcher[expectedValues.length];
		for (int i = 0, n = matchers.length; i != n; i++) {
			matchers[i] = matcherFactory.create(expectedValues[i]);
		}
		return createSingleLine(constructor, matchers);
	}

	private static Matcher createSingleLine(Constructor constructor, Matcher... matchers) {
		return constructor.construct("[", ", ", "]", matchers);
	}

	public interface Constructor {

		Matcher construct(CharSequence startDelimiter, CharSequence separator, CharSequence endDelimiter, Matcher... matchers);
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder(toLowerCamelCase(getDefaultName(this))).append('{');
		for (int i = 0, n = destinations.length; i != n; i++) {
			if (i != 0) {
				builder.append("}{");
			}
			builder.append(destinations[i].toString());
		}
		return builder.append('}').toString();
	}
}
