package internal.su.pernova.assertions.matchers;

import su.pernova.assertions.Matcher;

public class Is extends ForwardingMatcher {

	public Is(CharSequence name, Matcher delegatee) {
		super(name, delegatee);
	}

	public Is(Matcher delegatee) {
		this(null, delegatee);
	}
}
