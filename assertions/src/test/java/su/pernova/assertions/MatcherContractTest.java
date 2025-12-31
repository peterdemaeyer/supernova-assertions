package su.pernova.assertions;

import static java.util.concurrent.Executors.newFixedThreadPool;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import su.pernova.design.ContractTest;

/// A test interface covering the {@link Matcher} contract.
/// Tests for all concrete {@code Matcher} implementations must implement this interface.
///
/// @since 2.0.0
public interface MatcherContractTest extends ContractTest {

	/// Implementations must return a complete matcher, not `null`.
	/// To have meaningful tests, it is recommended that implementations return a matcher representing a common use case
	/// rather than a corner case.
	///
	/// @return a complete matcher, not `null`.
	Matcher getInstance();

	@Test
	default void matchingNullDoesNotThrow() {
		assertDoesNotThrow(() -> getInstance().match(null));
	}

	@Test
	default void matchingAnyObjectDoesNotThrow() {
		assertDoesNotThrow(() -> getInstance().match(new Object()));
	}

	@Test
	default void matchingObjectOfAnyTypeDoesNotThrow() {
		assertDoesNotThrow(() -> getInstance().match(this));
	}

	@Test
	default void descriptionChainsDescription() {
		final AppendableDescription description = new AppendableDescription(new StringBuilder());
		assertSame(description, getInstance().describe(description));
	}

	@Test
	default void mismatchDescriptionChainsDescription() {
		final AppendableDescription mismatchDescription = new AppendableDescription(new StringBuilder());
		assertSame(mismatchDescription, getInstance().describeMismatch(mismatchDescription));
	}

	@Test
	default void stringValueIsNotNull() {
		assertNotNull(getInstance().toString());
	}

	@Test
	default void orMatcherDoesNotThrow() {
		assertDoesNotThrow(() -> getInstance().or(mock(Matcher.class)));
	}

	@Test
	default void orDoubleDoesNotThrow() {
		assertDoesNotThrow(() -> getInstance().or(0d));
	}

	@Test
	default void orFloatDoesNotThrow() {
		assertDoesNotThrow(() -> getInstance().or(0f));
	}

	@Test
	default void orLongDoesNotThrow() {
		assertDoesNotThrow(() -> getInstance().or(0L));
	}

	@Test
	default void orIntDoesNotThrow() {
		assertDoesNotThrow(() -> getInstance().or(0));
	}

	@Test
	default void orShortDoesNotThrow() {
		assertDoesNotThrow(() -> getInstance().or((short) 0));
	}

	@Test
	default void orByteDoesNotThrow() {
		assertDoesNotThrow(() -> getInstance().or((byte) 0));
	}

	@Test
	default void orCharDoesNotThrow() {
		assertDoesNotThrow(() -> getInstance().or('0'));
	}

	@Test
	default void orBooleanDoesNotThrow() {
		assertDoesNotThrow(() -> getInstance().or(false));
	}

	@Test
	default void andMatcherDoesNotThrow() {
		assertDoesNotThrow(() -> getInstance().and(mock(Matcher.class)));
	}

	@Test
	default void andFloatDoesNotThrow() {
		assertDoesNotThrow(() -> getInstance().and(0f));
	}

	@Test
	default void andLongDoesNotThrow() {
		assertDoesNotThrow(() -> getInstance().and(0L));
	}

	@Test
	default void andIntDoesNotThrow() {
		assertDoesNotThrow(() -> getInstance().and(0));
	}

	@Test
	default void andShortDoesNotThrow() {
		assertDoesNotThrow(() -> getInstance().and((short) 0));
	}

	@Test
	default void andByteDoesNotThrow() {
		assertDoesNotThrow(() -> getInstance().and((byte) 0));
	}

	@Test
	default void andCharDoesNotThrow() {
		assertDoesNotThrow(() -> getInstance().and('0'));
	}

	@Test
	default void andBooleanDoesNotThrow() {
		assertDoesNotThrow(() -> getInstance().and(false));
	}

	@Test
	default void matchingIsConsistent() {
		final Matcher matcher = getInstance();
		assertEquals(matcher.match(null), matcher.match(null));
		assertEquals(matcher.match(this), matcher.match(this));
		final Object value = new Object();
		assertEquals(matcher.match(value), matcher.match(value));
		assertEquals(matcher.match(1.32354e-56d), matcher.match(1.32354e-56d));
		assertEquals(matcher.match(Double.NaN), matcher.match(Double.NaN));
		assertEquals(matcher.match(Double.POSITIVE_INFINITY), matcher.match(Double.POSITIVE_INFINITY));
		assertEquals(matcher.match(Double.NEGATIVE_INFINITY), matcher.match(Double.NEGATIVE_INFINITY));
		assertEquals(matcher.match(5.635165e5f), matcher.match(5.635165e5f));
		assertEquals(matcher.match(Float.NaN), matcher.match(Float.NaN));
		assertEquals(matcher.match(Float.POSITIVE_INFINITY), matcher.match(Float.POSITIVE_INFINITY));
		assertEquals(matcher.match(Float.NEGATIVE_INFINITY), matcher.match(Float.NEGATIVE_INFINITY));
		assertEquals(matcher.match(-5635385384354353435L), matcher.match(-5635385384354353435L));
		assertEquals(matcher.match(6843651), matcher.match(6843651));
		final short shortValue = -654;
		assertEquals(matcher.match(shortValue), matcher.match(shortValue));
		final byte byteValue = -65;
		assertEquals(matcher.match(byteValue), matcher.match(byteValue));
		assertEquals(matcher.match('⌛'), matcher.match('⌛'));
		assertEquals(matcher.match(false), matcher.match(false));
		assertEquals(matcher.match(true), matcher.match(true));
	}

	@Test
	@Timeout(30L)
	default void matchingIsThreadSafe() throws Exception {
		final int concurrency = 20;
		final int iterations = 100;
		final CyclicBarrier barrier = new CyclicBarrier(concurrency + 1);
		final List<Future<?>> futures = new ArrayList<>(concurrency);
		final Matcher matcher = getInstance();
		final Object value = new Object();
		final AtomicBoolean result = new AtomicBoolean(matcher.match(value));
		try (ExecutorService executor = newFixedThreadPool(concurrency)) {
			for (int thread = 0; thread++ != concurrency; ) {
				futures.add(executor.submit((Callable<Void>) () -> {
					barrier.await();
					for (int iteration = 0; iteration++ != iterations; ) {
						assertEquals(result.get(), matcher.match(value));
					}
					return null;
				}));
			}
			barrier.await();
			for (Future<?> future : futures) {
				future.get();
			}
		}
	}
}
