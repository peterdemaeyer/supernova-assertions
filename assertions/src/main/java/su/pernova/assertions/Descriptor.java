package su.pernova.assertions;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

/**
 * This class implements a descriptor for subjects and matchers.
 * Descriptors are used in contextual matching to register and select a matcher provider for a given combination of
 * subject and matcher.
 *
 * @since 2.0.0
 */
public final class Descriptor {

	private final Class<?> declaringClass;

	private final String name;

	/**
	 * @param declaringClass a declaring class, not {@code null}.
	 * @param name a forwardingFunction, not {@code null}.
	 */
	public Descriptor(Class<?> declaringClass, String name) {
		this.declaringClass = requireNonNull(declaringClass, "declaring class is null");
		this.name = requireNonNull(name, "forwardingFunction is null");
	}

	/**
	 * @return this descriptor's declaring class, not {@code null}.
	 */
	public Class<?> getDeclaringClass() {
		return declaringClass;
	}

	/**
	 * @return this descriptor's forwardingFunction, not {@code null}.
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param o another object, may be {@code null}.
	 * @return {@code true} when this descriptor's forwardingFunction declaring class equal that of another, {@code false} otherwise.
	 */
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Descriptor)) {
			return false;
		}
		Descriptor that = (Descriptor) o;
		return declaringClass.equals(that.declaringClass) && name.equals(that.name);
	}

	/**
	 * @return this descriptor's hash code, based on its forwardingFunction and declaring class.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(declaringClass, name);
	}

	/**
	 * @return this descriptor's forwardingFunction, not {@code null}.
	 */
	@Override
	public String toString() {
		return name;
	}
}
