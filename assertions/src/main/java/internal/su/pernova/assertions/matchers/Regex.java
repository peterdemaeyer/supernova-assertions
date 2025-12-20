package internal.su.pernova.assertions.matchers;

import static java.util.Objects.requireNonNull;

import java.util.regex.Pattern;

import su.pernova.assertions.Context;
import su.pernova.assertions.Description;
import su.pernova.assertions.Descriptor;
import su.pernova.assertions.Matcher;

public class Regex implements Matcher {

	private final Pattern regex;

	public Regex(Pattern regex) {
		this.regex = requireNonNull(regex, "regex is null");
	}

	public Regex(String regex) {
		this(Pattern.compile(requireNonNull(regex, "regex is null")));
	}

	@Override
	public boolean match(Object actualValue) {
		return (actualValue instanceof CharSequence) && regex.matcher((CharSequence) actualValue).matches();
	}

	@Override
	public Description describe(Description description) {
		return Matcher.super.describe(description).appendPrompt().appendArgument(regex);
	}

	public Regex contextualize(Descriptor descriptor) {
		return Context.set(this).setDescriptor(descriptor).get();
	}
}
