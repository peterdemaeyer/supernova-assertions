package internal.su.pernova.assertions.matchers;

import static java.util.Arrays.stream;

import su.pernova.assertions.Matcher;

public final class All {

	private All() {
	}

	public static ContextSensitiveMatcher of(Object... expected) {
		return new ContextSensitiveMatcher(
				context -> new AllOf("[", ", ", "]", stream(expected)
						.map(value -> context.apply(value))
						.toArray(size -> new Matcher[size]))
		);
	}

	public static ContextSensitiveMatcher of(double... expected) {
		return new ContextSensitiveMatcher(
				context -> new AllOf("[", ", ", "]", stream(expected)
						.mapToObj(value -> context.apply(value))
						.toArray(size -> new Matcher[size]))
		);
	}
}
