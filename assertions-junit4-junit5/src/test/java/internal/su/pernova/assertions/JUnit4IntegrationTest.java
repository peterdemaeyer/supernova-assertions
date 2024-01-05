package internal.su.pernova.assertions;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import static internal.su.pernova.assertions.TestFrameworkUtils.isJUnit4;
import static internal.su.pernova.assertions.TestFrameworkUtils.isOpenTest4J;

import java.util.concurrent.atomic.AtomicBoolean;

import org.junit.BeforeClass;
import org.junit.Test;

public class JUnit4IntegrationTest {

	@BeforeClass
	public static void assertThatJUnit5IsOnClassPath() throws Exception {
		// This IS a JUnit 4 test so obviously JUnit 4 is on the class path or this wouldn't even run.
		// JUnit 5 must ALSO be on the class path for this test.
		Class.forName("org.junit.jupiter.api.Assertions");
		Class.forName("org.opentest4j.AssertionFailedError");
	}

	@Test
	public void detectionOfJUnit4OnTestThread() {
		assertTrue(isJUnit4());
		assertFalse(isOpenTest4J());
	}

	@Test
	public void detectionOfJUnit4OnOtherThread() throws Exception {
		final AtomicBoolean jUnit4 = new AtomicBoolean(false);
		final AtomicBoolean openTest4J = new AtomicBoolean(true);
		final Thread thread = new Thread(() -> {
			jUnit4.set(isJUnit4());
			openTest4J.set(isOpenTest4J());
		});
		thread.start();
		thread.join();
		assertTrue(jUnit4.get());
		assertFalse(openTest4J.get());
	}
}
