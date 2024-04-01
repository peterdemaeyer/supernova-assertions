package internal.su.pernova.assertions.matchers;

import su.pernova.assertions.Matcher;

public class AllOf extends CompositeMatcher {

	public AllOf(CharSequence prefix, CharSequence separator, CharSequence suffix, Matcher... delegates) {
		super(prefix, separator, suffix, delegates);
	}

	public AllOf(Matcher... delegates) {
		super(delegates);
	}

	@Override
	public boolean match(Object actual) {
		for (Matcher delegate : delegates) {
			if (!delegate.match(actual)) {
				return false;
			}
		}
		return true;
	}
}
