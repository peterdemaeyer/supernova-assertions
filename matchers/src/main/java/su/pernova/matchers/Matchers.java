package su.pernova.matchers;

import su.pernova.matchers.core.CoreMatchers;
import su.pernova.matchers.core.EqualsMatcher;
import su.pernova.matchers.core.SameAsMatcher;
import su.pernova.matchers.number.NumberMatchers;

public final class Matchers {

	private Matchers() {
		// Prevent instantiation.
	}

	/**
	 * Decorates another matcher, preserving its behaviour, but allowing assertions to be more expressive.
	 * When given {@code null} as delegate, this method will return a null matcher.
	 *
	 * <h3>Examples</h3>
	 *
	 * <pre>{@code assertThat(instance, is(equalTo(anotherInstance)))}</pre>
	 * instead of:
	 * <pre>{@code assertThat(instance, equalTo(anotherInstance))}</pre>
	 *
	 * @since 1.0.0
	 */
	public static <T> Matcher<T> is(Matcher<T> delegate) {
		return CoreMatchers.is(delegate);
	}

	/**
	 * Creates a matcher that matches actual values for identity against a given expected value, including {@code null}.
	 * Such a matcher implements the "is" relation, corresponding to the "==" operator in Java.
	 * Beware that this matcher matches for identity <i>only</i>, it does <i>not</i> match for equality.
	 *
	 * <h3>Examples</h13
	 *
	 * <pre>{@code
	 * assertThat(value, is(null)); // Assert that a value is null.
	 * assertThat(intValue, is(2)); // Assert that an int value is 2.
	 * assertThat(value, is(expected)); // Assert that a value is ("==") an expected value.
	 * }</pre>
	 *
	 * @param expected an expected value to match against for identity, which may be {@code null}.
	 * @return a matcher that matches against a given expected value, not {@code null}.
	 * @since 1.0.0
	 */
	public static <T> Matcher<T> is(T expected) {
		return new SameAsMatcher<>("is ", expected);
	}

	/**
	 * Creates a matcher that matches for identity against a given value.
	 * Such a matcher implement the "same as" relating, corresponding to the "==" operator in Java.
	 *
	 * <h1>Examples</h1>
	 *
	 * <pre>{@code
	 * assertThat(value, is(sameAs(expected))); // Assert that a value is ("==") an expected value.
	 * }</pre>
	 *
	 * @param expected an expected value to match against for identity, which may be {@code null}.
	 * @return a matcher that matches against a given expected value, not {@code null}.
	 * @since 1.0.0
	 */
	public static <T> Matcher<T> sameAs(T expected) {
		return new SameAsMatcher<>("same as ", expected);
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
		return CoreMatchers.not(delegate);
	}

	public static <T> Matcher<T> not(T expected) {
		return CoreMatchers.not(expected);
	}

	/**
	 * @see NumberMatchers#notANumber()
	 * @since 1.0.0
	 */
	public static Matcher notANumber() {
		return NumberMatchers.notANumber();
	}
}
