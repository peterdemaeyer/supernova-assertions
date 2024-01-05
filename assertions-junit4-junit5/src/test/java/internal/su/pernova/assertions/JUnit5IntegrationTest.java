package internal.su.pernova.assertions;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static internal.su.pernova.assertions.TestFrameworkUtils.isJUnit4;
import static internal.su.pernova.assertions.TestFrameworkUtils.isOpenTest4J;

import java.util.concurrent.atomic.AtomicBoolean;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class JUnit5IntegrationTest {

	@BeforeAll
	static void assertThatJUnit4IsOnClassPath() {
		// This IS a JUnit 5 test so obviously JUnit 5 is on the class path or this wouldn't even run.
		// JUnit 4 must ALSO be on the class path for this test.
		assertDoesNotThrow(() -> Class.forName("junit.framework.Assert"));
	}

	@Test
	void detectionOfJUnit5OnTestThread() {
		assertFalse(isJUnit4());
		assertTrue(isOpenTest4J());
	}

	@Test
	void detectionOfJUnit5OnOtherThread() throws Exception {
		final AtomicBoolean jUnit4 = new AtomicBoolean(true);
		final AtomicBoolean openTest4J = new AtomicBoolean(false);
		final Thread thread = new Thread(() -> {
			jUnit4.set(isJUnit4());
			openTest4J.set(isOpenTest4J());
		});
		thread.start();
		thread.join();
		assertFalse(jUnit4.get());
		assertTrue(openTest4J.get());
	}
}
