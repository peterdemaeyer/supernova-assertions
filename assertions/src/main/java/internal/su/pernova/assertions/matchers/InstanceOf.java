package internal.su.pernova.assertions.matchers;

import static java.util.Objects.requireNonNull;

import su.pernova.assertions.Context;
import su.pernova.assertions.Description;
import su.pernova.assertions.MatcherFactory;

public class InstanceOf extends PromptDescriptiveMatcher {

	public static final MatcherFactory MATCHER_FACTORY = expected -> new InstanceOf("", false, (Class<?>) expected);

	private final Class<?> class_;

	public InstanceOf(CharSequence description, boolean prompt, Class<?> class_) {
		super(description, prompt);
		this.class_ = requireNonNull(class_, "class is null");
		Context.set(this).matcherFactory(MATCHER_FACTORY);
	}

	public InstanceOf(Class<?> class_) {
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
