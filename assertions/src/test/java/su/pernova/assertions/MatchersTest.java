package su.pernova.assertions;

import static org.junit.jupiter.api.Assertions.*;

import static su.pernova.assertions.Matchers.equalTo;
import static su.pernova.assertions.Matchers.instanceOf;
import static su.pernova.assertions.Matchers.sameAs;

import org.junit.jupiter.api.Test;

class MatchersTest {

	@Test
	void noMatcherIsNull() {
		assertNotNull(sameAs(this));
		assertNotNull(equalTo(this));
		assertNotNull(instanceOf(getClass()));
	}
}
