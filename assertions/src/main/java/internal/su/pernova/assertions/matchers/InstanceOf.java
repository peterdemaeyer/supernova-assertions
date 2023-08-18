package internal.su.pernova.assertions.matchers;

import static java.util.Objects.requireNonNull;

import internal.su.pernova.assertions.DefaultDescribable;
import su.pernova.assertions.Description;
import su.pernova.assertions.Matcher;

public class InstanceOf extends DefaultDescribable implements Matcher {

	private final Class class_;

	public InstanceOf(Class class_) {
		this.class_ = requireNonNull(class_, "class is null");
	}

	@Override
	public boolean match(Object actual) {
		return class_.isInstance(actual);
	}

	@Override
	public Description describe(Description description) {
		return Matcher.super.describe(description).appendArgument(class_);
	}
}
