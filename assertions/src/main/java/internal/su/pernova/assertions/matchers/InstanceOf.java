package internal.su.pernova.assertions.matchers;

import static java.util.Objects.requireNonNull;

import su.pernova.assertions.Description;
import su.pernova.assertions.Matcher;
import su.pernova.assertions.MatcherFactory;

public class InstanceOf extends ExpectedValueMatcher {

	public static final MatcherFactory MATCHER_FACTORY = new MatcherFactory() {

		@Override
		public Matcher create(Matcher expectation) {
			return new ForwardingMatcher("instance of", expectation);
		}

		@Override
		public Matcher create(Object expectedValue) {
			return new InstanceOf((Class) expectedValue);
		}
	};

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
}
