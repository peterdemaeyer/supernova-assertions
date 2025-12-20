package internal.su.pernova.assertions.matchers;

import static java.util.Objects.requireNonNull;

import java.math.BigDecimal;
import java.math.BigInteger;

import su.pernova.assertions.Description;

public class CloseTo extends ExpectedValueMatcher {

	private final Number expectedValue;

	private final Number tolerance;

	@SuppressWarnings("rawtypes")
	private final Comparable minimum;

	@SuppressWarnings("rawtypes")
	private final Comparable maximum;

	public CloseTo(Number expectedValue, Number tolerance) {
		super(null, true);
		this.expectedValue = requireNonNull(expectedValue, "expected value is null");
		this.tolerance = requireNonNull(tolerance, "tolerance is null");
		if (!tolerance.getClass().equals(expectedValue.getClass())) {
			throw new IllegalArgumentException(String.format("tolerance is not an instance of %s", expectedValue.getClass().getName()));
		}
		if (expectedValue instanceof Double) {
			minimum = expectedValue.doubleValue() - tolerance.doubleValue();
			maximum = expectedValue.doubleValue() + tolerance.doubleValue();
		} else if (expectedValue instanceof Float) {
			minimum = expectedValue.floatValue() - tolerance.floatValue();
			maximum = expectedValue.floatValue() + tolerance.floatValue();
		} else if (expectedValue instanceof BigDecimal) {
			minimum = ((BigDecimal) expectedValue).subtract((BigDecimal) tolerance);
			maximum = ((BigDecimal) expectedValue).add((BigDecimal) tolerance);
		} else if (expectedValue instanceof Long) {
			minimum = cappedMin(expectedValue.longValue(), tolerance.longValue());
			maximum = cappedMax(expectedValue.longValue(), tolerance.longValue());
		} else if (expectedValue instanceof Integer) {
			minimum = cappedMin(expectedValue.intValue(), tolerance.intValue());
			maximum = cappedMax(expectedValue.intValue(), tolerance.intValue());
		} else if (expectedValue instanceof Short) {
			minimum = cappedMin(expectedValue.shortValue(), tolerance.shortValue());
			maximum = cappedMax(expectedValue.shortValue(), tolerance.shortValue());
		} else if (expectedValue instanceof Byte) {
			minimum = cappedMin(expectedValue.byteValue(), tolerance.byteValue());
			maximum = cappedMax(expectedValue.byteValue(), tolerance.byteValue());
		} else if (expectedValue instanceof BigInteger) {
			minimum = ((BigInteger) expectedValue).subtract((BigInteger) tolerance);
			maximum = ((BigInteger) expectedValue).add((BigInteger) tolerance);
		} else {
			throw new IllegalArgumentException(String.format("illegal number class: %s", expectedValue.getClass().getName()));
		}
	}

	private static long cappedMin(long expectedValue, long tolerance) {
		final long minimum = expectedValue - tolerance;
		return (minimum > expectedValue) ? Long.MIN_VALUE : minimum;
	}

	private static long cappedMax(long expectedValue, long tolerance) {
		final long maximum = expectedValue + tolerance;
		return (maximum < expectedValue) ? Long.MAX_VALUE : maximum;
	}

	private static int cappedMin(int expectedValue, int tolerance) {
		final int minimum = expectedValue - tolerance;
		return (minimum > expectedValue) ? Integer.MIN_VALUE : minimum;
	}

	private static int cappedMax(int expectedValue, int tolerance) {
		final int maximum = expectedValue + tolerance;
		return (maximum < expectedValue) ? Integer.MAX_VALUE : maximum;
	}

	private static short cappedMin(short expectedValue, short tolerance) {
		final short minimum = (short) (expectedValue - tolerance);
		return (minimum > expectedValue) ? Short.MIN_VALUE : minimum;
	}

	private static short cappedMax(short expectedValue, short tolerance) {
		final short maximum = (short) (expectedValue + tolerance);
		return (maximum < expectedValue) ? Short.MAX_VALUE : maximum;
	}

	private static byte cappedMin(byte expectedValue, byte tolerance) {
		final byte minimum = (byte) (expectedValue - tolerance);
		return (minimum > expectedValue) ? Byte.MIN_VALUE : minimum;
	}

	private static byte cappedMax(byte expectedValue, byte tolerance) {
		final byte maximum = (byte) (expectedValue + tolerance);
		return (maximum < expectedValue) ? Byte.MAX_VALUE : maximum;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean match(Object actualValue) {
		return (actualValue != null) && actualValue.getClass().equals(expectedValue.getClass())
				&& (minimum.compareTo(actualValue) <= 0) && (maximum.compareTo(actualValue) >= 0);
	}

	@Override
	public Description describe(Description description) {
		return super.describe(description)
				.appendPrompt()
				.appendArgument(expectedValue)
				.appendText(" ± ")
				.appendText(tolerance.toString())
				.appendText(" [" + minimum + ", " + maximum + "]");
	}
}
