package internal.su.pernova.assertions.matchers;

import static java.util.Objects.requireNonNull;

import internal.su.pernova.assertions.DefaultDescribable;
import su.pernova.assertions.Description;
import su.pernova.assertions.Matcher;

public class InstanceOf extends PromptDescriptiveMatcher {

	private final Class class_;

	public InstanceOf(CharSequence description, boolean prompt, Class class_) {
		super(description, prompt);
		this.class_ = requireNonNull(class_, "class is null");
	}

	public InstanceOf(Class class_) {
		this(null, true, class_);
	}

	@Override
	public boolean match(Object actual) {
		return class_.isInstance(actual);
	}

	@Override
	public Description describe(Description description) {
		return super.describe(description).appendArgument(class_);
	}
}
