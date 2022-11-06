package su.pernova.matchers.number;

import su.pernova.matchers.Matcher;

/**
 * @author Peter De Maeyer
 * @since 1.0.0
 */
public interface NumberMatchers {

	/**
	 * Returns a matcher implementing the "not a number" relation.
	 * Such a matcher is only applicable to instances of {@link Double} and {@link Float}.
	 * It will mismatch with any other object, including {@code null}.
	 * <p>
	 * Beware that because of the special nature of {@link Double#NaN}, the following code fragments are <em>not</em>
	 * equivalent:
	 *
	 * <pre>{@code
	 * assertThat(doubleValue, is(Double.NaN));
	 * assertThat(doubleValue, is(notANumber());
	 * }</pre>
	 * <p>
	 * The former expression uses the identity matcher based on the "==" operator, which returns {@code false} for
	 * {@code Double.NaN == Double.NaN}.
	 * Correspondingly, the matcher will <em>mismatch</em> on {@code doubleValue = NaN}.
	 * This may be counterintuitive, but the behavior is not specific to Java and is defined as part of an
	 * <a href="https://en.wikipedia.org/wiki/IEEE_754">IEEE specification</a>.
	 * <p>
	 * The latter expression is based on the {@link Double#isNaN} operator, which behaves as intuitively expected.
	 * <p>
	 * {@link Float#NaN} is equivalent.
	 *
	 * @return a matcher implementing the "not a number" relation, not {@code null}.
	 */
	static <T> Matcher<T> notANumber() {
		return NotANumberMatcher.getInstance();
	}
}
