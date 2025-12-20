package su.pernova.assertions;

import static java.util.Arrays.stream;

import static internal.su.pernova.assertions.MatcherProviders.getMatcher;

import java.util.function.BiFunction;

import internal.su.pernova.assertions.matchers.PromptedNamedMatcher;

/**
 * @since 2.0.0
 */
public class ContextualMatcher extends PromptedNamedMatcher {

	public static final Object[] NO_PARAMETERS = new Object[0];

	private final ThreadLocal<Matcher> delegates = new ThreadLocal<>();

	private ContextualMatcher(Descriptor descriptor, boolean prompt, BiFunction<Descriptor, Descriptor, Matcher> matcherFunction) {
		super(descriptor != null ? descriptor.getName() : "", prompt);
		Context.Setter<ContextualMatcher> setter = Context.set(this).addListener(new Context.Listener() {

			@Override
			public void allSet(Subject subject, Descriptor subjectDescriptor, Matcher matcher, Descriptor matcherDescriptor) {
				delegates.set(matcherFunction.apply(subjectDescriptor, matcherDescriptor));
			}

			@Override
			public void allUnset() {
				delegates.remove();
			}
		});
		if (descriptor != null) {
			setter.setDescriptor(descriptor);
		}
	}

	public ContextualMatcher(Descriptor descriptor, boolean prompt, Object expectedValue, Object... parameters) {
		this(descriptor, prompt, (subjectDescriptor, matcherDescriptor) -> getMatcher(
				subjectDescriptor, matcherDescriptor, expectedValue, parameters));
	}

	public ContextualMatcher(Descriptor descriptor, boolean prompt, double expectedValue, Object... parameters) {
		this(descriptor, prompt, (subjectDescriptor, matcherDescriptor) -> getMatcher(
				subjectDescriptor, matcherDescriptor, expectedValue, parameters));
	}

	public ContextualMatcher(Descriptor descriptor, boolean prompt, float expectedValue, Object... parameters) {
		this(descriptor, prompt, (subjectDescriptor, matcherDescriptor) -> getMatcher(
				subjectDescriptor, matcherDescriptor, expectedValue, parameters));
	}

	public ContextualMatcher(Descriptor descriptor, boolean prompt, long expectedValue, Object... parameters) {
		this(descriptor, prompt, (subjectDescriptor, matcherDescriptor) -> getMatcher(
				subjectDescriptor, matcherDescriptor, expectedValue, parameters));
	}

	public ContextualMatcher(Descriptor descriptor, boolean prompt, int expectedValue, Object... parameters) {
		this(descriptor, prompt, (subjectDescriptor, matcherDescriptor) -> getMatcher(
				subjectDescriptor, matcherDescriptor, expectedValue, parameters));
	}

	public ContextualMatcher(Descriptor descriptor, boolean prompt, short expectedValue, Object... parameters) {
		this(descriptor, prompt, (subjectDescriptor, matcherDescriptor) -> getMatcher(
				subjectDescriptor, matcherDescriptor, expectedValue, parameters));
	}

	public ContextualMatcher(Descriptor descriptor, boolean prompt, byte expectedValue, Object... parameters) {
		this(descriptor, prompt, (subjectDescriptor, matcherDescriptor) -> getMatcher(
				subjectDescriptor, matcherDescriptor, expectedValue, parameters));
	}

	public ContextualMatcher(Descriptor descriptor, boolean prompt, char expectedValue, Object... parameters) {
		this(descriptor, prompt, (subjectDescriptor, matcherDescriptor) -> getMatcher(
				subjectDescriptor, matcherDescriptor, expectedValue, parameters));
	}

	public ContextualMatcher(Descriptor descriptor, boolean prompt, boolean expectedValue, Object... parameters) {
		this(descriptor, prompt, (subjectDescriptor, matcherDescriptor) -> getMatcher(
				subjectDescriptor, matcherDescriptor, expectedValue, parameters));
	}

	@Override
	public boolean match(Object actualValue) {
		return requireDelegate().match(actualValue);
	}

	@Override
	public Description describe(Description description) {
		return requireDelegate().describe(description);
	}

	@Override
	public Description describeMismatch(Description mismatchDescription) {
		return requireDelegate().describeMismatch(mismatchDescription);
	}

	protected Matcher requireDelegate() {
		Matcher delegate = delegates.get();
		if (delegate == null) {
			throw new IllegalStateException("No destination has been set. This means that the assertion expression is incomplete, or that this method is being called outside of the context framework.");
		}
		return delegate;
	}

	public static ContextualMatcher[] arrayOf(Object[] expectedValues, Object... parameters) {
		return stream(expectedValues).map(expectedValue -> new ContextualMatcher(null, false,
				expectedValue, parameters)).toArray(ContextualMatcher[]::new);
	}

	public static ContextualMatcher[] arrayOf(double[] expectedValues, Object... parameters) {
		return stream(expectedValues).mapToObj(expectedValue -> new ContextualMatcher(null, false,
				expectedValue, parameters)).toArray(ContextualMatcher[]::new);
	}

	public static ContextualMatcher[] arrayOf(float[] expectedValues, Object... parameters) {
		ContextualMatcher[] matchers = new ContextualMatcher[expectedValues.length];
		int index = 0;
		for (float expectedValue : expectedValues) {
			matchers[index++] = new ContextualMatcher(null, false, expectedValue, parameters);
		}
		return matchers;
	}

	public static ContextualMatcher[] arrayOf(long[] expectedValues, Object... parameters) {
		return stream(expectedValues).mapToObj(expectedValue -> new ContextualMatcher(null, false,
				expectedValue, parameters)).toArray(ContextualMatcher[]::new);
	}

	public static ContextualMatcher[] arrayOf(int[] expectedValues, Object... parameters) {
		return stream(expectedValues).mapToObj(expectedValue -> new ContextualMatcher(null, false,
				expectedValue, parameters)).toArray(ContextualMatcher[]::new);
	}

	public static ContextualMatcher[] arrayOf(short[] expectedValues, Object... parameters) {
		ContextualMatcher[] matchers = new ContextualMatcher[expectedValues.length];
		int i = 0;
		for (short expectedValue : expectedValues) {
			matchers[i++] = new ContextualMatcher(null, false, expectedValue, parameters);
		}
		return matchers;
	}

	public static ContextualMatcher[] arrayOf(byte[] expectedValues, Object... parameters) {
		ContextualMatcher[] matchers = new ContextualMatcher[expectedValues.length];
		int i = 0;
		for (byte expectedValue : expectedValues) {
			matchers[i++] = new ContextualMatcher(null, false, expectedValue, parameters);
		}
		return matchers;
	}

	public static ContextualMatcher[] arrayOf(char[] expectedValues, Object... parameters) {
		ContextualMatcher[] matchers = new ContextualMatcher[expectedValues.length];
		int i = 0;
		for (char expectedValue : expectedValues) {
			matchers[i++] = new ContextualMatcher(null, false, expectedValue, parameters);
		}
		return matchers;
	}

	public static ContextualMatcher[] arrayOf(boolean[] expectedValues, Object... parameters) {
		ContextualMatcher[] matchers = new ContextualMatcher[expectedValues.length];
		int i = 0;
		for (boolean expectedValue : expectedValues) {
			matchers[i++] = new ContextualMatcher(null, false, expectedValue, parameters);
		}
		return matchers;
	}
}
