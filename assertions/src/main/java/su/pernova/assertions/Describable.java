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
	 * The default implementation derives a lower case description from the class name,
	 * ignoring some well-known class name prefixes such as {@code Default}, {@code Base}
	 * and some well-known suffixes such as {@code Impl}.
	 * For example a class name {@code EqualTo} will result in a description "equal to".
	 * A class name {@code SubjectImpl} will result in a description "subject".
	 *
	 * @param description a description, not {@code null}.
	 * @return the description which was given as parameter, not {@code null}.
	 * @since 1.0.0
	 */
	default Description describe(Description description) {
		final CharSequence name = name();
		return !name.isEmpty() ? description.appendText(name) : description;
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

	/**
	 * <p>
	 * This object's name.
	 * </p>
	 * <p>
	 * The default implementation derives a lower case name from this object's class,
	 * ignoring some well-known class prefixes such as {@code Default}, {@code Base}
	 * and some well-known suffixes such as {@code Impl}.
	 * </p>
	 * <h4>Examples</h4>
	 * a class name {@code EqualTo} will result in a description "equal to".
	 * A class name {@code SubjectImpl} will result in a description "subject".
	 *
	 * @return a name, not {@code null}.
	 * @since 2.0.0
	 */
	default CharSequence name() {
		return getDefaultName(this);
	}
}
