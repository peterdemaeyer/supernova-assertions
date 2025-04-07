package internal.su.pernova.assertions.matchers;

import java.util.Objects;

import su.pernova.assertions.Context;
import su.pernova.assertions.MatcherFactory;

public class EqualTo extends ObjectMatcher {

	public static final MatcherFactory MATCHER_FACTORY = expected -> new EqualTo("", false, expected);

	public EqualTo(CharSequence description, boolean prompt, Object expected) {
		super(description, prompt, expected);
	}

	public EqualTo(Object expected) {
		this(null, true, expected);
	}

	@Override
	public boolean match(Object actual) {
		return Objects.equals(Context.get(this).expectedTransformation().apply(expected), actual);
	}
}
