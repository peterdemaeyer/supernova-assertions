package internal.su.pernova.assertions.matchers;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class InstanceOfTest {

	@Test
	void instanceOfDoesNotMatchNull() {
		final InstanceOf instanceOf = new InstanceOf(String.class);
		assertFalse(instanceOf.match(null));
	}

	@Test
	void instanceOfDoesNotMatchObjectOfAnotherClass() {
		final InstanceOf instanceOf = new InstanceOf(Number.class);
		assertFalse(instanceOf.match(this));
	}

	@Test
	void instanceOfMatchesObjectOfClass() {
		final InstanceOf instanceOf = new InstanceOf(String.class);
		assertTrue(instanceOf.match("abc"));
	}

	@Test
	void instanceOfMatchesObjectOfSubclass() {
		final InstanceOf instanceOf = new InstanceOf(CharSequence.class);
		assertTrue(instanceOf.match("abc"));
	}
}
