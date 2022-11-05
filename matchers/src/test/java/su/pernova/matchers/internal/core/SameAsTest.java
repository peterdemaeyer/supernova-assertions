package su.pernova.matchers.internal.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import su.pernova.matchers.Description;
import su.pernova.matchers.StringDescription;

class SameAsTest {

	@Test
	void constructionThrowsWhenRelationIsNull() {
		final NullPointerException expected = assertThrows(NullPointerException.class, () -> new SameAs<>(null, this));
		assertEquals("relation is null", expected.getMessage());
	}

	@SuppressWarnings("StringOperationCanBeSimplified")
	@Test
	void customRelationIsAppendedToDescription() {
		final String instance = new String("instance");
		final String anotherInstance = new String("another instance");
		final SameAs<Object> sameAs = new SameAs<>("has custom relation to ", instance);
		assertTrue(sameAs.matches(instance));
		assertFalse(sameAs.matches(anotherInstance));
		Description description = new StringDescription();
		sameAs.describeTo(description);
		assertEquals("has custom relation to \"instance\"", description.toString());
	}
}
