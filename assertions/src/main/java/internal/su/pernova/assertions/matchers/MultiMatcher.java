package internal.su.pernova.assertions.matchers;

import static java.util.Arrays.stream;
import static java.util.Objects.requireNonNull;

import static internal.su.pernova.assertions.DescribableUtils.getDefaultName;
import static internal.su.pernova.assertions.DescribableUtils.toLowerCamelCase;

import su.pernova.assertions.Context;
import su.pernova.assertions.DelegatingDescription;
import su.pernova.assertions.Description;
import su.pernova.assertions.Matcher;

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

	public MultiMatcher(Matcher... destinations) {
		this("[", ", ", "]", destinations);
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

	public static ObjectMatcher[] toMatchers(Object[] expectedValues) {
		return stream(requireNonNull(expectedValues, "array of expected values is null"))
				.map(v -> new ObjectMatcher("", false, v))
				.toArray(ObjectMatcher[]::new);
	}

	public static DoubleMatcher[] toMatchers(double[] expectedValues) {
		return stream(requireNonNull(expectedValues, "array of expected values is null"))
				.mapToObj(v -> new DoubleMatcher("", false, v))
				.toArray(DoubleMatcher[]::new);
	}

	public static FloatMatcher[] toMatchers(float[] expectedValues) {
		requireNonNull(expectedValues, "array of expected values is null");
		final FloatMatcher[] matchers = new FloatMatcher[expectedValues.length];
		for (int i = 0, n = matchers.length; i != n; i++) {
			matchers[i] = new FloatMatcher("", false, expectedValues[i]);
		}
		return matchers;
	}

	public static LongMatcher[] toMatchers(long[] expectedValues) {
		return stream(requireNonNull(expectedValues, "array of expected values is null"))
				.mapToObj(v -> new LongMatcher("", false, v))
				.toArray(LongMatcher[]::new);
	}

	public static IntMatcher[] toMatchers(int[] expectedValues) {
		return stream(requireNonNull(expectedValues, "array of expected values is null"))
				.mapToObj(v -> new IntMatcher("", false, v))
				.toArray(IntMatcher[]::new);
	}

	public static ShortMatcher[] toMatchers(short[] expectedValues) {
		requireNonNull(expectedValues, "array of expected values is null");
		final ShortMatcher[] matchers = new ShortMatcher[expectedValues.length];
		for (int i = 0, n = matchers.length; i != n; i++) {
			matchers[i] = new ShortMatcher("", false, expectedValues[i]);
		}
		return matchers;
	}

	public static ByteMatcher[] toMatchers(byte[] expectedValues) {
		requireNonNull(expectedValues, "array of expected values is null");
		final ByteMatcher[] matchers = new ByteMatcher[expectedValues.length];
		for (int i = 0, n = matchers.length; i != n; i++) {
			matchers[i] = new ByteMatcher("", false, expectedValues[i]);
		}
		return matchers;
	}

	public static CharMatcher[] toMatchers(char[] expectedValues) {
		requireNonNull(expectedValues, "array of expected values is null");
		final CharMatcher[] matchers = new CharMatcher[expectedValues.length];
		for (int i = 0, n = matchers.length; i != n; i++) {
			matchers[i] = new CharMatcher("", false, expectedValues[i]);
		}
		return matchers;
	}

	public static BooleanMatcher[] toMatchers(boolean[] expectedValues) {
		requireNonNull(expectedValues, "array of expected values is null");
		final BooleanMatcher[] matchers = new BooleanMatcher[expectedValues.length];
		for (int i = 0, n = matchers.length; i != n; i++) {
			matchers[i] = new BooleanMatcher("", false, expectedValues[i]);
		}
		return matchers;
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

	@Override
	public Matcher contextualize(Context context) {
		return context.resolve(this, destinations, this::newInstance);
	}

	protected abstract MultiMatcher newInstance(Matcher[] contextualizedDestinations);
}
