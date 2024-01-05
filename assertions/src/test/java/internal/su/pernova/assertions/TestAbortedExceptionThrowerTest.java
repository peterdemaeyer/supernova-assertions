package internal.su.pernova.assertions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.opentest4j.TestAbortedException;

import su.pernova.design.SingletonContractTest;

class TestAbortedExceptionThrowerTest implements SingletonContractTest {

	@Test
	void failure() {
		assertNull(
				assertThrows(TestAbortedException.class, () -> TestAbortedExceptionThrower.getInstance().throwFailure())
						.getMessage());
	}

	@Test
	void failureWithMessage() {
		assertEquals("message",
				assertThrows(TestAbortedException.class, () -> TestAbortedExceptionThrower.getInstance().throwFailure("message", "expected", "actual"))
						.getMessage());
	}
}
