package internal.su.pernova.assertions.matchers;

import static java.util.Objects.requireNonNull;

import java.util.regex.Pattern;

import internal.su.pernova.assertions.DefaultDescribable;
import su.pernova.assertions.Description;
import su.pernova.assertions.Matcher;

public class Regex extends DefaultDescribable implements Matcher {

	private final Pattern regex;

	public Regex(Pattern regex) {
		this.regex = requireNonNull(regex, "regex is null");
	}

	public Regex(String regex) {
		this(Pattern.compile(requireNonNull(regex, "regex is null")));
	}

	@Override
	public boolean match(Object actual) {
		return (actual instanceof CharSequence) && regex.matcher((CharSequence) actual).matches();
	}

	@Override
	public Description describe(Description description) {
		return Matcher.super.describe(description).appendPrompt().appendArgument(regex);
	}
}
