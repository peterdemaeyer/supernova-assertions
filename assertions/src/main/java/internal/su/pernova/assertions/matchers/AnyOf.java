package internal.su.pernova.assertions.matchers;

import su.pernova.assertions.Matcher;

public class AnyOf extends MultiMatcher {

	public AnyOf(CharSequence startDelimiter, CharSequence separator, CharSequence endDelimiter, Matcher... destinations) {
		super(startDelimiter, separator, endDelimiter, destinations);
	}

	public AnyOf(Matcher... destinations) {
		super(destinations);
	}

	@Override
	protected AnyOf newInstance(Matcher[] contextualizedDestinations) {
		return new AnyOf(startDelimiter, separator, endDelimiter, contextualizedDestinations);
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
