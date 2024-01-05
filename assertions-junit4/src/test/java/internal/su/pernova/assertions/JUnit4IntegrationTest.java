package internal.su.pernova.assertions;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import static internal.su.pernova.assertions.TestFrameworkUtils.isJUnit4;
import static internal.su.pernova.assertions.TestFrameworkUtils.isOpenTest4J;

import java.util.concurrent.atomic.AtomicBoolean;

import org.junit.BeforeClass;
import org.junit.Test;

public class JUnit4IntegrationTest {

	@BeforeClass
	public static void assertThatJUnit5IsNotOnClassPath() {
		assertThrows(ClassNotFoundException.class, () -> Class.forName("org.junit.jupiter.api.Assertions"));
		assertThrows(ClassNotFoundException.class, () -> Class.forName("org.opentest4j.AssertionFailedError"));
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
