package su.pernova.assertions;

import internal.su.pernova.assertions.matchers.And;
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
	 * @param actualValue a given object to match, which may be {@code null}.
	 * @return {@code true} in case of match, {@code false} otherwise.
	 * @since 1.0.0
	 */
	boolean match(Object actualValue);

	/**
	 * Appends "was" to a given mismatch description and returns the same mismatch description.
	 *
	 * @param mismatchDescription a mismatch description, not {@code null}.
	 * @return the mismatch description which is given as parameter.
	 */
	@Override
	default Description describeMismatch(Description mismatchDescription) {
		return mismatchDescription.appendText("was");
	}

	/**
	 * Returns a logical AND matcher that matches if both this and another given matcher match.
	 *
	 * @param matcher another matcher, not {@code null}.
	 * @return a logical AND matcher, not {@code null}.
	 * @since 2.0.0
	 */
	default Matcher and(Matcher matcher) {
		return Context.fork(And::new, this, matcher);
	}

	/**
	 * Returns a logical AND matcher that matches if both this and another given expected value match.
	 *
	 * @param expectedValue another expected value, possibly {@code null}.
	 * @return a logical AND matcher, not {@code null}.
	 * @since 2.0.0
	 */
	default Matcher and(Object expectedValue) {
		return Context.fork(And::new, this, (CompletionFunction) matcherFactory -> matcherFactory.create(expectedValue));
	}

	/**
	 * Returns a logical AND matcher that matches if both this and another given expected value match.
	 *
	 * @param expectedValue another expected value, possibly {@code null}.
	 * @return a logical AND matcher, not {@code null}.
	 * @since 2.0.0
	 */
	default Matcher and(double expectedValue) {
		return Context.fork(And::new, this, (CompletionFunction) matcherFactory -> matcherFactory.create(expectedValue));
	}

	/**
	 * Returns a logical AND matcher that matches if both this and another given expected value match.
	 *
	 * @param expectedValue another expected value, possibly {@code null}.
	 * @return a logical AND matcher, not {@code null}.
	 * @since 2.0.0
	 */
	default Matcher and(float expectedValue) {
		return Context.fork(And::new, this, (CompletionFunction) matcherFactory -> matcherFactory.create(expectedValue));
	}

	/**
	 * Returns a logical AND matcher that matches if both this and another given expected value match.
	 *
	 * @param expectedValue another expected value, possibly {@code null}.
	 * @return a logical AND matcher, not {@code null}.
	 * @since 2.0.0
	 */
	default Matcher and(long expectedValue) {
		return Context.fork(And::new, this, (CompletionFunction) matcherFactory -> matcherFactory.create(expectedValue));
	}

	/**
	 * Returns a logical AND matcher that matches if both this and another given expected value match.
	 *
	 * @param expectedValue another expected value, possibly {@code null}.
	 * @return a logical AND matcher, not {@code null}.
	 * @since 2.0.0
	 */
	default Matcher and(int expectedValue) {
		return Context.fork(And::new, this, (CompletionFunction) matcherFactory -> matcherFactory.create(expectedValue));
	}

	/**
	 * Returns a logical AND matcher that matches if both this and another given expected value match.
	 *
	 * @param expectedValue another expected value, possibly {@code null}.
	 * @return a logical AND matcher, not {@code null}.
	 * @since 2.0.0
	 */
	default Matcher and(short expectedValue) {
		return Context.fork(And::new, this, (CompletionFunction) matcherFactory -> matcherFactory.create(expectedValue));
	}

	/**
	 * Returns a logical AND matcher that matches if both this and another given expected value match.
	 *
	 * @param expectedValue another expected value, possibly {@code null}.
	 * @return a logical AND matcher, not {@code null}.
	 * @since 2.0.0
	 */
	default Matcher and(byte expectedValue) {
		return Context.fork(And::new, this, (CompletionFunction) matcherFactory -> matcherFactory.create(expectedValue));
	}

	/**
	 * Returns a logical AND matcher that matches if both this and another given expected value match.
	 *
	 * @param expectedValue another expected value, possibly {@code null}.
	 * @return a logical AND matcher, not {@code null}.
	 * @since 2.0.0
	 */
	default Matcher and(char expectedValue) {
		return Context.fork(And::new, this, (CompletionFunction) matcherFactory -> matcherFactory.create(expectedValue));
	}

	/**
	 * Returns a logical AND matcher that matches if both this and another given expected value match.
	 *
	 * @param expectedValue another expected value, possibly {@code null}.
	 * @return a logical AND matcher, not {@code null}.
	 * @since 2.0.0
	 */
	default Matcher and(boolean expectedValue) {
		return Context.fork(And::new, this, (CompletionFunction) matcherFactory -> matcherFactory.create(expectedValue));
	}

	/**
	 * Returns a logical OR matcher that matches if either this or another given matcher match.
	 *
	 * @param matcher another matcher, not {@code null}.
	 * @return a logical OR matcher, not {@code null}.
	 * @since 2.0.0
	 */
	default Matcher or(Matcher matcher) {
		return Context.fork(Or::new, this, matcher);
	}

	/**
	 * Composes this matcher with a logical OR matcher for a given value.
	 * The behavior of the matcher depends on the context.
	 * It could for example behave as an "equal to", an "instance of", or any other matcher.
	 *
	 * @param expectedValue a value, possibly {@code null}.
	 * @return a matcher, not {@code null}.
	 * @since 2.0.0
	 */
	default Matcher or(Object expectedValue) {
		return Context.fork(Or::new, this, (CompletionFunction) matcherFactory -> matcherFactory.create(expectedValue));
	}

	/**
	 * Returns a logical OR matcher that matches if either this or another given expected value match.
	 *
	 * @param expectedValue another expected value, possibly {@code null}.
	 * @return a logical OR matcher, not {@code null}.
	 * @since 2.0.0
	 */
	default Matcher or(double expectedValue) {
		return Context.fork(Or::new, this, (CompletionFunction) matcherFactory -> matcherFactory.create(expectedValue));
	}

	/**
	 * Returns a logical OR matcher that matches if either this or another given expected value match.
	 *
	 * @param expectedValue another expected value, possibly {@code null}.
	 * @return a logical OR matcher, not {@code null}.
	 * @since 2.0.0
	 */
	default Matcher or(float expectedValue) {
		return Context.fork(Or::new, this, (CompletionFunction) matcherFactory -> matcherFactory.create(expectedValue));
	}

	/**
	 * Returns a logical OR matcher that matches if either this or another given expected value match.
	 *
	 * @param expectedValue another expected value, possibly {@code null}.
	 * @return a logical OR matcher, not {@code null}.
	 * @since 2.0.0
	 */
	default Matcher or(long expectedValue) {
		return Context.fork(Or::new, this, (CompletionFunction) matcherFactory -> matcherFactory.create(expectedValue));
	}

	/**
	 * Returns a logical OR matcher that matches if either this or another given expected value match.
	 *
	 * @param expectedValue another expected value, possibly {@code null}.
	 * @return a logical OR matcher, not {@code null}.
	 * @since 2.0.0
	 */
	default Matcher or(int expectedValue) {
		return Context.fork(Or::new, this, (CompletionFunction) matcherFactory -> matcherFactory.create(expectedValue));
	}

	/**
	 * Returns a logical OR matcher that matches if either this or another given expected value match.
	 *
	 * @param expectedValue another expected value, possibly {@code null}.
	 * @return a logical OR matcher, not {@code null}.
	 * @since 2.0.0
	 */
	default Matcher or(short expectedValue) {
		return Context.fork(Or::new, this, (CompletionFunction) matcherFactory -> matcherFactory.create(expectedValue));
	}

	/**
	 * Returns a logical OR matcher that matches if either this or another given expected value match.
	 *
	 * @param expectedValue another expected value, possibly {@code null}.
	 * @return a logical OR matcher, not {@code null}.
	 * @since 2.0.0
	 */
	default Matcher or(byte expectedValue) {
		return Context.fork(Or::new, this, (CompletionFunction) matcherFactory -> matcherFactory.create(expectedValue));
	}

	/**
	 * Returns a logical OR matcher that matches if either this or another given expected value match.
	 *
	 * @param expectedValue another expected value, possibly {@code null}.
	 * @return a logical OR matcher, not {@code null}.
	 * @since 2.0.0
	 */
	default Matcher or(char expectedValue) {
		return Context.fork(Or::new, this, (CompletionFunction) matcherFactory -> matcherFactory.create(expectedValue));
	}

	/**
	 * Returns a logical OR matcher that matches if either this or another given expected value match.
	 *
	 * @param expectedValue another expected value, possibly {@code null}.
	 * @return a logical OR matcher, not {@code null}.
	 * @since 2.0.0
	 */
	default Matcher or(boolean expectedValue) {
		return Context.fork(Or::new, this, (CompletionFunction) matcherFactory -> matcherFactory.create(expectedValue));
	}
}
