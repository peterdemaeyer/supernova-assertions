package internal.su.pernova.assertions.matchers;

import su.pernova.assertions.Matcher;

public class AnyOf extends CompositeMatcher {

	public AnyOf(CharSequence prefix, CharSequence separator, CharSequence suffix, Matcher... delegates) {
		super(prefix, separator, suffix, delegates);
	}

	public AnyOf(Matcher... delegates) {
		super(delegates);
	}

	@Override
	public boolean match(Object actual) {
		for (Matcher delegate : delegates) {
			if (delegate.match(actual)) {
				return true;
			}
		}
		return false;
	}
}
