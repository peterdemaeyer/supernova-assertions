package su.pernova.assertions;

import static java.util.Objects.requireNonNull;

/**
 * @since 2.0.0
 */
public abstract class DefaultMatcherProvider<E> implements MatcherProvider {

	protected final Descriptor subjectDescriptor;

	protected final Descriptor matcherDescriptor;

	protected final Class<E> expectedValueClass;

	protected final boolean nullable;

	/**
	 * @param subjectDescriptor an optional subject descriptor.
	 * @param matcherDescriptor an optional matcher descriptor.
	 * @param expectedValueClass an expected value class, not {@code null}.
	 * @param nullable whether the expected value may be {@code null}.
	 */
	protected DefaultMatcherProvider(Descriptor subjectDescriptor, Descriptor matcherDescriptor, Class<E> expectedValueClass, boolean nullable) {
		this.subjectDescriptor = subjectDescriptor;
		this.matcherDescriptor = matcherDescriptor;
		this.expectedValueClass = requireNonNull(expectedValueClass, "expected value class is null");
		this.nullable = nullable;
	}

	@Override
	public Matcher provide(Descriptor subjectDescriptor, Descriptor matcherDescriptor, Object expectedValue, Object... parameters) {
		if (filter(subjectDescriptor, matcherDescriptor, expectedValue)) {
			return provide(expectedValueClass.cast(expectedValue), parameters);
		}
		return null;
	}

	protected abstract Matcher provide(E expectedValue, Object... parameters);

	@Override
	public Matcher provide(Descriptor subjectDescriptor, Descriptor matcherDescriptor, double expectedValue, Object... parameters) {
		if (filter(subjectDescriptor, matcherDescriptor, expectedValue)) {
			return provide(expectedValue, parameters);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	protected Matcher provide(double expectedValue, Object... parameters) {
		return provide((E) (Double) expectedValue, parameters);
	}

	@Override
	public Matcher provide(Descriptor subjectDescriptor, Descriptor matcherDescriptor, float expectedValue, Object... parameters) {
		if (filter(subjectDescriptor, matcherDescriptor, expectedValue)) {
			return provide(expectedValue, parameters);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	protected Matcher provide(float expectedValue, Object... parameters) {
		return provide((E) (Float) expectedValue, parameters);
	}

	@Override
	public Matcher provide(Descriptor subjectDescriptor, Descriptor matcherDescriptor, long expectedValue, Object... parameters) {
		if (filter(subjectDescriptor, matcherDescriptor, expectedValue)) {
			return provide(expectedValue, parameters);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	protected Matcher provide(long expectedValue, Object... parameters) {
		return provide((E) (Long) expectedValue, parameters);
	}

	@Override
	public Matcher provide(Descriptor subjectDescriptor, Descriptor matcherDescriptor, int expectedValue, Object... parameters) {
		if (filter(subjectDescriptor, matcherDescriptor, expectedValue)) {
			return provide(expectedValue, parameters);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	protected Matcher provide(int expectedValue, Object... parameters) {
		return provide((E) (Integer) expectedValue, parameters);
	}

	@Override
	public Matcher provide(Descriptor subjectDescriptor, Descriptor matcherDescriptor, short expectedValue, Object... parameters) {
		if (filter(subjectDescriptor, matcherDescriptor, expectedValue)) {
			return provide(expectedValue, parameters);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	protected Matcher provide(short expectedValue, Object... parameters) {
		return provide((E) (Short) expectedValue, parameters);
	}

	@Override
	public Matcher provide(Descriptor subjectDescriptor, Descriptor matcherDescriptor, byte expectedValue, Object... parameters) {
		if (filter(subjectDescriptor, matcherDescriptor, expectedValue)) {
			return provide(expectedValue, parameters);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	protected Matcher provide(byte expectedValue, Object... parameters) {
		return provide((E) (Byte) expectedValue, parameters);
	}

	@Override
	public Matcher provide(Descriptor subjectDescriptor, Descriptor matcherDescriptor, char expectedValue, Object... parameters) {
		if (filter(subjectDescriptor, matcherDescriptor, expectedValue)) {
			return provide(expectedValue, parameters);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	protected Matcher provide(char expectedValue, Object... parameters) {
		return provide((E) (Character) expectedValue, parameters);
	}

	@Override
	public Matcher provide(Descriptor subjectDescriptor, Descriptor matcherDescriptor, boolean expectedValue, Object... parameters) {
		if (filter(subjectDescriptor, matcherDescriptor, expectedValue)) {
			return provide(expectedValue, parameters);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	protected Matcher provide(boolean expectedValue, Object... parameters) {
		return provide((E) (Boolean) expectedValue, parameters);
	}

	private boolean filter(Descriptor subjectDescriptor, Descriptor matcherDescriptor, Object expectedValue) {
		return subjectDescriptor.equals(this.subjectDescriptor) && matcherDescriptor.equals(this.matcherDescriptor)
				&& ((expectedValue == null && nullable) || expectedValueClass.isInstance(expectedValue));
	}
}
