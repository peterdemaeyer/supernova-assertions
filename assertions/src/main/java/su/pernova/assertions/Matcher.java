package su.pernova.assertions;

import internal.su.pernova.assertions.matchers.And;
import internal.su.pernova.assertions.matchers.ContextualBooleanMatcher;
import internal.su.pernova.assertions.matchers.ContextualByteMatcher;
import internal.su.pernova.assertions.matchers.ContextualCharMatcher;
import internal.su.pernova.assertions.matchers.ContextualDoubleMatcher;
import internal.su.pernova.assertions.matchers.ContextualFloatMatcher;
import internal.su.pernova.assertions.matchers.ContextualIntMatcher;
import internal.su.pernova.assertions.matchers.ContextualLongMatcher;
import internal.su.pernova.assertions.matchers.ContextualObjectMatcher;
import internal.su.pernova.assertions.matchers.ContextualShortMatcher;
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
	 * Returns a logical AND matcher that matches if both this and another given matcher match.
	 *
	 * @param matcher another matcher, not {@code null}.
	 * @return a logical AND matcher, not {@code null}.
	 * @since 2.0.0
	 */
	default Matcher and(Matcher matcher) {
		return Context.set(new And(this, matcher)).replaceForwardingTo(this).get();
	}

	/**
	 * Returns a logical AND matcher that matches if both this and another given expected value match.
	 *
	 * @param expected another expected value, possibly {@code null}.
	 * @return a logical AND matcher, not {@code null}.
	 * @since 2.0.0
	 */
	default Matcher and(Object expected) {
		return Context.set(new And(this, new ContextualObjectMatcher(expected, true)))
				.replaceForwardingTo(this).get();
	}

	/**
	 * Returns a logical AND matcher that matches if both this and another given expected value match.
	 *
	 * @param expected another expected value, possibly {@code null}.
	 * @return a logical AND matcher, not {@code null}.
	 * @since 2.0.0
	 */
	default Matcher and(double expected) {
		return Context.set(new And(this, new ContextualDoubleMatcher(expected, true)))
				.replaceForwardingTo(this).get();
	}

	/**
	 * Returns a logical AND matcher that matches if both this and another given expected value match.
	 *
	 * @param expected another expected value, possibly {@code null}.
	 * @return a logical AND matcher, not {@code null}.
	 * @since 2.0.0
	 */
	default Matcher and(float expected) {
		return Context.set(new And(this, new ContextualFloatMatcher(expected, true)))
				.replaceForwardingTo(this).get();
	}

	/**
	 * Returns a logical AND matcher that matches if both this and another given expected value match.
	 *
	 * @param expected another expected value, possibly {@code null}.
	 * @return a logical AND matcher, not {@code null}.
	 * @since 2.0.0
	 */
	default Matcher and(long expected) {
		return Context.set(new And(this, new ContextualLongMatcher(expected, true)))
				.replaceForwardingTo(this).get();
	}

	/**
	 * Returns a logical AND matcher that matches if both this and another given expected value match.
	 *
	 * @param expected another expected value, possibly {@code null}.
	 * @return a logical AND matcher, not {@code null}.
	 * @since 2.0.0
	 */
	default Matcher and(int expected) {
		return Context.set(new And(this, new ContextualIntMatcher(expected, true)))
				.replaceForwardingTo(this).get();
	}

	/**
	 * Returns a logical AND matcher that matches if both this and another given expected value match.
	 *
	 * @param expected another expected value, possibly {@code null}.
	 * @return a logical AND matcher, not {@code null}.
	 * @since 2.0.0
	 */
	default Matcher and(short expected) {
		return Context.set(new And(this, new ContextualShortMatcher(expected, true)))
				.replaceForwardingTo(this).get();
	}

	/**
	 * Returns a logical AND matcher that matches if both this and another given expected value match.
	 *
	 * @param expected another expected value, possibly {@code null}.
	 * @return a logical AND matcher, not {@code null}.
	 * @since 2.0.0
	 */
	default Matcher and(byte expected) {
		return Context.set(new And(this, new ContextualByteMatcher(expected, true)))
				.replaceForwardingTo(this).get();
	}

	/**
	 * Returns a logical AND matcher that matches if both this and another given expected value match.
	 *
	 * @param expected another expected value, possibly {@code null}.
	 * @return a logical AND matcher, not {@code null}.
	 * @since 2.0.0
	 */
	default Matcher and(char expected) {
		return Context.set(new And(this, new ContextualCharMatcher(expected, true)))
				.replaceForwardingTo(this).get();
	}

	/**
	 * Returns a logical AND matcher that matches if both this and another given expected value match.
	 *
	 * @param expected another expected value, possibly {@code null}.
	 * @return a logical AND matcher, not {@code null}.
	 * @since 2.0.0
	 */
	default Matcher and(boolean expected) {
		return Context.set(new And(this, new ContextualBooleanMatcher(expected, true)))
				.replaceForwardingTo(this).get();
	}

	/**
	 * Returns a logical OR matcher that matches if either this or another given matcher match.
	 *
	 * @param matcher another matcher, not {@code null}.
	 * @return a logical OR matcher, not {@code null}.
	 * @since 2.0.0
	 */
	default Matcher or(Matcher matcher) {
		return Context.set(new Or(this, matcher)).replaceForwardingTo(this).get();
	}

	/**
	 * Composes this matcher with a logical OR matcher for a given value.
	 * The behavior of the matcher depends on the context.
	 * It could for example behave as an "equal to", an "instance of", or any other matcher.
	 *
	 * @param expected a value, possibly {@code null}.
	 * @return a matcher, not {@code null}.
	 * @since 2.0.0
	 */
	default Matcher or(Object expected) {
		return Context.set(new Or(this, new ContextualObjectMatcher(expected, true)))
				.replaceForwardingTo(this).get();
	}

	/**
	 * Returns a logical OR matcher that matches if either this or another given expected value match.
	 *
	 * @param expected another expected value, possibly {@code null}.
	 * @return a logical OR matcher, not {@code null}.
	 * @since 2.0.0
	 */
	default Matcher or(double expected) {
		return Context.set(new Or(this, new ContextualDoubleMatcher(expected, true)))
				.replaceForwardingTo(this).get();
	}

	/**
	 * Returns a logical OR matcher that matches if either this or another given expected value match.
	 *
	 * @param expected another expected value, possibly {@code null}.
	 * @return a logical OR matcher, not {@code null}.
	 * @since 2.0.0
	 */
	default Matcher or(float expected) {
		return Context.set(new Or(this, new ContextualFloatMatcher(expected, true)))
				.replaceForwardingTo(this).get();
	}

	/**
	 * Returns a logical OR matcher that matches if either this or another given expected value match.
	 *
	 * @param expected another expected value, possibly {@code null}.
	 * @return a logical OR matcher, not {@code null}.
	 * @since 2.0.0
	 */
	default Matcher or(long expected) {
		return Context.set(new Or(this, new ContextualLongMatcher(expected, true)))
				.replaceForwardingTo(this).get();
	}

	/**
	 * Returns a logical OR matcher that matches if either this or another given expected value match.
	 *
	 * @param expected another expected value, possibly {@code null}.
	 * @return a logical OR matcher, not {@code null}.
	 * @since 2.0.0
	 */
	default Matcher or(int expected) {
		return Context.set(new Or(this, new ContextualIntMatcher(expected, true)))
				.replaceForwardingTo(this).get();
	}

	/**
	 * Returns a logical OR matcher that matches if either this or another given expected value match.
	 *
	 * @param expected another expected value, possibly {@code null}.
	 * @return a logical OR matcher, not {@code null}.
	 * @since 2.0.0
	 */
	default Matcher or(short expected) {
		return Context.set(new Or(this, new ContextualShortMatcher(expected, true)))
				.replaceForwardingTo(this).get();
	}

	/**
	 * Returns a logical OR matcher that matches if either this or another given expected value match.
	 *
	 * @param expected another expected value, possibly {@code null}.
	 * @return a logical OR matcher, not {@code null}.
	 * @since 2.0.0
	 */
	default Matcher or(byte expected) {
		return Context.set(new Or(this, new ContextualByteMatcher(expected, true)))
				.replaceForwardingTo(this).get();
	}

	/**
	 * Returns a logical OR matcher that matches if either this or another given expected value match.
	 *
	 * @param expected another expected value, possibly {@code null}.
	 * @return a logical OR matcher, not {@code null}.
	 * @since 2.0.0
	 */
	default Matcher or(char expected) {
		return Context.set(new Or(this, new ContextualCharMatcher(expected, true)))
				.replaceForwardingTo(this).get();
	}

	/**
	 * Returns a logical OR matcher that matches if either this or another given expected value match.
	 *
	 * @param expected another expected value, possibly {@code null}.
	 * @return a logical OR matcher, not {@code null}.
	 * @since 2.0.0
	 */
	default Matcher or(boolean expected) {
		return Context.set(new Or(this, new ContextualBooleanMatcher(expected, true)))
				.replaceForwardingTo(this).get();
	}
}
