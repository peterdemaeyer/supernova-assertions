package internal.su.pernova.assertions;

import static internal.su.pernova.assertions.TestFrameworkUtils.isJUnit4;
import static internal.su.pernova.assertions.TestFrameworkUtils.isOpenTest4J;

import java.util.concurrent.atomic.AtomicBoolean;

import junit.framework.TestCase;

public class JUnit3IntegrationTest extends TestCase {

	public void testDetectionOfTestFrameworkOnTestThread() {
		assertFalse(isJUnit4());
		assertFalse(isOpenTest4J());
	}

	public void testDetectionOfTestFrameworkOnOtherThread() throws Exception {
		final AtomicBoolean jUnit4 = new AtomicBoolean(true);
		final AtomicBoolean openTest4J = new AtomicBoolean(true);
		final Thread thread = new Thread(() -> {
			jUnit4.set(isJUnit4());
			openTest4J.set(isOpenTest4J());
		});
		thread.start();
		thread.join();
		assertFalse(jUnit4.get());
		assertFalse(openTest4J.get());
	}
}
