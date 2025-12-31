package internal.su.pernova.assertions.matchers;

import su.pernova.assertions.Matcher;

public class NoneOf extends MultiMatcher {

	public NoneOf(CharSequence startDelimiter, CharSequence separator, CharSequence endDelimiter, Matcher... destinations) {
		super(startDelimiter, separator, endDelimiter, destinations);
	}

	@Override
	public boolean match(Object actualValue) {
		for (Matcher destination : destinations) {
			if (destination.match(actualValue)) {
				return false;
			}
		}
		return true;
	}
}
