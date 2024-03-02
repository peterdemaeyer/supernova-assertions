package internal.su.pernova.assertions.matchers;

import su.pernova.assertions.Matcher;

public class Not extends IsMatcher {

	public Not(Matcher delegate) {
		super("not", delegate);
	}

	@Override
	public boolean match(Object actual) {
		return !delegate.match(actual);
	}
}
