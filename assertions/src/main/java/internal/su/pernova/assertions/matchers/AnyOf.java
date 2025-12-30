package internal.su.pernova.assertions.matchers;

import su.pernova.assertions.Matcher;

public class AnyOf extends MultiMatcher {

	public AnyOf(CharSequence startDelimiter, CharSequence separator, CharSequence endDelimiter, Matcher... destinations) {
		super(startDelimiter, separator, endDelimiter, destinations);
	}

	@Override
	public boolean match(Object actualValue) {
		for (Matcher destination : destinations) {
			if (destination.match(actualValue)) {
				return true;
			}
		}
		return false;
	}
}
