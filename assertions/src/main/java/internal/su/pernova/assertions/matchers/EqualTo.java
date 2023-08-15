package internal.su.pernova.assertions.matchers;

import java.util.Objects;

public class EqualTo extends ExpectedMatcher {

	public EqualTo(CharSequence description, Object expected) {
		super(description, expected);
	}

	public EqualTo(Object expected) {
		this(null, expected);
	}

	@Override
	public boolean match(Object actual) {
		return Objects.equals(expected, actual);
	}
}
