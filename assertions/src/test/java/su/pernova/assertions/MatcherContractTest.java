package su.pernova.assertions;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Test;

import su.pernova.design.ContractTest;

/// A test interface covering the {@link Matcher} contract.
/// Tests for all concrete {@code Matcher} implementations must implement this interface.
///
/// @since 2.0.0
public interface MatcherContractTest extends ContractTest {

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
}
