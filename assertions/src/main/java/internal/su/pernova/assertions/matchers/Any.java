package internal.su.pernova.assertions.matchers;

import static internal.su.pernova.assertions.matchers.CompositeMatcher.apply;

public final class Any {

	private Any() {
	}

	public static ContextSensitiveMatcher of(Object... expected) {
		return new ContextSensitiveMatcher(
				context -> new AnyOf("[", ", ", "]", apply(context, expected))
		);
	}

	public static ContextSensitiveMatcher of(double... expected) {
		return new ContextSensitiveMatcher(
				context -> new AnyOf("[", ", ", "]", apply(context, expected))
		);
	}

	public static ContextSensitiveMatcher of(float... expected) {
		return new ContextSensitiveMatcher(
				context -> new AnyOf("[", ", ", "]", apply(context, expected))
		);
	}

	public static ContextSensitiveMatcher of(long... expected) {
		return new ContextSensitiveMatcher(
				context -> new AnyOf("[", ", ", "]", apply(context, expected))
		);
	}

	public static ContextSensitiveMatcher of(int... expected) {
		return new ContextSensitiveMatcher(
				context -> new AnyOf("[", ", ", "]", apply(context, expected))
		);
	}

	public static ContextSensitiveMatcher of(short... expected) {
		return new ContextSensitiveMatcher(
				context -> new AnyOf("[", ", ", "]", apply(context, expected))
		);
	}

	public static ContextSensitiveMatcher of(byte... expected) {
		return new ContextSensitiveMatcher(
				context -> new AnyOf("[", ", ", "]", apply(context, expected))
		);
	}

	public static ContextSensitiveMatcher of(char... expected) {
		return new ContextSensitiveMatcher(
				context -> new AnyOf("[", ", ", "]", apply(context, expected))
		);
	}

	public static ContextSensitiveMatcher of(boolean... expected) {
		return new ContextSensitiveMatcher(
				context -> new AnyOf("[", ", ", "]", apply(context, expected))
		);
	}
}
