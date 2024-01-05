package su.pernova.assertions;

import static su.pernova.assertions.Assertions.assertThat;
import static su.pernova.assertions.Assumptions.assumeThat;

import junit.framework.TestCase;

public class JUnit3IntegrationTest extends TestCase {

	public void testAssertionFailure() {
		try {
			assertThat(false);
			fail();
		} catch (AssertionError expected){
			assertEquals(AssertionError.class, expected.getClass());
		}
	}

	public void testAssumptionFailure() {
		try {
			assumeThat(false);
			fail();
		} catch (AssertionError expected){
			// JUnit 3 does not have assumptions, so the assumption failures become assertion failures.
			assertEquals(AssertionError.class, expected.getClass());
		}
	}
}
