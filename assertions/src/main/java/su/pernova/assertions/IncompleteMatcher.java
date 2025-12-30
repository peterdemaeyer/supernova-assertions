package su.pernova.assertions;

public class IncompleteMatcher implements Matcher {

	public static final MatcherFactory MATCHER_FACTORY = new MatcherFactory() {

		@Override
		public Matcher create(Object expectedValue) {
			return Context.newIncompleteMatcher(matcherFactory -> matcherFactory.create(expectedValue));
		}

		@Override
		public Matcher create(double expectedValue) {
			return Context.newIncompleteMatcher(matcherFactory -> matcherFactory.create(expectedValue));
		}

		@Override
		public Matcher create(float expectedValue) {
			return Context.newIncompleteMatcher(matcherFactory -> matcherFactory.create(expectedValue));
		}

		@Override
		public Matcher create(long expectedValue) {
			return Context.newIncompleteMatcher(matcherFactory -> matcherFactory.create(expectedValue));
		}

		@Override
		public Matcher create(int expectedValue) {
			return Context.newIncompleteMatcher(matcherFactory -> matcherFactory.create(expectedValue));
		}

		@Override
		public Matcher create(short expectedValue) {
			return Context.newIncompleteMatcher(matcherFactory -> matcherFactory.create(expectedValue));
		}

		@Override
		public Matcher create(byte expectedValue) {
			return Context.newIncompleteMatcher(matcherFactory -> matcherFactory.create(expectedValue));
		}

		@Override
		public Matcher create(char expectedValue) {
			return Context.newIncompleteMatcher(matcherFactory -> matcherFactory.create(expectedValue));
		}

		@Override
		public Matcher create(boolean expectedValue) {
			return Context.newIncompleteMatcher(matcherFactory -> matcherFactory.create(expectedValue));
		}

		@Override
		public Matcher create(Matcher matcher) {
			throw new UnsupportedOperationException();
		}
	};

	public IncompleteMatcher() {
	}

	@Override
	public boolean match(Object actualValue) {
		throw new IllegalStateException("incomplete matcher");
	}

	@Override
	public String toString() {
		return "...";
	}
}
