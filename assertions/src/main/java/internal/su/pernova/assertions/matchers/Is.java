package internal.su.pernova.assertions.matchers;

import su.pernova.assertions.Matcher;

public class Is extends PrefixingMatcher {

	public Is(CharSequence description, Matcher delegate) {
		super(description, delegate);
	}

	public Is(Matcher delegate) {
		this(null, delegate);
	}
}
