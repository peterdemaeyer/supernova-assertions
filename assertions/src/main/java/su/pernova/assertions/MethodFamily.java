package su.pernova.assertions;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

public class MethodFamily {

	private final Class<?> declaringClass;

	private final String name;

	public MethodFamily(Class<?> declaringClass, String name) {
		this.declaringClass = requireNonNull(declaringClass, "declaring class is null");
		this.name = requireNonNull(name, "family name is null");
	}

	public Class<?> getDeclaringClass() {
		return declaringClass;
	}

	public String getName() {
		return name;
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof MethodFamily)) return false;
		MethodFamily that = (MethodFamily) o;
		return Objects.equals(declaringClass, that.declaringClass) && Objects.equals(name, that.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(declaringClass, name);
	}

	@Override
	public String toString() {
		return declaringClass.getName() + "." + name;
	}
}
