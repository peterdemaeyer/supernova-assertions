package internal.su.pernova.assertions.matchers;

import java.util.Objects;

import su.pernova.assertions.Matcher;
import su.pernova.assertions.MatcherFactory;

public class EqualTo extends ObjectMatcher {

	public static final MatcherFactory MATCHER_FACTORY = new MatcherFactory() {

		@Override
		public Matcher create(Object expectedValue) {
			return new EqualTo(expectedValue);
		}

		@Override
		public Matcher create(Matcher matcher) {
			return new ForwardingMatcher("equal to", matcher);
		}
	};

	public EqualTo(CharSequence name, boolean prompt, Object expectedValue) {
		super(name, prompt, expectedValue);
	}

	public EqualTo(Object expectedValue) {
		this(null, true, expectedValue);
	}

	@Override
	public boolean match(Object actualValue) {
		return Objects.equals(expectedValue, actualValue);
	}
}
