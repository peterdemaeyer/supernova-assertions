package su.pernova.assertions;

import static internal.su.pernova.assertions.matchers.ContextSensitive.provideContext;
import static internal.su.pernova.assertions.matchers.ContextSensitive.requireBackwardContext;

import java.util.Objects;
import java.util.regex.Pattern;

import internal.su.pernova.assertions.matchers.All;
import internal.su.pernova.assertions.matchers.AllOf;
import internal.su.pernova.assertions.matchers.Any;
import internal.su.pernova.assertions.matchers.AnyOf;
import internal.su.pernova.assertions.matchers.CloseTo;
import internal.su.pernova.assertions.matchers.Context;
import internal.su.pernova.assertions.matchers.ContextSensitive;
import internal.su.pernova.assertions.matchers.EqualTo;
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
import internal.su.pernova.assertions.matchers.Regex;

/**
 * This utility class provides all built-in matchers.
 * It is recommended to use static imports for readability.
 *
 * @since 1.0.0
 */
public final class Matchers {

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
		return new Is("matches", delegate);
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
		return new Is("match", delegate);
	}

	/**
	 * Returns an identity matcher decorating a given matcher.
	 *
	 * @param delegate a given matcher to match, which may be {@code null}.
	 * @return an identity matcher decorating a given matcher, not {@code null}.
	 * @since 2.0.0
	 */
	public static Matcher is(Matcher delegate) {
		if (delegate != null) {
			return provideContext(new Is(delegate), Is.CONTEXT);
		}
		return is((Object) null);
	}

	/**
	 * Returns a matcher matching a given object using the "==" operator.
	 * This works for any object, including {@code null}.
	 *
	 * @param expected a given object, which may be {@code null}.
	 * @return a matcher matching by identity, not {@code null}.
	 * @since 2.0.0
	 */
	public static Matcher is(Object expected) {
		return provideContext(new IsObject(expected), Is.CONTEXT);
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
		return provideContext(new IsDouble(expected), Is.CONTEXT);
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
	 * @param expected an expected float.
	 * @return a matcher using the "==" operator, not {@code null}.
	 * @since 2.0.0
	 */
	public static Matcher is(float expected) {
		return provideContext(new IsFloat(expected), Is.CONTEXT);
	}

	/**
	 * Returns a matcher matching a given expected primitive long using the "==" operator.
	 * Beware that the "==" operator behaves differently for primitive longs than for {@link Long} objects.
	 * For example {@code 1L == 1} is {@code true} but {@code Long.valueOf(1L) == Integer.valueOf(1)} is {@code false}.
	 *
	 * @param expected an expected long.
	 * @return a matcher using the "==" operator, not {@code null}.
	 * @since 2.0.0
	 */
	public static Matcher is(long expected) {
		return provideContext(new IsLong(expected), Is.CONTEXT);
	}

	/**
	 * Returns a matcher matching a given expected primitive int using the "==" operator.
	 * Beware that the "==" operator behaves differently for primitive ints than for {@link Integer} objects.
	 * For example {@code 1L == 1} is {@code true} but {@code Long.valueOf(1L) == Integer.valueOf(1)} is {@code false}.
	 *
	 * @param expected an expected int.
	 * @return a matcher using the "==" operator, not {@code null}.
	 * @since 2.0.0
	 */
	public static Matcher is(int expected) {
		return provideContext(new IsInt(expected), Is.CONTEXT);
	}

	/**
	 * Returns a matcher matching a given expected primitive int using the "==" operator.
	 * Beware that the "==" operator behaves differently for primitive shorts than for {@link Short} objects.
	 * For example {@code ((short) 1) == 1} is {@code true} but {@code Short.valueOf((short) 1) == Integer.valueOf(1)}
	 * is {@code false}.
	 *
	 * @param expected an expected short.
	 * @return a matcher using the "==" operator, not {@code null}.
	 * @since 2.0.0
	 */
	public static Matcher is(short expected) {
		return provideContext(new IsShort(expected), Is.CONTEXT);
	}

	/**
	 * Returns a matcher matching a given expected primitive byte using the "==" operator.
	 * Beware that the "==" operator behaves differently for primitive bytes than for {@link Byte} objects.
	 * For example {@code ((byte) 1) == 1} is {@code true} but {@code Byte.valueOf((byte) 1) == Integer.valueOf(1)}
	 * is {@code false}.
	 *
	 * @param expected an expected byte.
	 * @return a matcher using the "==" operator, not {@code null}.
	 * @since 2.0.0
	 */
	public static Matcher is(byte expected) {
		return provideContext(new IsByte(expected), Is.CONTEXT);
	}

	/**
	 * Returns a matcher matching a given expected primitive char using the "==" operator.
	 * Beware that the "==" operator behaves differently for primitive chars than for {@link Character} objects.
	 * For example {@code ('\u0001') == 1} is {@code true} but {@code Character.valueOf('\u0001') == Integer.valueOf(1)}
	 * is {@code false}.
	 *
	 * @param expected an expected char.
	 * @return a matcher using the "==" operator, not {@code null}.
	 * @since 2.0.0
	 */
	public static Matcher is(char expected) {
		return provideContext(new IsChar(expected), Is.CONTEXT);
	}

	/**
	 * Returns a matcher matching a given expected primitive boolean using the "==" operator.
	 * The "==" operator behaves the same for primitive booleans than for {@link Boolean} objects.
	 * This method is provided for completeness and consistency with the other {@code is} methods that take a primitive
	 * type as parameter.
	 *
	 * @param expected an expected boolean.
	 * @return a matcher using the "==" operator, not {@code null}.
	 * @since 2.0.0
	 */
	public static Matcher is(boolean expected) {
		return provideContext(new IsBoolean(expected), Is.CONTEXT);
	}

	/**
	 * Returns a matcher that matches objects using to the {@link Objects#equals(Object, Object)} operator.
	 * The object to match may be {@code null}.
	 *
	 * @param expected an object to match, which may be {@code null}.
	 * @return a matcher, not {@code null}, that matches equal objects.
	 * @since 1.0.0
	 */
	public static Matcher equalTo(Object expected) {
		return provideContext(new EqualTo(expected), EqualTo.CONTEXT);
	}

	/**
	 * Returns a context-providing matcher that matches by equality.
	 * It provides context for a context-sensitive matcher such as {@link #allOf(Object...)}.
	 *
	 * @param delegate a context-sensitive matcher, not {@code null}.
	 * @return a context-providing matcher, not {@code null}.
	 * @since 2.0.0
	 */
	public static Matcher equalTo(Matcher delegate) {
		if (delegate != null) {
			return provideContext(new Is("equal to", delegate), EqualTo.CONTEXT);
		}
		return equalTo((Object) null);
	}

	/**
	 * Returns a matcher that matches if an object is of a given instance, according to the {@code instanceof} operator.
	 * The object to match may be {@code null}
	 *
	 * @param class_ a class to match (including subtypes).
	 * @return a matcher, not {@code null}, that matches is an object is of a given instance.
	 * @since 1.0.0
	 */
	public static Matcher instanceOf(Class class_) {
		return provideContext(new InstanceOf(class_), InstanceOf.CONTEXT);
	}

	/**
	 * Returns a context-providing matcher that provides the "instance of" context to a context-sensitive matcher such
	 * as {@link #anyOf(Object...)}.
	 *
	 * @param delegate a context-sensitive matcher, not {@code null}.
	 * @return a context-providing matcher, not {@code null}.
	 * context-sensitive matcher.
	 * @since 2.0.0
	 */
	public static Matcher instanceOf(Matcher delegate) {
		return provideContext(new Is("instance of", delegate), InstanceOf.CONTEXT);
	}

	/**
	 * Returns a matcher that matches identical objects, including {@code null}.
	 * Such a matcher uses the {@code ==} operator.
	 *
	 * @param expected the object to match
	 * @return a matcher that matches identical objects, not {@code null}.
	 * @since 1.0.0
	 */
	public static Matcher sameAs(Object expected) {
		return provideContext(new IsObject("same as", true, expected), Is.CONTEXT);
	}

	/**
	 * Returns a context-providing matcher that matches by identity.
	 * It provides context for a context-sensitive matcher such as {@link #allOf(Object...)}.
	 *
	 * @param delegate a context-sensitive matcher, not {@code null}.
	 * @return a context-providing matcher, not {@code null}.
	 * @since 2.0.0
	 */
	public static Matcher sameAs(Matcher delegate) {
		if (delegate != null) {
			return provideContext(new Is("same as", delegate), Is.CONTEXT);
		}
		return sameAs((Object) null);
	}

	/**
	 * Returns a matcher matching a given regex.
	 *
	 * @param regex a regex to match, must not be {@code null}.
	 * @return a matcher matching against a given regex, not {@code null}.
	 * @since 1.1.0
	 */
	public static Matcher regex(Pattern regex) {
		return new Regex(regex);
	}

	/**
	 * Returns a matcher matching a given regex.
	 *
	 * @param regex a regex to match, must not be {@code null}.
	 * @return a matcher matching against a given regex, not {@code null}.
	 * @since 1.1.0
	 */
	public static Matcher regex(String regex) {
		return new Regex(regex);
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
	 * Returns a matcher that matches like a given matcher and prefixes its description with "does".
	 * This is useful for matching negated regular expressions:
	 * <pre class="code"><code class="java">
	 * assertThat("apple", does(not(match(regex("[0-9]*")))));
	 * </code></pre>
	 *
	 * @param delegate a given delegate, which must not be {@code null}.
	 * @return a matcher that prefixes the description with "does".
	 * @since 2.0.0
	 */
	public static Matcher does(Matcher delegate) {
		return new Is("does", delegate);
	}

	/**
	 * Returns a matcher that matches when a given matcher does not and vice versa.
	 * This is useful for matching negations:
	 * <pre class="code"><code class="java">
	 * assertThat(1, is(not(equalTo(2))));
	 * </code></pre>
	 *
	 * @param delegate a given delegate, which must not be {@code null}.
	 * @return a matcher that matches when a given matcher does not and vice versa.
	 * @since 2.0.0
	 */
	public static Matcher not(Matcher delegate) {
		return new Not(delegate);
	}

	/**
	 * Returns a matcher that matches when a given object does not.
	 *
	 * @param expected an object to not match, which may be {@code null}.
	 * @return a matcher that does not match a given object.
	 */
	public static Matcher not(Object expected) {
		return new Not(new IsObject("", true, expected));
	}

	/**
	 * Returns a matcher that matches when a given double value does not.
	 *
	 * @param expected a double value to not match.
	 * @return a matcher that matches when a given double value does not.
	 */
	public static Matcher not(double expected) {
		return new Not(new IsDouble("", true, expected));
	}

	/**
	 * Returns a matcher that combines multiple matchers with a logical OR operator.
	 * If the matchers are empty, the matcher trivially does not match.
	 *
	 * @param matchers the matchers to combine with a logical OR operation, which must not be {@code null}.
	 * @return a matcher that combines multiple matchers with a logical OR operator, not {@code null}.
	 * @since 2.0.0
	 */
	public static Matcher anyOf(Matcher... matchers) {
		return new AnyOf(matchers);
	}

	/**
	 * Returns a context-sensitive matcher that combines multiple values with a logical OR operator.
	 * This matcher must be used in combination with a context-providing matcher, which defines how all the individual
	 * values match.
	 *
	 * @param expected multiple values, which must not be {@code null} as an array.
	 * Individual elements in the array may be {@code null}.
	 * @return a context-sensitive matcher that combines multiple values with a logical OR operator, not {@code null}.
	 * @since 2.0.0
	 */
	public static Matcher anyOf(Object... expected) {
		return requireBackwardContext(Any.of(expected));
	}

	/**
	 * Returns a context-sensitive matcher that combines multiple values with a logical OR operator.
	 * This matcher must be used in combination with a context-providing matcher, which defines how all the individual
	 * values match.
	 *
	 * @param expected multiple values, which must not be {@code null}.
	 * @return a context-sensitive matcher that combines multiple values with a logical OR operator, not {@code null}.
	 * @since 2.0.0
	 */
	public static Matcher anyOf(double... expected) {
		return requireBackwardContext(Any.of(expected));
	}

	/**
	 * Returns a context-sensitive matcher that combines multiple values with a logical OR operator.
	 * This matcher must be used in combination with a context-providing matcher, which defines how all the individual
	 * values match.
	 *
	 * @param expected multiple values, which must not be {@code null}.
	 * @return a context-sensitive matcher that combines multiple values with a logical OR operator, not {@code null}.
	 * @since 2.0.0
	 */
	public static Matcher anyOf(float... expected) {
		return requireBackwardContext(Any.of(expected));
	}

	/**
	 * Returns a context-sensitive matcher that combines multiple values with a logical OR operator.
	 * This matcher must be used in combination with a context-providing matcher, which defines how all the individual
	 * values match.
	 *
	 * @param expected multiple values, which must not be {@code null}.
	 * @return a context-sensitive matcher that combines multiple values with a logical OR operator, not {@code null}.
	 * @since 2.0.0
	 */
	public static Matcher anyOf(long... expected) {
		return requireBackwardContext(Any.of(expected));
	}

	/**
	 * Returns a context-sensitive matcher that combines multiple values with a logical OR operator.
	 * This matcher must be used in combination with a context-providing matcher, which defines how all the individual
	 * values match.
	 *
	 * @param expected multiple values, which must not be {@code null}.
	 * @return a context-sensitive matcher that combines multiple values with a logical OR operator, not {@code null}.
	 * @since 2.0.0
	 */
	public static Matcher anyOf(int... expected) {
		return requireBackwardContext(Any.of(expected));
	}

	/**
	 * Returns a context-sensitive matcher that combines multiple values with a logical OR operator.
	 * This matcher must be used in combination with a context-providing matcher, which defines how all the individual
	 * values match.
	 *
	 * @param expected multiple values, which must not be {@code null}.
	 * @return a context-sensitive matcher that combines multiple values with a logical OR operator, not {@code null}.
	 * @since 2.0.0
	 */
	public static Matcher anyOf(short... expected) {
		return requireBackwardContext(Any.of(expected));
	}

	/**
	 * Returns a context-sensitive matcher that combines multiple values with a logical OR operator.
	 * This matcher must be used in combination with a context-providing matcher, which defines how all the individual
	 * values match.
	 *
	 * @param expected multiple values, which must not be {@code null}.
	 * @return a context-sensitive matcher that combines multiple values with a logical OR operator, not {@code null}.
	 * @since 2.0.0
	 */
	public static Matcher anyOf(byte... expected) {
		return requireBackwardContext(Any.of(expected));
	}

	/**
	 * Returns a context-sensitive matcher that combines multiple values with a logical OR operator.
	 * This matcher must be used in combination with a context-providing matcher, which defines how all the individual
	 * values match.
	 *
	 * @param expected multiple values, which must not be {@code null}.
	 * @return a context-sensitive matcher that combines multiple values with a logical OR operator, not {@code null}.
	 * @since 2.0.0
	 */
	public static Matcher anyOf(char... expected) {
		return requireBackwardContext(Any.of(expected));
	}

	/**
	 * Returns a context-sensitive matcher that combines multiple values with a logical OR operator.
	 * This matcher must be used in combination with a context-providing matcher, which defines how all the individual
	 * values match.
	 *
	 * @param expected multiple values, which must not be {@code null}.
	 * @return a context-sensitive matcher that combines multiple values with a logical OR operator, not {@code null}.
	 * @since 2.0.0
	 */
	public static Matcher anyOf(boolean... expected) {
		return requireBackwardContext(Any.of(expected));
	}

	/**
	 * Returns a matcher that combines multiple matchers with a logical AND operator.
	 * If the matchers are empty, the matcher trivially matches.
	 *
	 * @param delegates the matchers to combine with a logical AND operation, which must not be {@code null}.
	 * @return a matcher that combines multiple matchers with a logical AND operator, not {@code null}.
	 * @since 2.0.0
	 */
	public static Matcher allOf(Matcher... delegates) {
		return new AllOf(delegates);
	}

	/**
	 * Returns a context-sensitive matcher that combines multiple values with a logical AND operator.
	 * This matcher must be used in combination with a context-providing matcher, which defines how all the individual
	 * values match.
	 *
	 * @param expected multiple values, which must not be {@code null} as an array.
	 * Individual elements in the array may be {@code null}.
	 * @return a context-sensitive matcher that combines multiple values with a logical OR operator, not {@code null}.
	 * @since 2.0.0
	 */
	public static Matcher allOf(Object... expected) {
		return requireBackwardContext(All.of(expected));
	}

	/**
	 * Returns a context-sensitive matcher that combines multiple values with a logical OR operator.
	 * This matcher must be used in combination with a context-providing matcher, which defines how all the individual
	 * values match.
	 *
	 * @param expected multiple values, which must not be {@code null}.
	 * @return a context-sensitive matcher that combines multiple values with a logical OR operator, not {@code null}.
	 * @since 2.0.0
	 */
	public static Matcher allOf(double... expected) {
		return requireBackwardContext(All.of(expected));
	}

	/**
	 * Returns a context-sensitive matcher that combines multiple values with a logical OR operator.
	 * This matcher must be used in combination with a context-providing matcher, which defines how all the individual
	 * values match.
	 *
	 * @param expected multiple values, which must not be {@code null}.
	 * @return a context-sensitive matcher that combines multiple values with a logical OR operator, not {@code null}.
	 * @since 2.0.0
	 */
	public static Matcher allOf(float... expected) {
		return requireBackwardContext(All.of(expected));
	}

	/**
	 * Returns a context-sensitive matcher that combines multiple values with a logical OR operator.
	 * This matcher must be used in combination with a context-providing matcher, which defines how all the individual
	 * values match.
	 *
	 * @param expected multiple values, which must not be {@code null}.
	 * @return a context-sensitive matcher that combines multiple values with a logical OR operator, not {@code null}.
	 * @since 2.0.0
	 */
	public static Matcher allOf(long... expected) {
		return requireBackwardContext(All.of(expected));
	}

	/**
	 * Returns a context-sensitive matcher that combines multiple values with a logical OR operator.
	 * This matcher must be used in combination with a context-providing matcher, which defines how all the individual
	 * values match.
	 *
	 * @param expected multiple values, which must not be {@code null}.
	 * @return a context-sensitive matcher that combines multiple values with a logical OR operator, not {@code null}.
	 * @since 2.0.0
	 */
	public static Matcher allOf(int... expected) {
		return requireBackwardContext(All.of(expected));
	}

	/**
	 * Returns a context-sensitive matcher that combines multiple values with a logical OR operator.
	 * This matcher must be used in combination with a context-providing matcher, which defines how all the individual
	 * values match.
	 *
	 * @param expected multiple values, which must not be {@code null}.
	 * @return a context-sensitive matcher that combines multiple values with a logical OR operator, not {@code null}.
	 * @since 2.0.0
	 */
	public static Matcher allOf(short... expected) {
		return requireBackwardContext(All.of(expected));
	}

	/**
	 * Returns a context-sensitive matcher that combines multiple values with a logical OR operator.
	 * This matcher must be used in combination with a context-providing matcher, which defines how all the individual
	 * values match.
	 *
	 * @param expected multiple values, which must not be {@code null}.
	 * @return a context-sensitive matcher that combines multiple values with a logical OR operator, not {@code null}.
	 * @since 2.0.0
	 */
	public static Matcher allOf(byte... expected) {
		return requireBackwardContext(All.of(expected));
	}

	/**
	 * Returns a context-sensitive matcher that combines multiple values with a logical OR operator.
	 * This matcher must be used in combination with a context-providing matcher, which defines how all the individual
	 * values match.
	 *
	 * @param expected multiple values, which must not be {@code null}.
	 * @return a context-sensitive matcher that combines multiple values with a logical OR operator, not {@code null}.
	 * @since 2.0.0
	 */
	public static Matcher allOf(char... expected) {
		return requireBackwardContext(All.of(expected));
	}

	/**
	 * Returns a context-sensitive matcher that combines multiple values with a logical OR operator.
	 * This matcher must be used in combination with a context-providing matcher, which defines how all the individual
	 * values match.
	 *
	 * @param expected multiple values, which must not be {@code null}.
	 * @return a context-sensitive matcher that combines multiple values with a logical OR operator, not {@code null}.
	 * @since 2.0.0
	 */
	public static Matcher allOf(boolean... expected) {
		return requireBackwardContext(All.of(expected));
	}
}
