package su.pernova.assertions;

import java.util.Objects;
import java.util.regex.Pattern;

import internal.su.pernova.assertions.matchers.EqualTo;
import internal.su.pernova.assertions.matchers.InstanceOf;
import internal.su.pernova.assertions.matchers.Regex;
import internal.su.pernova.assertions.matchers.SameAs;

public final class Matchers {

	private Matchers() {
	}

	/**
	 * A matcher that matches equal objects according to the {@link Objects#equals(Object, Object)} operator.
	 * The object to match may be {@code null}.
	 *
	 * @param expected an object to match, which may be {@code null}.
	 * @return a matcher, not {@code null}, that matches equal objects.
	 */
	public static Matcher equalTo(Object expected) {
		return new EqualTo(expected);
	}

	/**
	 * A matcher that matches if an object is of a given instance, according to the {@code instanceof} operator.
	 * The object to match may be {@code null}
	 *
	 * @param clazz a class to match (including subtypes).
	 * @return a matcher, not {@code null}, that matches is an object is of a given instance.
	 */
	public static Matcher instanceOf(Class clazz) {
		return new InstanceOf(clazz);
	}

	/**
	 * A matcher that matches identical objects, including {@code null}.
	 * Such a matcher uses the {@code ==} operator.
	 *
	 * @param expected the object to match
	 * @return
	 */
	public static Matcher sameAs(Object expected) {
		return new SameAs(null, expected);
	}

	/**
	 * Creates a matcher matching a given regex, not matching {@code null}.
	 *
	 * @param regex a regex to match, must not be {@code null}.
	 * @return a matcher matching against a given regex, not {@code null}.
	 */
	public static Matcher regex(Pattern regex) {
		return new Regex(regex);
	}

	/**
	 * Creates a matcher matching a given regex, not matching {@code null}.
	 *
	 * @param regex a regex to match, must not be {@code null}.
	 * @return a matcher matching against a given regex, not {@code null}.
	 */
	public static Matcher regex(String regex) {
		return new Regex(regex);
	}
}
