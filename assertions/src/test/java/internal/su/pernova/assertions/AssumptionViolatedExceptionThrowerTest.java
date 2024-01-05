package internal.su.pernova.assertions;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

import su.pernova.design.SingletonContractTest;

class AssumptionViolatedExceptionThrowerTest implements SingletonContractTest {

	@Test
	void noFailureWhenNotJUnit4() {
		assertDoesNotThrow(() -> AssumptionViolatedExceptionThrower.getInstance().throwFailure());
		assertDoesNotThrow(() -> AssumptionViolatedExceptionThrower.getInstance().throwFailure("gotcha", null, null));
	}
}
