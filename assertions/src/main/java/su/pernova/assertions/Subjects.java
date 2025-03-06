package su.pernova.assertions;

import java.nio.charset.Charset;

import internal.su.pernova.assertions.subjects.CharacterEncodedContent;
import internal.su.pernova.assertions.subjects.Condition;
import internal.su.pernova.assertions.subjects.Content;
import internal.su.pernova.assertions.subjects.DefaultSubject;

/**
 * This utility class provides factory methods for all subjects.
 * By design, subjects can be created for any <em>actual</em> object, including {@code null},
 * which means the {@code actual} parameter to create a subject for is never typed but always a generic {@code Object}.
 * Even though it may not seem logical for some subjects to apply to any object,
 * bear in mind that subjects represent the <em>actual</em> value of an assertion,
 * which must be capable of modeling any kind of mismatch, so any kind of object.
 * Take a condition as an example: even though you <em>expect</em> a boolean, you <em>actually</em> may get anything,
 * which will mismatch if it's not a boolean, but you must be able to model it.
 *
 * @since 1.0.0
 */
public final class Subjects {

	private Subjects() {
	}

	/**
	 * Creates a subject for a given object.
	 *
	 * @param actual an object to create a subject for, possibly {@code null}.
	 * @return a subject, not {@code null}.
	 * @since 1.0.0
	 */
	public static Subject subject(Object actual) {
		return new DefaultSubject(actual);
	}

	/**
	 * Creates a condition for a given object.
	 *
	 * @param actual an object to create a condition for, possibly {@code null}.
	 * @return a condition, not {@code null}.
	 * @since 1.0.0
	 */
	public static Subject condition(Object actual) {
		return new Condition(actual);
	}

	/**
	 * Creates content for a given object.
	 *
	 * @param actual an object to create content for, possibly {@code null}.
	 * @return content, not {@code null}.
	 * @since 2.0.0
	 */
	public static Subject contentOf(Object actual) {
		return new Content(actual);
	}

	/**
	 * Creates character-encoded content for a given object.
	 *
	 * @param actual an object to create content for, possibly {@code null}.
	 * @param charset a charset, not {@code null}.
	 * @return character-encoded content, not {@code null}.
	 */
	public static Subject contentOf(Object actual, Charset charset) {
		return new CharacterEncodedContent(actual, charset);
	}

	static Subject defaultSubject(Object actual, Matcher... matchers) {
		return (matchers.length == 0) ? condition(actual) : subject(actual);
	}
}
