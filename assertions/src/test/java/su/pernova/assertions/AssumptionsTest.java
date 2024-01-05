package su.pernova.assertions;

import static java.util.Collections.emptyList;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static su.pernova.assertions.Assumptions.assumeThat;
import static su.pernova.assertions.Matchers.is;
import static su.pernova.junit.jupiter.extension.Fixtures.forEach;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.opentest4j.TestAbortedException;

import internal.su.pernova.assertions.AssumptionFailureThrowerFixture;
import su.pernova.design.UtilityContractTest;

class AssumptionsTest implements UtilityContractTest {

	@RegisterExtension
	final AssumptionFailureThrowerFixture fixture = forEach(new AssumptionFailureThrowerFixture());

	AssumptionsTest() throws Exception {
	}

	@Test
	void assumptionSuccess() {
		assertDoesNotThrow(() -> assumeThat(true, is(true)));
	}

	@Test
	void assumptionFailure() {
		assertThrows(TestAbortedException.class, () -> assumeThat(true, is(false)));
	}

	@Test
	void noFailureWhenAssumptionFailureThrowerDoesNotThrow() throws Exception {
		fixture.setThrowers(emptyList());
		assertDoesNotThrow(su.pernova.assertions.Assumptions::fail);
	}
}
