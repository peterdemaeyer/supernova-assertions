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
			minimum = expected.longValue() - tolerance.longValue();
			maximum = expected.longValue() + tolerance.longValue();
		} else if (expected instanceof Integer) {
			minimum = expected.intValue() - tolerance.intValue();
			maximum = expected.intValue() + tolerance.intValue();
		} else if (expected instanceof Short) {
			minimum = (short) (expected.shortValue() - tolerance.shortValue());
			maximum = (short) (expected.shortValue() + tolerance.shortValue());
		} else if (expected instanceof Byte) {
			minimum = (byte) (expected.byteValue() - tolerance.byteValue());
			maximum = (byte) (expected.byteValue() + tolerance.byteValue());
		} else if (expected instanceof BigInteger) {
			minimum = ((BigInteger) expected).subtract((BigInteger) tolerance);
			maximum = ((BigInteger) expected).add((BigInteger) tolerance);
		} else {
			throw new IllegalArgumentException(String.format("illegal number class: %s", expected.getClass().getName()));
		}
	}

	@Override
	public boolean match(Object actual) {
		return (actual != null) && actual.getClass().equals(expected.getClass()) && (minimum.compareTo(actual) <= 0) && (maximum.compareTo(actual) >= 0);
	}

	@Override
	public Description describe(Description description) {
		return super.describe(description)
				.appendArgument(expected)
				.appendText(" ± ")
				.appendText(tolerance.toString())
				.appendText(" [" + minimum + ", " + maximum + "]");
	}
}
