package internal.su.pernova.assertions.matchers;

import static java.util.Arrays.stream;

import su.pernova.assertions.Matcher;

public final class Any {

	private Any() {
	}

	public static ContextSensitiveMatcher of(Object... expected) {
		return new ContextSensitiveMatcher(
				context -> new AnyOf("[", ", ", "]", stream(expected)
						.map(value -> context.apply(value))
						.toArray(size -> new Matcher[size]))
		);
	}

	public static ContextSensitiveMatcher of(double... expected) {
		return new ContextSensitiveMatcher(
				context -> new AnyOf("[", ", ", "]", stream(expected)
						.mapToObj(value -> context.apply(value))
						.toArray(size -> new Matcher[size]))
		);
	}
}
