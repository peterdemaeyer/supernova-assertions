package internal.su.pernova.assertions;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static internal.su.pernova.assertions.TestFrameworkUtils.isJUnit4;
import static internal.su.pernova.assertions.TestFrameworkUtils.isOpenTest4J;

import java.util.concurrent.atomic.AtomicBoolean;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class JUnit5IntegrationTest {

	@BeforeAll
	static void assertThatJUnit4IsNotOnClassPath() {
		assertThrows(ClassNotFoundException.class, () -> Class.forName("junit.framework.Assert"));
	}

	@Test
	void detectionOfTestFrameworkOnTestThread() {
		assertFalse(isJUnit4());
		assertTrue(isOpenTest4J());
	}

	@Test
	void detectionOfTestFrameworkOnOtherThread() throws Exception {
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
