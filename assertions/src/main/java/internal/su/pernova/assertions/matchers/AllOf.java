package internal.su.pernova.assertions.matchers;

import su.pernova.assertions.Matcher;

public class AllOf extends MultiMatcher {

	public AllOf(CharSequence prefix, CharSequence separator, CharSequence suffix, Matcher... delegates) {
		super(prefix, separator, suffix, delegates);
	}

	@Override
	public boolean match(Object actualValue) {
		for (Matcher delegate : destinations) {
			if (!delegate.match(actualValue)) {
				return false;
			}
		}
		return true;
	}
}
