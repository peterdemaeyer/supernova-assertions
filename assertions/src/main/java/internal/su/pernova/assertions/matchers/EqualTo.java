package internal.su.pernova.assertions.matchers;

import static java.util.Objects.requireNonNull;

public class EqualTo extends GenericMatcher {

	public EqualTo(Object expected) {
		super(requireNonNull(expected, "expected is null"));
	}

	@Override
	public boolean match(Object actual) {
		return expected.equals(actual);
	}
}
