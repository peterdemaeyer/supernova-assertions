package su.pernova.assertions;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import static su.pernova.assertions.Matchers.is;

import java.util.concurrent.CompletableFuture;

import org.junit.Assert;
import org.junit.Test;

public class JUnit4IntegrationTest {

	@Test
	public void failure() {
		final AssertionError jUnit4Failure = assertThrows(AssertionError.class, () -> Assert.fail());
		final AssertionError supernovaFailure = assertThrows(AssertionError.class, () -> Assertions.fail());
		assertTrue(jUnit4Failure.getClass().isAssignableFrom(supernovaFailure.getClass()));
	}

	@Test
	public void assertionFailure() throws Exception {
		// JUnit 5 MUST ALSO be on the class path for this test.
		Class.forName("org.junit.jupiter.api.Assertions");
		Class.forName("org.opentest4j.AssertionFailedError");
		final AssertionError jUnit4Failure = assertThrows(AssertionError.class, () -> Assert.assertTrue(false));
		final AssertionError supernovaFailure = assertThrows(AssertionError.class, () -> Assertions.assertThat(false, is(true)));
		assertTrue(jUnit4Failure.getClass().isAssignableFrom(supernovaFailure.getClass()));
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
		assertTrue(AssertionError.class.isAssignableFrom(future.get().getClass()));
	}
}
