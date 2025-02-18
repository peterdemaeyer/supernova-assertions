package internal.su.pernova.assertions.matchers;

import static internal.su.pernova.assertions.matchers.CompositeMatcher.apply;

import su.pernova.assertions.Matcher;

public class Or extends BiMatcher {

	public Or(Matcher left, Matcher right) {
		this(null, left, right);
	}

	public Or(CharSequence description, Matcher left, Matcher right) {
		super(description, left, right);
	}

	@Override
	public boolean match(Object actual) {
		return left.match(actual) || right.match(actual);
	}

	public static ContextSensitiveMatcher of(Matcher left, Object expected) {
		return new ContextSensitiveMatcher(
				context -> new Or("or:", left, apply(context, expected)[0])
		);
	}

	public static ContextSensitiveMatcher of(Matcher left, double expected) {
		return new ContextSensitiveMatcher(
				context -> new Or("or:", left, apply(context, new double[] { expected })[0])
		);
	}

	public static ContextSensitiveMatcher of(Matcher left, float expected) {
		return new ContextSensitiveMatcher(
				context -> new Or("or:", left, apply(context, new float[] { expected })[0])
		);
	}

	public static ContextSensitiveMatcher of(Matcher left, long expected) {
		return new ContextSensitiveMatcher(
				context -> new Or("or:", left, apply(context, new long[] { expected })[0])
		);
	}

	public static ContextSensitiveMatcher of(Matcher left, int expected) {
		return new ContextSensitiveMatcher(
				context -> new Or("or:", left, apply(context, new int[] { expected })[0])
		);
	}

	public static ContextSensitiveMatcher of(Matcher left, short expected) {
		return new ContextSensitiveMatcher(
				context -> new Or("or:", left, apply(context, new short[] { expected })[0])
		);
	}

	public static ContextSensitiveMatcher of(Matcher left, byte expected) {
		return new ContextSensitiveMatcher(
				context -> new Or("or:", left, apply(context, new byte[] { expected })[0])
		);
	}

	public static ContextSensitiveMatcher of(Matcher left, char expected) {
		return new ContextSensitiveMatcher(
				context -> new Or("or:", left, apply(context, new char[] { expected })[0])
		);
	}

	public static ContextSensitiveMatcher of(Matcher left, boolean expected) {
		return new ContextSensitiveMatcher(
				context -> new Or("or:", left, apply(context, new boolean[] { expected })[0])
		);
	}
}
