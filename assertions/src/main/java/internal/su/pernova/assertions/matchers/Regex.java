package internal.su.pernova.assertions.matchers;

import static java.util.Objects.requireNonNull;

import java.util.regex.Pattern;

public class Regex extends GenericMatcher<Pattern> {

	public Regex(Pattern regex) {
		super(null, requireNonNull(regex, "regex is null"));
	}

	public Regex(String regex) {
		this(Pattern.compile(requireNonNull(regex, "regex is null")));
	}

	@Override
	public boolean match(Object actual) {
		return (actual != null) && expected.matcher((CharSequence) actual).matches();
	}
}
