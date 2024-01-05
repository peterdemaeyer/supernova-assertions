package internal.su.pernova.assertions;

import static java.util.Collections.emptyList;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

import su.pernova.design.SingletonContractTest;

class AssertionFailureThrowerTest implements SingletonContractTest {

	@Test
	void noProviders() {
		CompositeFailureThrower thrower = new CompositeFailureThrower(emptyList());
		assertDoesNotThrow(() -> thrower.throwFailure());
		assertDoesNotThrow(() -> thrower.throwFailure("message", this, this));
	}
}
