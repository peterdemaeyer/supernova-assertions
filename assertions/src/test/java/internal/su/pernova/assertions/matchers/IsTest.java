package internal.su.pernova.assertions.matchers;

import static su.pernova.assertions.Matchers.equalTo;
import static su.pernova.assertions.Matchers.is;

import su.pernova.assertions.MatcherContractTest;
import su.pernova.assertions.MatcherFactory;
import su.pernova.assertions.MatcherFactoryContractTest;

class IsTest implements MatcherContractTest {

	@Override
	public Is getInstance() {
		return (Is) is(equalTo(new Object()));
	}

	static class MatcherFactoryTest implements MatcherFactoryContractTest {

		@Override
		public MatcherFactory getInstance() {
			return Is.getMatcherFactory(null);
		}
	}
}
