package internal.su.pernova.assertions.matchers;

public class Is extends ExpectedMatcher {

	public Is(Object expected) {
		super(null, expected);
	}

	@Override
	public boolean match(Object actual) {
		return actual == expected;
	}
}
