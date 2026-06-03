package internal.su.pernova.assertions.matchers;

import su.pernova.assertions.Matcher;

public class And extends BiMatcher {

	public And(Matcher destination1, Matcher destination2) {
		super(destination1, destination2);
	}

	@Override
	public boolean match(Object actualValue) {
		return destination1.match(actualValue) && destination2.match(actualValue);
	}

	@Override
	public Matcher newInstance(Matcher contextualizedDestination1, Matcher contextualizedDestination2) {
		return new And(contextualizedDestination1, contextualizedDestination2);
	}
}
