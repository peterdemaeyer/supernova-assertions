package internal.su.pernova.assertions.matchers;

import static java.util.Objects.requireNonNull;

import su.pernova.assertions.Description;
import su.pernova.assertions.Matcher;

public class InstanceOf implements Matcher {

	private final Class clazz;

	public InstanceOf(Class clazz) {
		this.clazz = requireNonNull(clazz, "class is null");
	}

	@Override
	public boolean match(Object actual) {
		return clazz.isInstance(actual);
	}

	@Override
	public Description describe(Description description) {
		return Matcher.super.describe(description).appendArgument(clazz);
	}
}
