package internal.su.pernova.assertions.matchers;

import su.pernova.assertions.Matcher;

public class Is extends DelegatingMatcher {

	public static final Context CONTEXT = new Context() {

		@Override
		public Matcher evaluate(Object expected) {
			return new IsObject("", false, expected);
		}

		@Override
		public Matcher evaluate(double expected) {
			return new IsDouble("", false, expected);
		}

		@Override
		public Matcher evaluate(float expected) {
			return new IsFloat("", false, expected);
		}

		@Override
		public Matcher evaluate(long expected) {
			return new IsLong("", false, expected);
		}

		@Override
		public Matcher evaluate(int expected) {
			return new IsInt("", false, expected);
		}

		@Override
		public Matcher evaluate(short expected) {
			return new IsShort("", false, expected);
		}

		@Override
		public Matcher evaluate(byte expected) {
			return new IsByte("", false, expected);
		}

		@Override
		public Matcher evaluate(char expected) {
			return Context.super.evaluate(expected);
		}

		@Override
		public Matcher evaluate(boolean expected) {
			return Context.super.evaluate(expected);
		}
	};

	public Is(CharSequence description, Matcher delegate) {
		super(description, delegate);
	}

	public Is(Matcher delegate) {
		this(null, delegate);
	}
}
