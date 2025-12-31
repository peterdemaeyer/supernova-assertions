package su.pernova.assertions;

import static java.util.Objects.requireNonNull;

import static internal.su.pernova.assertions.matchers.MultiMatcher.create;

import java.util.Objects;
import java.util.regex.Pattern;

import internal.su.pernova.assertions.matchers.AllOf;
import internal.su.pernova.assertions.matchers.AnyOf;
import internal.su.pernova.assertions.matchers.CloseTo;
import internal.su.pernova.assertions.matchers.EqualTo;
import internal.su.pernova.assertions.matchers.ForwardingMatcher;
import internal.su.pernova.assertions.matchers.InstanceOf;
import internal.su.pernova.assertions.matchers.Is;
import internal.su.pernova.assertions.matchers.IsBoolean;
import internal.su.pernova.assertions.matchers.IsByte;
import internal.su.pernova.assertions.matchers.IsChar;
import internal.su.pernova.assertions.matchers.IsDouble;
import internal.su.pernova.assertions.matchers.IsFloat;
import internal.su.pernova.assertions.matchers.IsInt;
import internal.su.pernova.assertions.matchers.IsLong;
import internal.su.pernova.assertions.matchers.IsObject;
import internal.su.pernova.assertions.matchers.IsShort;
import internal.su.pernova.assertions.matchers.Nan;
import internal.su.pernova.assertions.matchers.Not;
import internal.su.pernova.assertions.matchers.PromptedNamedMatcher;
import internal.su.pernova.assertions.matchers.Regex;

/**
 * This utility class provides all built-in matchers.
 * It is recommended to use static imports for readability.
 *
 * @since 1.0.0
 */
public final class Matchers {

	private static final CharSequence SAME_AS = "same as";

	private static final CharSequence IDENTICAL_TO = "identical to";

	/**
	 * @since 2.0.0
	 */
	public static final Descriptor REGEX = new Descriptor(Matchers.class, "regex");

	private Matchers() {
	}

	/**
	 * Returns a matcher that matches like a given matcher and prefixes its description with "matches".
	 * This is useful for expressing regular expressions:
	 * <pre class="code"><code class="java">
	 * assertThat("apple", matches(regex("[a-b]*")));
	 * </code></pre>
	 *
	 * @param delegate a given matcher to match, which must not be {@code null}.
	 * @return an identity matcher decorating a given matcher, not {@code null}.
	 * @since 2.0.0
	 */
	public static Matcher matches(Matcher delegate) {
		return forward("matches", delegate);
	}

	private static Matcher forward(CharSequence name, Matcher delegatee) {
		if (delegatee != null) {
			return Context.forwardMatcherFactory(name, delegatee);
		}
		return putIsFactory(new IsObject(name, null));
	}

	/**
	 * Returns a matcher that matches like a given matcher and prefixes its description with "match".
	 * This is useful for expressing negated regular expressions:
	 * <pre class="code"><code class="java">
	 * assertThat("apple", does(not(match(regex("[0-9]*")))));
	 * </code></pre>
	 *
	 * @param delegate a given matcher to match, which must not be {@code null}.
	 * @return a matcher
	 * @since 2.0.0
	 */
	public static Matcher match(Matcher delegate) {
		return forward("match", delegate);
	}

	/**
	 * Returns an identity matcher decorating a given matcher}.
	 * When the given matcher is {@code null}, then this method behaves as {@link #is(Object)}.
	 *
	 * @param matcher a given matcher to match, which may be {@code null}.
	 * @return an identity matcher decorating a given matcher, not {@code null}.
	 * @since 2.0.0
	 */
	public static Matcher is(Matcher matcher) {
		if (matcher != null) {
			return Context.putMatcherFactory(Is::new, matcher, Is.getMatcherFactory(null));
		}
		return is((Object) null);
	}

	/**
	 * Returns a matcher matching a given object using the "==" operator.
	 * This works for any object, including {@code null}.
	 *
	 * @param expectedValue a given object, which may be {@code null}.
	 * @return a matcher matching by identity, not {@code null}.
	 * @since 2.0.0
	 */
	public static Matcher is(Object expectedValue) {
		return putIsFactory(new IsObject(expectedValue));
	}

	/**
	 * Returns a matcher matching a given expected primitive double using the "==" operator.
	 * Beware that the "==" operator behaves differently for primitive doubles than for {@link Double} objects.
	 * <table>
	 *     <th>
	 *         <td>Primitive</td><td>Result</td>
	 *         <td>Object</td><td>Result</td>
	 *     </th>
	 *     <tr>
	 *         <td>{@code Double.NaN == Double.NaN}</td><td>{@code false}</td>
	 *         <td>{@code Double.valueOf(Double.NaN) == Double.valueOf(Double.NaN)}</td><td>{@code true}</td>
	 *     </tr>
	 *     <tr>
	 *         <td>{@code 1d == 1f}</td><td>{@code true}</td>
	 *         <td>{@code Double.valueOf(1d) == Float.valueOf(1f)}</td><td>{@code false}</td>
	 *     </tr>
	 * </table>
	 *
	 * @param expected an expected double.
	 * @return a matcher, not {@code null}.
	 * @since 2.0.0
	 */
	public static Matcher is(double expected) {
		return putIsFactory(new IsDouble(expected));
	}

	/**
	 * Returns a matcher matching a given expected primitive float using the "==" operator.
	 * Beware that the "==" operator behaves differently for primitive doubles than for {@link Double} objects.
	 * <table>
	 *     <th>
	 *         <td>Primitive</td><td>Result</td>
	 *         <td>Object</td><td>Result</td>
	 *     </th>
	 *     <tr>
	 *         <td>{@code Float.NaN == Float.NaN}</td><td>{@code false}</td>
	 *         <td>{@code Float.valueOf(Float.NaN) == Float.valueOf(Float.NaN)}</td><td>{@code true}</td>
	 *     </tr>
	 *     <tr>
	 *         <td>{@code 1f == 1d}</td><td>{@code true}</td>
	 *         <td>{@code Float.valueOf(1f) == Double.valueOf(1d)}</td><td>{@code false}</td>
	 *     </tr>
	 * </table>
	 *
	 * @param expectedValue an expected float.
	 * @return a matcher using the "==" operator, not {@code null}.
	 * @since 2.0.0
	 */
	public static Matcher is(float expectedValue) {
		return putIsFactory(new IsFloat(expectedValue));
	}

	/**
	 * Returns a matcher matching a given expected primitive long using the "==" operator.
	 * Beware that the "==" operator behaves differently for primitive longs than for {@link Long} objects.
	 * For example {@code 1L == 1} is {@code true} but {@code Long.valueOf(1L) == Integer.valueOf(1)} is {@code false}.
	 *
	 * @param expectedValue an expected long.
	 * @return a matcher using the "==" operator, not {@code null}.
	 * @since 2.0.0
	 */
	public static Matcher is(long expectedValue) {
		return putIsFactory(new IsLong(expectedValue));
	}

	/**
	 * Returns a matcher matching a given expected primitive int using the "==" operator.
	 * Beware that the "==" operator behaves differently for primitive ints than for {@link Integer} objects.
	 * For example {@code 1L == 1} is {@code true} but {@code Long.valueOf(1L) == Integer.valueOf(1)} is {@code false}.
	 *
	 * @param expectedValue an expected int.
	 * @return a matcher using the "==" operator, not {@code null}.
	 * @since 2.0.0
	 */
	public static Matcher is(int expectedValue) {
		return putIsFactory(new IsInt(expectedValue));
	}

	/**
	 * Returns a matcher matching a given expected primitive int using the "==" operator.
	 * Beware that the "==" operator behaves differently for primitive shorts than for {@link Short} objects.
	 * For example {@code ((short) 1) == 1} is {@code true} but {@code Short.valueOf((short) 1) == Integer.valueOf(1)}
	 * is {@code false}.
	 *
	 * @param expectedValue an expected short.
	 * @return a matcher using the "==" operator, not {@code null}.
	 * @since 2.0.0
	 */
	public static Matcher is(short expectedValue) {
		return putIsFactory(new IsShort(expectedValue));
	}

	/**
	 * Returns a matcher matching a given expected primitive byte using the "==" operator.
	 * Beware that the "==" operator behaves differently for primitive bytes than for {@link Byte} objects.
	 * For example {@code ((byte) 1) == 1} is {@code true} but {@code Byte.valueOf((byte) 1) == Integer.valueOf(1)}
	 * is {@code false}.
	 *
	 * @param expectedValue an expected byte.
	 * @return a matcher using the "==" operator, not {@code null}.
	 * @since 2.0.0
	 */
	public static Matcher is(byte expectedValue) {
		return putIsFactory(new IsByte(expectedValue));
	}

	/**
	 * Returns a matcher matching a given expected primitive char using the "==" operator.
	 * Beware that the "==" operator behaves differently for primitive chars than for {@link Character} objects.
	 * For example {@code ('\u0001') == 1} is {@code true} but {@code Character.valueOf('\u0001') == Integer.valueOf(1)}
	 * is {@code false}.
	 *
	 * @param expectedValue an expected char.
	 * @return a matcher using the "==" operator, not {@code null}.
	 * @since 2.0.0
	 */
	public static Matcher is(char expectedValue) {
		return putIsFactory(new IsChar(expectedValue));
	}

	/**
	 * Returns a matcher matching a given expected primitive boolean using the "==" operator.
	 * The "==" operator behaves the same for primitive booleans than for {@link Boolean} objects.
	 * This method is provided for completeness and consistency with the other {@code is} methods that take a primitive
	 * type as parameter.
	 *
	 * @param expectedValue an expected value.
	 * @return a matcher using the "==" operator, not {@code null}.
	 * @since 2.0.0
	 */
	public static Matcher is(boolean expectedValue) {
		return putIsFactory(new IsBoolean(expectedValue));
	}

	private static Matcher putIsFactory(PromptedNamedMatcher prototype) {
		return Context.putMatcherFactory(prototype, Is.getMatcherFactory(null));
	}

	/**
	 * Returns a matcher that matches objects using to the {@link Objects#equals(Object, Object)} operator.
	 * The object to match may be {@code null}.
	 *
	 * @param expectedValue an object to match, which may be {@code null}.
	 * @return a matcher, not {@code null}, that matches equal objects.
	 * @since 1.0.0
	 */
	public static Matcher equalTo(Object expectedValue) {
		return Context.putMatcherFactory(EqualTo.MATCHER_FACTORY.create(expectedValue), EqualTo.MATCHER_FACTORY);
	}

	/**
	 * Returns a context-providing matcher that matches by equality.
	 * It provides context for a context-sensitive matcher such as {@link #allOf(Object...)}.
	 *
	 * @param matcher a context-sensitive matcher, not {@code null}.
	 * @return a context-providing matcher, not {@code null}.
	 * @since 2.0.0
	 */
	public static Matcher equalTo(Matcher matcher) {
		return Context.putMatcherFactory(m -> new ForwardingMatcher("equal to", m), matcher, EqualTo.MATCHER_FACTORY);
	}

	/**
	 * Returns a matcher that matches if an object is of a given instance, according to the {@code instanceof} operator.
	 * The object to match may be {@code null}
	 *
	 * @param expectedClass a class to match (including subtypes).
	 * @return a matcher, not {@code null}, that matches is an object is of a given instance.
	 * @since 1.0.0
	 */
	public static Matcher instanceOf(Class<?> expectedClass) {
		return Context.putMatcherFactory(new InstanceOf(expectedClass), InstanceOf.MATCHER_FACTORY);
	}

	/**
	 * Returns a context-providing matcher that provides the "instance of" context to a context-sensitive matcher such
	 * as {@link #anyOf(Object...)}.
	 *
	 * @param matcher a context-sensitive matcher, not {@code null}.
	 * @return a context-providing matcher, not {@code null}.
	 * context-sensitive matcher.
	 * @since 2.0.0
	 */
	public static Matcher instanceOf(Matcher matcher) {
		return Context.putMatcherFactory(m -> new ForwardingMatcher("instance of", m), matcher, InstanceOf.MATCHER_FACTORY);
	}

	/**
	 * Returns a matcher that matches identical objects, including {@code null}.
	 * Such a matcher uses the {@code ==} operator.
	 *
	 * @param expectedValue the object to match.
	 * @return a matcher that matches identical objects, not {@code null}.
	 * @see #identicalTo(Object)
	 * @since 1.0.0
	 */
	public static Matcher sameAs(Object expectedValue) {
		return putIsFactory(new IsObject("same as", expectedValue));
	}

	/**
	 * Returns a context-providing matcher that matches by identity.
	 * It provides context for a context-sensitive matcher such as {@link #allOf(Object...)}.
	 * This method exists for compatibility with other assertion frameworks.
	 * It is recommended to use {@link #identicalTo(Matcher)} instead.
	 *
	 * @param matcher a context-sensitive matcher, not {@code null}.
	 * @return a context-providing matcher, not {@code null}.
	 * @since 2.0.0
	 */
	public static Matcher sameAs(Matcher matcher) {
		return Context.putMatcherFactory(m -> new ForwardingMatcher(SAME_AS, m), matcher, Is.getMatcherFactory(SAME_AS));
	}

	/**
	 * @param expectedValue the object to match.
	 * @return a matcher that matches identical objects, not {@code null}.
	 * @since 2.0.0
	 */
	public static Matcher identicalTo(Object expectedValue) {
		return putIsFactory(new IsObject("identical to", expectedValue));
	}

	/**
	 * Returns a context-providing matcher that matches by identity.
	 *
	 * @param delegate a context-sensitive matcher, not {@code null}.
	 * @return a context-providing matcher, not {@code null}.
	 * @since 2.0.0
	 */
	public static Matcher identicalTo(Matcher delegate) {
		return forward("identical to", delegate);
	}

	/**
	 * Returns a matcher matching a given regex.
	 *
	 * @param regex a regex to match, must not be {@code null}.
	 * @return a matcher matching against a given regex, not {@code null}.
	 * @since 1.1.0
	 */
	public static Matcher regex(Pattern regex) {
		return Context.putMatcherFactory(new Regex(regex), new MatcherFactory() {

			@Override
			public Matcher create(Object expectedValue) {
				if (expectedValue instanceof Pattern pattern) {

				}
				return new Regex((Pattern) expectedValue);
			}
		});
	}

	/**
	 * Returns a matcher matching a given regex.
	 *
	 * @param regex a regex to match, must not be {@code null}.
	 * @return a matcher matching against a given regex, not {@code null}.
	 * @since 1.1.0
	 */
	public static Matcher regex(String regex) {
		return new Regex(regex).contextualize(REGEX);
	}

	/**
	 * Returns a matcher matching floating point NaN (not a number) values.
	 * This is equivalent to {@link Double#isNaN()}.
	 * Use {@code assertThat(actual).is(nan())} rather than {@code assertThat(actual).is(NaN)}, because the latter will
	 * <i>always</i> return {@code false} even when {@code actual = NaN}.
	 * This is because of the IEEE 756 semantics of {@code NaN == NaN} which is always {@code false}.
	 *
	 * @return a matcher matching floating point NaN (not a number) values, not {@code null}.
	 * @since 2.0.0
	 */
	public static Matcher nan() {
		return Nan.getInstance();
	}

	/**
	 * Returns a matcher matching an expected number with a given tolerance.
	 *
	 * @param expected a number to match, not {@code null}.
	 * @param tolerance a tolerance, not {@code null}.
	 * @return a matcher matching a number with a given tolerance.
	 * @since 2.0.0
	 */
	public static Matcher closeTo(Number expected, Number tolerance) {
		return new CloseTo(expected, tolerance);
	}

	/**
	 * Returns a matcher that matches like a given matcher and prefixes its description with "do".
	 * It is the plural of {@link #does}.
	 * The method forwardingFunction has a non-US-ASCII character to deconflict with the reserved keyword "do".
	 * If you find the non-US-ASCII character disturbing, use {@link #do_} instead.
	 * <p/>
	 * This method is equivalent to {@link #do_}, but it uses a Unicode
	 * <a href="https://gist.github.com/StevenACoffman/a5f6f682d94e38ed804182dc2693ed4b">'о'</a> instead of an
	 * underscore to deconflict with the reserved Java keyword "do".
	 * It improves readability (no unwanted '_') at the expense of codability (not code-completable when typing "do" in
	 * US-ASCII).
	 * It's a matter of taste whether you prefer this method or the equivalent.
	 *
	 * @param delegate a destination, not {@code null}.
	 * @return a matcher that prefixes the description with "do", not {@code null}.
	 * @see #do_(Matcher)
	 */
	public static Matcher dо(Matcher delegate) {
		return do_(delegate);
	}

	/**
	 * Returns a matcher that matches like a given matcher and prefixes its description with "do".
	 * It is the plural of {@link #does}.
	 * The method has a trailing underscore to deconflict with the reserved keyword "do".
	 * If you find the trailing underscore visually disturbing, use {@link #dо} instead.
	 *
	 * @param delegatee a destination, not {@code null}.
	 * @return a matcher that prefixes the description with "do", not {@code null}.
	 * @see #dо(Matcher)
	 */
	public static Matcher do_(Matcher delegatee) {
		return Context.forwardMatcherFactory("do", delegatee);
	}

	/**
	 * Returns a matcher that matches like a given matcher and prefixes its description with "does".
	 * This is useful for matching negated regular expressions:
	 * <pre class="code"><code class="java">
	 * assertThat("apple", does(not(match(regex("[0-9]*")))));
	 * </code></pre>
	 *
	 * @param delegate a given destination, which must not be {@code null}.
	 * @return a matcher that prefixes the description with "does".
	 * @since 2.0.0
	 */
	public static Matcher does(Matcher delegate) {
		return Context.forwardMatcherFactory("does", delegate);
	}

	/**
	 * Returns a matcher that matches when a given matcher does not and vice versa.
	 * This is useful for matching negations:
	 * <pre class="code"><code class="java">assertThat(1, is(not(equalTo(2))));</code></pre>
	 *
	 * @param destination a given destination, which must not be {@code null}.
	 * @return a matcher that matches when a given matcher does not and vice versa.
	 * @since 2.0.0
	 */
	public static Matcher not(Matcher destination) {
		return Context.forwardMatcherFactory(Not::new, "not", destination);
	}

	/**
	 * Returns a matcher that matches when a given object does not.
	 *
	 * @param expected an object to not match, which may be {@code null}.
	 * @return a matcher that does not match a given object.
	 */
	public static Matcher not(Object expected) {
		return not(new IsObject("", true, expected));
	}

	/**
	 * Returns a matcher that matches when a given long value does not.
	 *
	 * @param expectedValue a long value to not match.
	 * @return a matcher that matches when a given long value does not.
	 */
	public static Matcher not(long expectedValue) {
		return not(new IsLong("", true, expectedValue));
	}

	/**
	 * Returns a matcher that matches when a given int value does not.
	 *
	 * @param expectedValue an int value to not match.
	 * @return a matcher that matches when a given int value does not.
	 */
	public static Matcher not(int expectedValue) {
		return not(new IsInt("", true, expectedValue));
	}

	/**
	 * Returns a matcher that matches when a given short value does not.
	 *
	 * @param expectedValue a short value to not match.
	 * @return a matcher that matches when a given short value does not.
	 */
	public static Matcher not(short expectedValue) {
		return not(new IsShort("", true, expectedValue));
	}

	/**
	 * Returns a matcher that matches when a given byte value does not.
	 *
	 * @param expectedValue a byte value to not match.
	 * @return a matcher that matches when a given byte value does not.
	 */
	public static Matcher not(byte expectedValue) {
		return not(new IsByte("", true, expectedValue));
	}

	/**
	 * Returns a matcher that matches when a given char value does not.
	 *
	 * @param expectedValue a char value to not match.
	 * @return a matcher that matches when a given char value does not.
	 */
	public static Matcher not(char expectedValue) {
		return not(new IsChar("", true, expectedValue));
	}

	/**
	 * Returns a matcher that matches when a given boolean value does not.
	 *
	 * @param expectedValue a boolean value to not match.
	 * @return a matcher that matches when a given boolean value does not.
	 */
	public static Matcher not(boolean expectedValue) {
		return not(new IsBoolean("", true, expectedValue));
	}

	/**
	 * Returns a matcher that matches when a given double value does not.
	 *
	 * @param expectedValue a double value to not match.
	 * @return a matcher that matches when a given double value does not.
	 */
	public static Matcher not(double expectedValue) {
		return not(new IsDouble("", true, expectedValue));
	}

	/**
	 * Returns a matcher that matches when a given float value does not.
	 *
	 * @param expectedValue a float value to not match.
	 * @return a matcher that matches when a given float value does not.
	 */
	public static Matcher not(float expectedValue) {
		return not(new IsFloat("", true, expectedValue));
	}

	/**
	 * Returns a context-sensitive matcher that combines multiple values with a logical OR operator.
	 * This matcher must be used in combination with a context-providing matcher, which defines how all the individual
	 * values match.
	 *
	 * @param expectedValues multiple values, which must not be {@code null} as an array.
	 * Individual elements in the array may be {@code null}.
	 * @return a context-sensitive matcher that combines multiple values with a logical OR operator, not {@code null}.
	 * @since 2.0.0
	 */
	public static Matcher anyOf(Object... expectedValues) {
		requireNonNull(expectedValues, "array of expected values is null");
		return Context.newIncompleteMatcher(matcherFactory -> create(AnyOf::new, matcherFactory, expectedValues));
	}

	/**
	 * Returns a context-sensitive matcher that combines multiple values with a logical OR operator.
	 * This matcher must be used in combination with a context-providing matcher, which defines how all the individual
	 * values match.
	 *
	 * @param expectedValues multiple values, which must not be {@code null}.
	 * @return a context-sensitive matcher that combines multiple values with a logical OR operator, not {@code null}.
	 * @since 2.0.0
	 */
	public static Matcher anyOf(double... expectedValues) {
		requireNonNull(expectedValues, "array of expected values is null");
		return Context.newIncompleteMatcher(matcherFactory -> create(AnyOf::new, matcherFactory, expectedValues));
	}

	/**
	 * Returns a context-sensitive matcher that combines multiple values with a logical OR operator.
	 * This matcher must be used in combination with a context-providing matcher, which defines how all the individual
	 * values match.
	 *
	 * @param expectedValues multiple values, which must not be {@code null}.
	 * @return a context-sensitive matcher that combines multiple values with a logical OR operator, not {@code null}.
	 * @since 2.0.0
	 */
	public static Matcher anyOf(float... expectedValues) {
		requireNonNull(expectedValues, "array of expected values is null");
		return Context.newIncompleteMatcher(matcherFactory -> create(AnyOf::new, matcherFactory, expectedValues));
	}

	/**
	 * Returns a context-sensitive matcher that combines multiple values with a logical OR operator.
	 * This matcher must be used in combination with a context-providing matcher, which defines how all the individual
	 * values match.
	 *
	 * @param expectedValues multiple values, which must not be {@code null}.
	 * @return a context-sensitive matcher that combines multiple values with a logical OR operator, not {@code null}.
	 * @since 2.0.0
	 */
	public static Matcher anyOf(long... expectedValues) {
		requireNonNull(expectedValues, "array of expected values is null");
		return Context.newIncompleteMatcher(matcherFactory -> create(AnyOf::new, matcherFactory, expectedValues));
	}

	/**
	 * Returns a context-sensitive matcher that combines multiple values with a logical OR operator.
	 * This matcher must be used in combination with a context-providing matcher, which defines how all the individual
	 * values match.
	 *
	 * @param expectedValues multiple values, which must not be {@code null}.
	 * @return a context-sensitive matcher that combines multiple values with a logical OR operator, not {@code null}.
	 * @since 2.0.0
	 */
	public static Matcher anyOf(int... expectedValues) {
		requireNonNull(expectedValues, "array of expected values is null");
		return Context.newIncompleteMatcher(matcherFactory -> create(AnyOf::new, matcherFactory, expectedValues));
	}

	/**
	 * Returns a context-sensitive matcher that combines multiple values with a logical OR operator.
	 * This matcher must be used in combination with a context-providing matcher, which defines how all the individual
	 * values match.
	 *
	 * @param expectedValues multiple values, which must not be {@code null}.
	 * @return a context-sensitive matcher that combines multiple values with a logical OR operator, not {@code null}.
	 * @since 2.0.0
	 */
	public static Matcher anyOf(short... expectedValues) {
		requireNonNull(expectedValues, "array of expected values is null");
		return Context.newIncompleteMatcher(matcherFactory -> create(AnyOf::new, matcherFactory, expectedValues));
	}

	/**
	 * Returns a context-sensitive matcher that combines multiple values with a logical OR operator.
	 * This matcher must be used in combination with a context-providing matcher, which defines how all the individual
	 * values match.
	 *
	 * @param expectedValues multiple values, which must not be {@code null}.
	 * @return a context-sensitive matcher that combines multiple values with a logical OR operator, not {@code null}.
	 * @since 2.0.0
	 */
	public static Matcher anyOf(byte... expectedValues) {
		requireNonNull(expectedValues, "array of expected values is null");
		return Context.newIncompleteMatcher(matcherFactory -> create(AnyOf::new, matcherFactory, expectedValues));
	}

	/**
	 * Returns a context-sensitive matcher that combines multiple values with a logical OR operator.
	 * This matcher must be used in combination with a context-providing matcher, which defines how all the individual
	 * values match.
	 *
	 * @param expectedValues multiple values, which must not be {@code null}.
	 * @return a context-sensitive matcher that combines multiple values with a logical OR operator, not {@code null}.
	 * @since 2.0.0
	 */
	public static Matcher anyOf(char... expectedValues) {
		requireNonNull(expectedValues, "array of expected values is null");
		return Context.newIncompleteMatcher(matcherFactory -> create(AnyOf::new, matcherFactory, expectedValues));
	}

	/**
	 * Returns a context-sensitive matcher that combines multiple values with a logical OR operator.
	 * This matcher must be used in combination with a context-providing matcher, which defines how all the individual
	 * values match.
	 *
	 * @param expectedValues multiple values, which must not be {@code null}.
	 * @return a context-sensitive matcher that combines multiple values with a logical OR operator, not {@code null}.
	 * @since 2.0.0
	 */
	public static Matcher anyOf(boolean... expectedValues) {
		requireNonNull(expectedValues, "array of expected values is null");
		return Context.newIncompleteMatcher(matcherFactory -> create(AnyOf::new, matcherFactory, expectedValues));
	}

	/**
	 * Returns a context-sensitive matcher that combines multiple values with a logical AND operator.
	 * This matcher must be used in combination with a context-providing matcher, which defines how all the individual
	 * values match.
	 *
	 * @param expectedValues multiple values, which must not be {@code null} as an array.
	 * Individual elements in the array may be {@code null}.
	 * @return a context-sensitive matcher that combines multiple values with a logical OR operator, not {@code null}.
	 * @since 2.0.0
	 */
	public static Matcher allOf(Object... expectedValues) {
		requireNonNull(expectedValues, "array of expected values is null");
		return Context.newIncompleteMatcher(matcherFactory -> create(AllOf::new, matcherFactory, expectedValues));
	}

	/**
	 * Returns a context-sensitive matcher that combines multiple values with a logical AND operator.
	 * This matcher must be used in combination with a context-providing matcher, which defines how all the individual
	 * values match.
	 *
	 * @param expectedValues multiple values, which must not be {@code null}.
	 * @return a context-sensitive matcher that combines multiple values with a logical AND operator, not {@code null}.
	 * @since 2.0.0
	 */
	public static Matcher allOf(double... expectedValues) {
		requireNonNull(expectedValues, "array of expected values is null");
		return Context.newIncompleteMatcher(matcherFactory -> create(AllOf::new, matcherFactory, expectedValues));
	}

	/**
	 * Returns a context-sensitive matcher that combines multiple values with a logical AND operator.
	 * This matcher must be used in combination with a context-providing matcher, which defines how all the individual
	 * values match.
	 *
	 * @param expectedValues multiple values, which must not be {@code null}.
	 * @return a context-sensitive matcher that combines multiple values with a logical AND operator, not {@code null}.
	 * @since 2.0.0
	 */
	public static Matcher allOf(float... expectedValues) {
		requireNonNull(expectedValues, "array of expected values is null");
		return Context.newIncompleteMatcher(matcherFactory -> create(AllOf::new, matcherFactory, expectedValues));
	}

	/**
	 * Returns a context-sensitive matcher that combines multiple values with a logical AND operator.
	 * This matcher must be used in combination with a context-providing matcher, which defines how all the individual
	 * values match.
	 *
	 * @param expectedValues multiple values, which must not be {@code null}.
	 * @return a context-sensitive matcher that combines multiple values with a logical AND operator, not {@code null}.
	 * @since 2.0.0
	 */
	public static Matcher allOf(long... expectedValues) {
		requireNonNull(expectedValues, "array of expected values is null");
		return Context.newIncompleteMatcher(matcherFactory -> create(AllOf::new, matcherFactory, expectedValues));
	}

	/**
	 * Returns a context-sensitive matcher that combines multiple values with a logical AND operator.
	 * This matcher must be used in combination with a context-providing matcher, which defines how all the individual
	 * values match.
	 *
	 * @param expectedValues multiple values, which must not be {@code null}.
	 * @return a context-sensitive matcher that combines multiple values with a logical AND operator, not {@code null}.
	 * @since 2.0.0
	 */
	public static Matcher allOf(int... expectedValues) {
		requireNonNull(expectedValues, "array of expected values is null");
		return Context.newIncompleteMatcher(matcherFactory -> create(AllOf::new, matcherFactory, expectedValues));
	}

	/**
	 * Returns a context-sensitive matcher that combines multiple values with a logical AND operator.
	 * This matcher must be used in combination with a context-providing matcher, which defines how all the individual
	 * values match.
	 *
	 * @param expectedValues multiple values, which must not be {@code null}.
	 * @return a context-sensitive matcher that combines multiple values with a logical AND operator, not {@code null}.
	 * @since 2.0.0
	 */
	public static Matcher allOf(short... expectedValues) {
		requireNonNull(expectedValues, "array of expected values is null");
		return Context.newIncompleteMatcher(matcherFactory -> create(AllOf::new, matcherFactory, expectedValues));
	}

	/**
	 * Returns a context-sensitive matcher that combines multiple values with a logical AND operator.
	 * This matcher must be used in combination with a context-providing matcher, which defines how all the individual
	 * values match.
	 *
	 * @param expectedValues multiple values, which must not be {@code null}.
	 * @return a context-sensitive matcher that combines multiple values with a logical AND operator, not {@code null}.
	 * @since 2.0.0
	 */
	public static Matcher allOf(byte... expectedValues) {
		requireNonNull(expectedValues, "array of expected values is null");
		return Context.newIncompleteMatcher(matcherFactory -> create(AllOf::new, matcherFactory, expectedValues));
	}

	/**
	 * Returns a context-sensitive matcher that combines multiple values with a logical AND operator.
	 * This matcher must be used in combination with a context-providing matcher, which defines how all the individual
	 * values match.
	 *
	 * @param expectedValues multiple values, which must not be {@code null}.
	 * @return a context-sensitive matcher that combines multiple values with a logical AND operator, not {@code null}.
	 * @since 2.0.0
	 */
	public static Matcher allOf(char... expectedValues) {
		requireNonNull(expectedValues, "array of expected values is null");
		return Context.newIncompleteMatcher(matcherFactory -> create(AllOf::new, matcherFactory, expectedValues));
	}

	/**
	 * Returns a context-sensitive matcher that combines multiple values with a logical AND operator.
	 * This matcher must be used in combination with a context-providing matcher, which defines how all the individual
	 * values match.
	 *
	 * @param expectedValues multiple values, which must not be {@code null}.
	 * @return a context-sensitive matcher that combines multiple values with a logical AND operator, not {@code null}.
	 * @since 2.0.0
	 */
	public static Matcher allOf(boolean... expectedValues) {
		requireNonNull(expectedValues, "array of expected values is null");
		return Context.newIncompleteMatcher(matcherFactory -> create(AllOf::new, matcherFactory, expectedValues));
	}
}
