package internal.su.pernova.assertions.matchers;

import java.util.Objects;

public class EqualTo extends ObjectMatcher {

	public static final Context CONTEXT = expected -> new EqualTo("", false, expected);

	public EqualTo(CharSequence description, boolean prompt, Object expected) {
		super(description, prompt, expected);
	}

	public EqualTo(Object expected) {
		this(null, true, expected);
	}

	@Override
	public boolean match(Object actual) {
		return Objects.equals(expected, actual);
	}
}
