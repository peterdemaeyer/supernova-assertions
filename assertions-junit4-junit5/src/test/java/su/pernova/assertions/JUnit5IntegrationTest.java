package su.pernova.assertions;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static su.pernova.assertions.Matchers.is;

import java.util.concurrent.CompletableFuture;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;
import org.opentest4j.TestAbortedException;

class JUnit5IntegrationTest {

	@BeforeAll
	static void assertThatJUnit4IsOnClassPath() {
		assertDoesNotThrow(() -> Class.forName("junit.framework.Assert"));
	}

	@Test
	void immediateAssumptionFailure() {
		assertThrows(TestAbortedException.class, su.pernova.assertions.Assumptions::fail);
	}

	@Test
	void assumptionFailure() {
		final TestAbortedException jUnit5Failure = assertThrows(TestAbortedException.class,
				() -> org.junit.jupiter.api.Assumptions.assumeTrue(false));
		final TestAbortedException supernovaFailure = assertThrows(TestAbortedException.class,
				() -> su.pernova.assertions.Assumptions.assumeThat(false, is(true)));
		assertEquals(jUnit5Failure.getClass(), supernovaFailure.getClass());
	}

	@Test
	void immediateAssertionFailure() {
		final AssertionFailedError jUnit5Failure = assertThrows(AssertionFailedError.class,
				org.junit.jupiter.api.Assertions::fail);
		final AssertionFailedError supernovaFailure = assertThrows(AssertionFailedError.class,
				Assertions::fail);
		assertEquals(jUnit5Failure.getClass(), supernovaFailure.getClass());
	}

	@Test
	void assertionFailure() {
		final AssertionFailedError jUnit5Failure = assertThrows(AssertionFailedError.class,
				() -> org.junit.jupiter.api.Assertions.assertEquals(1, 2));
		final AssertionFailedError supernovaFailure = assertThrows(AssertionFailedError.class,
				() -> su.pernova.assertions.Assertions.assertThat(2, is(1)));
		assertEquals(jUnit5Failure.getClass(), supernovaFailure.getClass());
		assertEquals(jUnit5Failure.getActual().getEphemeralValue(), supernovaFailure.getActual().getEphemeralValue());
		assertEquals(jUnit5Failure.getExpected().getEphemeralValue(), supernovaFailure.getExpected().getEphemeralValue());
	}

	@Test
	void assertionFailureOnAnotherThread() throws Exception {
		final CompletableFuture<Throwable> future = new CompletableFuture<>();
		final Thread thread = new Thread(() -> {
			try {
				su.pernova.assertions.Assertions.fail();
				future.completeExceptionally(new AssertionFailedError());
			} catch (AssertionFailedError expected) {
				future.complete(expected);
			} catch (Throwable failure) {
				future.completeExceptionally(failure);
			}
		});
		thread.start();
		thread.join();
		assertEquals(AssertionFailedError.class, future.get().getClass());
	}
}
