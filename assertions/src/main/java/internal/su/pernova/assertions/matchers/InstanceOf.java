package internal.su.pernova.assertions.matchers;

import static java.util.Objects.requireNonNull;

import static internal.su.pernova.assertions.DescribableUtils.getDefaultName;

import su.pernova.assertions.Context;
import su.pernova.assertions.Description;
import su.pernova.assertions.Matcher;
import su.pernova.assertions.MatcherFactory;

public class InstanceOf extends ExpectedValueMatcher {

	public static final CharSequence NAME = getDefaultName(InstanceOf.class);

	@SuppressWarnings("rawtypes")
	public static final MatcherFactory MATCHER_FACTORY = expectedValue -> new InstanceOf((Class) expectedValue);

	private final Class<?> expectedClass;

	public InstanceOf(CharSequence description, boolean prompt, Class<?> expectedClass) {
		super(description, prompt);
		this.expectedClass = requireNonNull(expectedClass, "expected class is null");
	}

	public InstanceOf(Class<?> expectedClass) {
		this(null, true, expectedClass);
	}

	@Override
	public boolean match(Object actualValue) {
		return expectedClass.isInstance(actualValue);
	}

	@Override
	public Description describe(Description description) {
		return super.describe(description).appendArgument(expectedClass);
	}

	@Override
	public String toString() {
		return super.toString() + "(" + expectedClass.getName() + ")";
	}

	@Override
	public Matcher contextualize(Context context) {
		return context.contextualize(this, MATCHER_FACTORY, expectedClass);
	}
}
