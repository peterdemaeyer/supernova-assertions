package su.pernova.assertions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import static su.pernova.assertions.Matchers.is;

import java.util.concurrent.CompletableFuture;

import org.junit.Assert;
import org.junit.Assume;
import org.junit.AssumptionViolatedException;
import org.junit.BeforeClass;
import org.junit.Test;

public class JUnit4IntegrationTest {

	@BeforeClass
	public static void assertThatJUnit5IsNotOnClassPath() {
		assertThrows(ClassNotFoundException.class, () -> Class.forName("org.junit.jupiter.api.Assertions"));
		assertThrows(ClassNotFoundException.class, () -> Class.forName("org.opentest4j.AssertionFailedError"));
	}

	@Test
	public void immediateAssumptionFailure() {
		assertThrows(AssumptionViolatedException.class, Assumptions::fail);
	}

	@Test
	public void assumptionFailure() {
		final AssumptionViolatedException jUnit4Failure = assertThrows(AssumptionViolatedException.class, () -> Assume.assumeTrue(false));
		final AssumptionViolatedException supernovaFailure = assertThrows(AssumptionViolatedException.class, () -> Assumptions.assumeThat(false, is(true)));
		assertEquals(jUnit4Failure.getClass(), supernovaFailure.getClass());
	}

	@Test
	public void immediateAssertionFailure() {
		final AssertionError jUnit4Failure = assertThrows(AssertionError.class, Assert::fail);
		final AssertionError supernovaFailure = assertThrows(AssertionError.class, Assertions::fail);
		assertEquals(jUnit4Failure.getClass(), supernovaFailure.getClass());
	}

	@Test
	public void assertionFailure() {
		final AssertionError jUnit4Failure = assertThrows(AssertionError.class, () -> Assert.assertTrue(false));
		final AssertionError supernovaFailure = assertThrows(AssertionError.class, () -> Assertions.assertThat(false, is(true)));
		assertEquals(jUnit4Failure.getClass(), supernovaFailure.getClass());
	}

	@Test
	public void assertionFailureOnAnotherThread() throws Exception {
		final CompletableFuture<Throwable> future = new CompletableFuture<>();
		final Thread thread = new Thread(() -> {
			try {
				Assertions.fail();
				future.completeExceptionally(new AssertionError());
			} catch (AssertionError expected) {
				future.complete(expected);
			} catch (Throwable failure) {
				future.completeExceptionally(failure);
			}
		});
		thread.start();
		thread.join();
		assertEquals(AssertionError.class, future.get().getClass());
	}
}
