package internal.su.pernova.assertions.matchers;

import su.pernova.assertions.Matcher;

public class AllOf extends MultiMatcher {

	public AllOf(CharSequence startDelimiter, CharSequence separator, CharSequence endDelimiter, Matcher... destinations) {
		super(startDelimiter, separator, endDelimiter, destinations);
	}

	public AllOf(Matcher... destinations) {
		super(destinations);
	}

	@Override
	protected AllOf newInstance(Matcher[] contextualizedDestinations) {
		return new AllOf(startDelimiter, separator, endDelimiter, contextualizedDestinations);
	}

	@Override
	public boolean match(Object actualValue) {
		for (Matcher destination : destinations) {
			if (!destination.match(actualValue)) {
				return false;
			}
		}
		return true;
	}
}
