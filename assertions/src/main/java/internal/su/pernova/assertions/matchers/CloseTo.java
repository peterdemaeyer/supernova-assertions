package internal.su.pernova.assertions.matchers;

import static java.util.Objects.requireNonNull;

import java.math.BigDecimal;
import java.math.BigInteger;

import su.pernova.assertions.Description;

public class CloseTo extends DescriptiveMatcher {

	private final Number expected;

	private final Number tolerance;

	private final Comparable minimum;

	private final Comparable maximum;

	public CloseTo(Number expected, Number tolerance) {
		super(null);
		this.expected = requireNonNull(expected, "expected is null");
		this.tolerance = requireNonNull(tolerance, "tolerance is null");
		if (!tolerance.getClass().equals(expected.getClass())) {
			throw new IllegalArgumentException(String.format("tolerance is not an instance of %s", expected.getClass().getName()));
		}
		if (expected instanceof Double) {
			minimum = expected.doubleValue() - tolerance.doubleValue();
			maximum = expected.doubleValue() + tolerance.doubleValue();
		} else if (expected instanceof Float) {
			minimum = expected.floatValue() - tolerance.floatValue();
			maximum = expected.floatValue() + tolerance.floatValue();
		} else if (expected instanceof BigDecimal) {
			minimum = ((BigDecimal) expected).subtract((BigDecimal) tolerance);
			maximum = ((BigDecimal) expected).add((BigDecimal) tolerance);
		} else if (expected instanceof Long) {
			minimum = cappedMin(expected.longValue(), tolerance.longValue());
			maximum = cappedMax(expected.longValue(), tolerance.longValue());
		} else if (expected instanceof Integer) {
			minimum = cappedMin(expected.intValue(), tolerance.intValue());
			maximum = cappedMax(expected.intValue(), tolerance.intValue());
		} else if (expected instanceof Short) {
			minimum = cappedMin(expected.shortValue(), tolerance.shortValue());
			maximum = cappedMax(expected.shortValue(), tolerance.shortValue());
		} else if (expected instanceof Byte) {
			minimum = cappedMin(expected.byteValue(), tolerance.byteValue());
			maximum = cappedMax(expected.byteValue(), tolerance.byteValue());
		} else if (expected instanceof BigInteger) {
			minimum = ((BigInteger) expected).subtract((BigInteger) tolerance);
			maximum = ((BigInteger) expected).add((BigInteger) tolerance);
		} else {
			throw new IllegalArgumentException(String.format("illegal number class: %s", expected.getClass().getName()));
		}
	}

	private static long cappedMin(long expected, long tolerance) {
		final long minimum = expected - tolerance;
		return (minimum > expected) ? Long.MIN_VALUE : minimum;
	}

	private static long cappedMax(long expected, long tolerance) {
		final long maximum = expected + tolerance;
		return (maximum < expected) ? Long.MAX_VALUE : maximum;
	}

	private static int cappedMin(int expected, int tolerance) {
		final int minimum = expected - tolerance;
		return (minimum > expected) ? Integer.MIN_VALUE : minimum;
	}

	private static int cappedMax(int expected, int tolerance) {
		final int maximum = expected + tolerance;
		return (maximum < expected) ? Integer.MAX_VALUE : maximum;
	}

	private static short cappedMin(short expected, short tolerance) {
		final short minimum = (short) (expected - tolerance);
		return (minimum > expected) ? Short.MIN_VALUE : minimum;
	}

	private static short cappedMax(short expected, short tolerance) {
		final short maximum = (short) (expected + tolerance);
		return (maximum < expected) ? Short.MAX_VALUE : maximum;
	}

	private static byte cappedMin(byte expected, byte tolerance) {
		final byte minimum = (byte) (expected - tolerance);
		return (minimum > expected) ? Byte.MIN_VALUE : minimum;
	}

	private static byte cappedMax(byte expected, byte tolerance) {
		final byte maximum = (byte) (expected + tolerance);
		return (maximum < expected) ? Byte.MAX_VALUE : maximum;
	}

	@Override
	public boolean match(Object actual) {
		return (actual != null) && actual.getClass().equals(expected.getClass()) && (minimum.compareTo(actual) <= 0) && (maximum.compareTo(actual) >= 0);
	}

	@Override
	public Description describe(Description description) {
		return super.describe(description)
				.appendPrompt()
				.appendArgument(expected)
				.appendText(" ± ")
				.appendText(tolerance.toString())
				.appendText(" [" + minimum + ", " + maximum + "]");
	}

	public static Context context(Number tolerance) {
		return expected -> new CloseTo((Number) expected, tolerance);
	}
}
