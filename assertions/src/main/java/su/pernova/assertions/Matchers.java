package su.pernova.assertions;

import java.util.Objects;
import java.util.regex.Pattern;

import internal.su.pernova.assertions.matchers.CloseTo;
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
import internal.su.pernova.assertions.matchers.IsShort;
import internal.su.pernova.assertions.matchers.Nan;
import internal.su.pernova.assertions.matchers.Regex;

/**
 * This utility class provides factory methods for all matchers.
 * It is recommended to use static imports for readability.
 *
 * @since 1.0.0
 */
public final class Matchers {

	private Matchers() {
	}

	/**
	 * Creates a matcher matching a given expected object using the "==" operator.
	 * This works for any object, including {@code null}.
	 * Typically, you would call {@ling Assertion#is(Object)} instead of this method.
	 *
	 * @param expected an expected object, which may be {@code null}.
	 * @return a matcher matching by identity, not {@code null}.
	 * @link Assertion#is(Object)
	 * @since 2.0.0
	 */
	public static Matcher is(Object expected) {
		return new Is(expected);
	}

	/**
	 * Creates a matcher matching a given expected primitive double using the "==" operator.
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
	 * @see Assertion#is(double)
	 * @since 2.0.0
	 */
	public static Matcher is(double expected) {
		return new IsDouble(expected);
	}

	/**
	 * Creates a matcher matching a given expected primitive float using the "==" operator.
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
	 * @see Assertion#is(float)
	 * @since 2.0.0
	 */
	public static Matcher is(float expected) {
		return new IsFloat(expected);
	}

	/**
	 * Creates a matcher matching a given expected primitive long using the "==" operator.
	 * Beware that the "==" operator behaves differently for primitive longs than for {@link Long} objects.
	 * For example {@code 1L == 1} is {@code true} but {@code Long.valueOf(1L) == Integer.valueOf(1)} is {@code false}.
	 *
	 * @param expected an expected long.
	 * @return a matcher using the "==" operator, not {@code null}.
	 * @since 2.0.0
	 */
	public static Matcher is(long expected) {
		return new IsLong(expected);
	}

	/**
	 * Creates a matcher matching a given expected primitive int using the "==" operator.
	 * Beware that the "==" operator behaves differently for primitive ints than for {@link Integer} objects.
	 * For example {@code 1L == 1} is {@code true} but {@code Long.valueOf(1L) == Integer.valueOf(1)} is {@code false}.
	 *
	 * @param expected an expected int.
	 * @return a matcher using the "==" operator, not {@code null}.
	 * @since 2.0.0
	 */
	public static Matcher is(int expected) {
		return new IsInt(expected);
	}

	/**
	 * Creates a matcher matching a given expected primitive int using the "==" operator.
	 * Beware that the "==" operator behaves differently for primitive shorts than for {@link Short} objects.
	 * For example {@code ((short) 1) == 1} is {@code true} but {@code Short.valueOf((short) 1) == Integer.valueOf(1)}
	 * is {@code false}.
	 *
	 * @param expected an expected short.
	 * @return a matcher using the "==" operator, not {@code null}.
	 * @since 2.0.0
	 */
	public static Matcher is(short expected) {
		return new IsShort(expected);
	}

	/**
	 * Creates a matcher matching a given expected primitive byte using the "==" operator.
	 * Beware that the "==" operator behaves differently for primitive bytes than for {@link Byte} objects.
	 * For example {@code ((byte) 1) == 1} is {@code true} but {@code Byte.valueOf((byte) 1) == Integer.valueOf(1)}
	 * is {@code false}.
	 *
	 * @param expected an expected byte.
	 * @return a matcher using the "==" operator, not {@code null}.
	 * @since 2.0.0
	 */
	public static Matcher is(byte expected) {
		return new IsByte(expected);
	}

	/**
	 * Creates a matcher matching a given expected primitive char using the "==" operator.
	 * Beware that the "==" operator behaves differently for primitive chars than for {@link Character} objects.
	 * For example {@code ('\u0001') == 1} is {@code true} but {@code Character.valueOf('\u0001') == Integer.valueOf(1)}
	 * is {@code false}.
	 *
	 * @param expected an expected char.
	 * @return a matcher using the "==" operator, not {@code null}.
	 * @since 2.0.0
	 */
	public static Matcher is(char expected) {
		return new IsChar(expected);
	}

	/**
	 * Creates a matcher matching a given expected primitive boolean using the "==" operator.
	 * The "==" operator behaves the same for primitive booleans than for {@link Boolean} objects.
	 * This method is provided for completeness and consistency with the other {@code is} methods that take a primitive
	 * type as parameter.
	 *
	 * @param expected an expected boolean.
	 * @return a matcher using the "==" operator, not {@code null}.
	 * @since 2.0.0
	 */
	public static Matcher is(boolean expected) {
		return new IsBoolean(expected);
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
		return new EqualTo(expected);
	}

	/**
	 * Returns a matcher that matches if an object is of a given instance, according to the {@code instanceof} operator.
	 * The object to match may be {@code null}
	 *
	 * @param clazz a class to match (including subtypes).
	 * @return a matcher, not {@code null}, that matches is an object is of a given instance.
	 * @since 1.0.0
	 */
	public static Matcher instanceOf(Class clazz) {
		return new InstanceOf(clazz);
	}

	/**
	 * Returns a matcher that matches identical objects, including {@code null}.
	 * Such a matcher uses the {@code ==} operator.
	 *
	 * @param expected the object to match
	 * @return
	 * @since 1.0.0
	 */
	public static Matcher sameAs(Object expected) {
		return new Is(expected);
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
	 * @param expected
	 * @param tolerance
	 * @return
	 * @since 2.0.0
	 */
	public static Matcher closeTo(Number expected, Number tolerance) {
		return new CloseTo(expected, tolerance);
	}
//
//	public static Matcher greaterThan(Comparable comparable) {
//		return new GreaterThan(comparable);
//	}
//
//	public static Matcher lessThan(Comparable comparable) {
//		return new LessThan(comparable);
//	}
}
