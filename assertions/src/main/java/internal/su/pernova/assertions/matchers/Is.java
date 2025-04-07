package internal.su.pernova.assertions.matchers;

import su.pernova.assertions.Context;
import su.pernova.assertions.Matcher;
import su.pernova.assertions.MatcherFactory;

public class Is extends DelegatingMatcher {

	public static final MatcherFactory MATCHER_FACTORY = new MatcherFactory() {

		@Override
		public Matcher create(Object expected) {
			return new IsObject("", false, expected);
		}

		@Override
		public Matcher create(double expected) {
			return new IsDouble("", false, expected);
		}

		@Override
		public Matcher create(float expected) {
			return new IsFloat("", false, expected);
		}

		@Override
		public Matcher create(long expected) {
			return new IsLong("", false, expected);
		}

		@Override
		public Matcher create(int expected) {
			return new IsInt("", false, expected);
		}

		@Override
		public Matcher create(short expected) {
			return new IsShort("", false, expected);
		}

		@Override
		public Matcher create(byte expected) {
			return new IsByte("", false, expected);
		}

		@Override
		public Matcher create(char expected) {
			return new IsChar("", false, expected);
		}

		@Override
		public Matcher create(boolean expected) {
			return new IsBoolean("", false, expected);
		}
	};

	public Is(CharSequence description, Matcher delegate) {
		super(description, delegate);
		Context.set(this).matcherFactory(Is.MATCHER_FACTORY);
	}

	public Is(Matcher delegate) {
		this(null, delegate);
	}
}
