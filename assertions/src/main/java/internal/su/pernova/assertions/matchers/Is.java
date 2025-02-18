package internal.su.pernova.assertions.matchers;

import su.pernova.assertions.Matcher;

public class Is extends PrefixingMatcher {

	public static final Context CONTEXT = new Context() {

		@Override
		public Matcher apply(Object expected) {
			return new IsObject("", false, expected);
		}

		@Override
		public Matcher apply(double expected) {
			return new IsDouble("", false, expected);
		}

		@Override
		public Matcher apply(float expected) {
			return new IsFloat("", false, expected);
		}

		@Override
		public Matcher apply(long expected) {
			return new IsLong("", false, expected);
		}

		@Override
		public Matcher apply(int expected) {
			return new IsInt("", false, expected);
		}

		@Override
		public Matcher apply(short expected) {
			return new IsShort("", false, expected);
		}

		@Override
		public Matcher apply(byte expected) {
			return new IsByte("", false, expected);
		}

		@Override
		public Matcher apply(char expected) {
			return Context.super.apply(expected);
		}

		@Override
		public Matcher apply(boolean expected) {
			return Context.super.apply(expected);
		}
	};

	public Is(CharSequence description, Matcher delegate) {
		super(description, delegate);
	}

	public Is(Matcher delegate) {
		this(null, delegate);
	}
}
