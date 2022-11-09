package su.pernova.matchers;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

public interface MatcherContractTest {

	/**
	 * Concrete test subclasses must override this method to return a specific matcher instance.
	 * It is recommended for implementations to use covariant method overriding.
	 */
	Matcher createObject();

	@Test
	default void matcherIsNullSafe() {
		final Matcher matcher = createObject();
		assertDoesNotThrow(() -> matcher.matches(null));
		assertDoesNotThrow(() -> matcher.describeMismatch(null, Description.NULL));
	}

	@Test
	default void matcherIsUnknownTypeSafe() {
		final Matcher matcher = createObject();
		final UnknownType unknownType = new UnknownType();
		assertDoesNotThrow(() -> matcher.matches(unknownType));
		assertDoesNotThrow(() -> matcher.describeMismatch(unknownType, Description.NULL));
	}

	class UnknownType {
	}
}
