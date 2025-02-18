package su.pernova.assertions;

import static internal.su.pernova.assertions.matchers.ContextSensitive.requireForwardContext;

import internal.su.pernova.assertions.matchers.And;
import internal.su.pernova.assertions.matchers.ContextSensitive;
import internal.su.pernova.assertions.matchers.Or;

/**
 * This interface defines a matcher for matching an actual object.
 * Implementations must obey the following contract:
 * <ul>
 *     <li>Be able to match any object of any type without throwing, in particular {@code null}.</li>
 * </ul>
 *
 * @since 1.0.0
 */
public interface Matcher extends Describable {

	/**
	 * Matches this matcher against a given object, returning {@code true} in case of match and {@code false} otherwise.
	 *
	 * @param actual a given object to match, which may be {@code null}.
	 * @return {@code true} in case of match, {@code false} otherwise.
	 * @since 1.0.0
	 */
	boolean match(Object actual);

	/**
	 * Composes this matcher with another one to create a logical AND matcher that matches if both matchers match.
	 *
	 * @param matcher another matcher to compose a logical AND matcher with, not {@code null}.
	 * @return a logical AND matcher, not {@code null}.
	 * @since 2.0.0
	 */
	default Matcher and(Matcher matcher) {
		return new And(this, matcher);
	}

	default Matcher and(Object value) {
		return requireForwardContext(And.of(this, value));
	}

	default Matcher and(double value) {
		return requireForwardContext(And.of(this, value));
	}

	default Matcher and(float value) {
		return requireForwardContext(And.of(this, value));
	}

	default Matcher and(long value) {
		return requireForwardContext(And.of(this, value));
	}

	default Matcher and(int value) {
		return requireForwardContext(And.of(this, value));
	}

	default Matcher and(short value) {
		return requireForwardContext(And.of(this, value));
	}

	default Matcher and(byte value) {
		return requireForwardContext(And.of(this, value));
	}

	default Matcher and(char value) {
		return requireForwardContext(And.of(this, value));
	}

	default Matcher and(boolean value) {
		return requireForwardContext(And.of(this, value));
	}

	/**
	 * Composes this matcher with another one to create a logical OR matcher that matches if any of the matchers
	 * matches.
	 *
	 * @param matcher another matcher to compose a logical OR matcher with, not {@code null}.
	 * @return a logical OR matcher, not {@code null}.
	 * @since 2.0.0
	 */
	default Matcher or(Matcher matcher) {
		return new Or(this, matcher);
	}

	default Matcher or(Object value) {
		return requireForwardContext(Or.of(this, value));
	}

	default Matcher or(double value) {
		return requireForwardContext(Or.of(this, value));
	}

	default Matcher or(float value) {
		return requireForwardContext(Or.of(this, value));
	}

	default Matcher or(long value) {
		return requireForwardContext(Or.of(this, value));
	}

	default Matcher or(int value) {
		return requireForwardContext(Or.of(this, value));
	}

	default Matcher or(short value) {
		return requireForwardContext(Or.of(this, value));
	}

	default Matcher or(byte value) {
		return requireForwardContext(Or.of(this, value));
	}

	default Matcher or(char value) {
		return requireForwardContext(Or.of(this, value));
	}

	default Matcher or(boolean value) {
		return requireForwardContext(Or.of(this, value));
	}
}
