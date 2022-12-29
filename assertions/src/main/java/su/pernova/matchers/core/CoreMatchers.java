package su.pernova.matchers.core;

import su.pernova.matchers.Matcher;

public final class CoreMatchers {

	private CoreMatchers() {
		// Prevent instantiation.
	}

	/**
	 * Decorates another Matcher, retaining its behaviour, but allowing tests
	 * to be slightly more expressive.
	 * For example:
	 * <pre>assertThat(cheese, is(equalTo(smelly)))</pre>
	 * instead of:
	 * <pre>assertThat(cheese, equalTo(smelly))</pre>
	 */
	public static <T> Matcher<T> is(Matcher<T> matcher) {
		return (matcher != null) ? new IdentityMatcher<>("is ", matcher) : is((T) null);
	}

	/**
	 * A shortcut to the frequently used <code>is(equalTo(x))</code>.
	 * For example:
	 * <pre>assertThat(cheese, is(smelly))</pre>
	 * instead of:
	 * <pre>assertThat(cheese, is(equalTo(smelly)))</pre>
	 */
	public static <T> Matcher<T> is(T expected) {
		return new SameAsMatcher<>("is ", expected);
	}

	public static <T> Matcher<T> sameAs(T expected) {
		return new SameAsMatcher<>("same as ", expected);
	}

	public static <T> Matcher<T> instanceOf(Class<T> expectedClass) {
		return new InstanceOfMatcher<>("instance of ", expectedClass);
	}

	/**
	 * Creates a matcher that matches when the examined object is logically equal to the specified
	 * <code>operand</code>, as determined by calling the {@link Object#equals} method on
	 * the <b>examined</b> object.
	 *
	 * <p>If the specified operand is <code>null</code> then the created matcher will only match if
	 * the examined object's <code>equals</code> method returns <code>true</code> when passed a
	 * <code>null</code> (which would be a violation of the <code>equals</code> contract), unless the
	 * examined object itself is <code>null</code>, in which case the matcher will return a positive
	 * match.</p>
	 *
	 * <p>The created matcher provides a special behaviour when examining <code>Array</code>s, whereby
	 * it will match if both the operand and the examined object are arrays of the same length and
	 * contain items that are equal to each other (according to the above rules) <b>in the same
	 * indexes</b>.</p>
	 * For example:
	 * <pre>
	 * assertThat("foo", equalTo("foo"));
	 * assertThat(new String[] {"foo", "bar"}, equalTo(new String[] {"foo", "bar"}));
	 * </pre>
	 */
	public static <T> Matcher<T> equalTo(T operand) {
		return EqualsMatcher.equalTo(operand);
	}

	/**
	 * Creates an {@link EqualsMatcher} matcher that does not enforce the values being
	 * compared to be of the same static type.
	 */
	public static Matcher<Object> equalToObject(Object operand) {
		return EqualsMatcher.equalToObject(operand);
	}

	/**
	 * @see CoreMatchers#not(Matcher)
	 * @since 1.0.0
	 */
	public static <T> Matcher<T> not(Matcher<T> delegate) {
		return new NotMatcher<>(delegate);
	}

	public static <T> Matcher<T> not(T expected) {
		return new NotMatcher<>(new SameAsMatcher<>("", expected));
	}
}
