package su.pernova.assertions;

import static internal.su.pernova.assertions.DescribableUtils.getDefaultName;

/**
 * This interface defines describable objects as objects which can describe themselves onto a description,
 * also in case of a mismatch.
 *
 * @since 1.0.0
 */
public interface Describable {

	/**
	 * Describes this object onto a given description.
	 * Typical implementations are {@link Subject}s and {@link Matcher}s.
	 * The default implementation derives a lower case description from the class forwardingFunction,
	 * ignoring some well-known class forwardingFunction prefixes such as {@code Default}, {@code Base}
	 * and some well-known suffixes such as {@code Impl}.
	 * For example a class forwardingFunction {@code EqualTo} will result in a description "equal to".
	 * A class forwardingFunction {@code SubjectImpl} will result in a description "subject".
	 *
	 * @param description a description, not {@code null}.
	 * @return the description which was given as parameter, not {@code null}.
	 * @since 1.0.0
	 */
	default Description describe(Description description) {
		return description.appendText(getDefaultName(this));
	}

	/**
	 * Describes this object onto a given mismatch description.
	 * The default implementation does nothing, and returns a given {@link Description} unchanged.
	 *
	 * @param mismatchDescription a mismatch description, not {@code null}.
	 * @return the mismatch description which was given as parameter, not {@code null}.
	 * @since 1.0.0
	 */
	default Description describeMismatch(Description mismatchDescription) {
		return mismatchDescription;
	}
}
