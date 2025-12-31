package su.pernova.assertions;

import static su.pernova.assertions.AssertionTestUtils.assertThrowsWithMessage;

import org.junit.jupiter.api.Test;

import internal.su.pernova.assertions.matchers.Is;

class ImplicitMatcherFactoryTest implements MatcherFactoryContractTest {

	@Override
	public ImplicitMatcherFactory getInstance() {
		return new ImplicitMatcherFactory(Is.getMatcherFactory(null));
	}

	@Test
	void constructionThrowsWhenDestinationIsNull() {
		assertThrowsWithMessage(NullPointerException.class, () -> new ImplicitMatcherFactory(null), "destination is null");
	}
}
