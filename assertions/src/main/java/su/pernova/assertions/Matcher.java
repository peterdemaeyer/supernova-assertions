package su.pernova.assertions;

import internal.su.pernova.assertions.matchers.And;
import internal.su.pernova.assertions.matchers.BooleanMatcher;
import internal.su.pernova.assertions.matchers.ByteMatcher;
import internal.su.pernova.assertions.matchers.CharMatcher;
import internal.su.pernova.assertions.matchers.DoubleMatcher;
import internal.su.pernova.assertions.matchers.FloatMatcher;
import internal.su.pernova.assertions.matchers.IntMatcher;
import internal.su.pernova.assertions.matchers.LongMatcher;
import internal.su.pernova.assertions.matchers.ObjectMatcher;
import internal.su.pernova.assertions.matchers.Or;
import internal.su.pernova.assertions.matchers.ShortMatcher;

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
	 * Returns this matcher by default, ignoring any context.
	 * Contextualizable implementations must override this method.
	 * Recommendations:
	 * <ul>
	 *     <li>Use a covariant return type.</li>
	 *     <li>Be prepared for this method to be called multiple times, potentially concurrently.</li>
	 *     <li>Return an immutable object.</li>
	 *     <li>Be stateless.</li>
	 * </ul>
	 *
	 * @param context a context, not {@code null}.
	 * @return this matcher.
	 * @since 2.0.0
	 */
	default Matcher contextualize(Context context) {
		return this;
	}

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
		return new And(this, matcher);
	}

	/**
	 * Returns a logical AND matcher that matches if both this and another given expected value match.
	 *
	 * @param expectedValue another expected value, possibly {@code null}.
	 * @return a logical AND matcher, not {@code null}.
	 * @since 2.0.0
	 */
	default Matcher and(Object expectedValue) {
		return new And(this, new ObjectMatcher("?", true, expectedValue));
	}

	/**
	 * Returns a logical AND matcher that matches if both this and another given expected value match.
	 *
	 * @param expectedValue another expected value, possibly {@code null}.
	 * @return a logical AND matcher, not {@code null}.
	 * @since 2.0.0
	 */
	default Matcher and(double expectedValue) {
		return new And(this, new DoubleMatcher("?", true, expectedValue));
	}

	/**
	 * Returns a logical AND matcher that matches if both this and another given expected value match.
	 *
	 * @param expectedValue another expected value, possibly {@code null}.
	 * @return a logical AND matcher, not {@code null}.
	 * @since 2.0.0
	 */
	default Matcher and(float expectedValue) {
		return new And(this, new FloatMatcher("?", true, expectedValue));
	}

	/**
	 * Returns a logical AND matcher that matches if both this and another given expected value match.
	 *
	 * @param expectedValue another expected value, possibly {@code null}.
	 * @return a logical AND matcher, not {@code null}.
	 * @since 2.0.0
	 */
	default Matcher and(long expectedValue) {
		return new And(this, new LongMatcher("?", true, expectedValue));
	}

	/**
	 * Returns a logical AND matcher that matches if both this and another given expected value match.
	 *
	 * @param expectedValue another expected value, possibly {@code null}.
	 * @return a logical AND matcher, not {@code null}.
	 * @since 2.0.0
	 */
	default Matcher and(int expectedValue) {
		return new And(this, new IntMatcher("?", true, expectedValue));
	}

	/**
	 * Returns a logical AND matcher that matches if both this and another given expected value match.
	 *
	 * @param expectedValue another expected value, possibly {@code null}.
	 * @return a logical AND matcher, not {@code null}.
	 * @since 2.0.0
	 */
	default Matcher and(short expectedValue) {
		return new And(this, new ShortMatcher("?", true, expectedValue));
	}

	/**
	 * Returns a logical AND matcher that matches if both this and another given expected value match.
	 *
	 * @param expectedValue another expected value, possibly {@code null}.
	 * @return a logical AND matcher, not {@code null}.
	 * @since 2.0.0
	 */
	default Matcher and(byte expectedValue) {
		return new And(this, new ByteMatcher("?", true, expectedValue));
	}

	/**
	 * Returns a logical AND matcher that matches if both this and another given expected value match.
	 *
	 * @param expectedValue another expected value, possibly {@code null}.
	 * @return a logical AND matcher, not {@code null}.
	 * @since 2.0.0
	 */
	default Matcher and(char expectedValue) {
		return new And(this, new CharMatcher("?", true, expectedValue));
	}

	/**
	 * Returns a logical AND matcher that matches if both this and another given expected value match.
	 *
	 * @param expectedValue another expected value, possibly {@code null}.
	 * @return a logical AND matcher, not {@code null}.
	 * @since 2.0.0
	 */
	default Matcher and(boolean expectedValue) {
		return new And(this, new BooleanMatcher("?", true, expectedValue));
	}

	/**
	 * Returns a logical OR matcher that matches if either this or another given matcher match.
	 *
	 * @param matcher another matcher, not {@code null}.
	 * @return a logical OR matcher, not {@code null}.
	 * @since 2.0.0
	 */
	default Matcher or(Matcher matcher) {
		return new Or(this, matcher);
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
		return new Or(this, new ObjectMatcher("?", true, expectedValue));
	}

	/**
	 * Returns a logical OR matcher that matches if either this or another given expected value match.
	 *
	 * @param expectedValue another expected value, possibly {@code null}.
	 * @return a logical OR matcher, not {@code null}.
	 * @since 2.0.0
	 */
	default Matcher or(double expectedValue) {
		return new Or(this, new DoubleMatcher("?", true, expectedValue));
	}

	/**
	 * Returns a logical OR matcher that matches if either this or another given expected value match.
	 *
	 * @param expectedValue another expected value, possibly {@code null}.
	 * @return a logical OR matcher, not {@code null}.
	 * @since 2.0.0
	 */
	default Matcher or(float expectedValue) {
		return new Or(this, new FloatMatcher("?", true, expectedValue));
	}

	/**
	 * Returns a logical OR matcher that matches if either this or another given expected value match.
	 *
	 * @param expectedValue another expected value, possibly {@code null}.
	 * @return a logical OR matcher, not {@code null}.
	 * @since 2.0.0
	 */
	default Matcher or(long expectedValue) {
		return new Or(this, new LongMatcher("?", true, expectedValue));
	}

	/**
	 * Returns a logical OR matcher that matches if either this or another given expected value match.
	 *
	 * @param expectedValue another expected value, possibly {@code null}.
	 * @return a logical OR matcher, not {@code null}.
	 * @since 2.0.0
	 */
	default Matcher or(int expectedValue) {
		return new Or(this, new IntMatcher(expectedValue));
	}

	/**
	 * Returns a logical OR matcher that matches if either this or another given expected value match.
	 *
	 * @param expectedValue another expected value, possibly {@code null}.
	 * @return a logical OR matcher, not {@code null}.
	 * @since 2.0.0
	 */
	default Matcher or(short expectedValue) {
		return new Or(this, new ShortMatcher(expectedValue));
	}

	/**
	 * Returns a logical OR matcher that matches if either this or another given expected value match.
	 *
	 * @param expectedValue another expected value, possibly {@code null}.
	 * @return a logical OR matcher, not {@code null}.
	 * @since 2.0.0
	 */
	default Matcher or(byte expectedValue) {
		return new Or(this, new ByteMatcher("?", true, expectedValue));
	}

	/**
	 * Returns a logical OR matcher that matches if either this or another given expected value match.
	 *
	 * @param expectedValue another expected value, possibly {@code null}.
	 * @return a logical OR matcher, not {@code null}.
	 * @since 2.0.0
	 */
	default Matcher or(char expectedValue) {
		return new Or(this, new CharMatcher("?", true, expectedValue));
	}

	/**
	 * Returns a logical OR matcher that matches if either this or another given expected value match.
	 *
	 * @param expectedValue another expected value, possibly {@code null}.
	 * @return a logical OR matcher, not {@code null}.
	 * @since 2.0.0
	 */
	default Matcher or(boolean expectedValue) {
		return new Or(this, new BooleanMatcher("?", true, expectedValue));
	}
}
