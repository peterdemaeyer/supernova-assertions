package su.pernova.assertions;

import static su.pernova.assertions.AssertionTestUtils.assertThrowsWithMessage;

import org.junit.jupiter.api.Test;

class ImplicitMatcherFactoryTest {

	@Test
	void constructionThrowsWhenDestinationIsNull() {
		assertThrowsWithMessage(NullPointerException.class, () -> new ImplicitMatcherFactory(null), "destination is null");
	}
}
