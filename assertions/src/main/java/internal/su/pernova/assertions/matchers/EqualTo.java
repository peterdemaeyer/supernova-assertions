package internal.su.pernova.assertions.matchers;

import static java.util.Objects.requireNonNull;

public class EqualTo extends GenericMatcher<Object> {

	public EqualTo(CharSequence customText, Object expected) {
		super(customText, requireNonNull(expected, "expected is null"));
	}

	public EqualTo(Object expected) {
		this(null, expected);
	}

	@Override
	public boolean match(Object actual) {
		return expected.equals(actual);
	}
}
