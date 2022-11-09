package su.pernova.matchers.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import su.pernova.matchers.Description;
import su.pernova.matchers.Matcher;
import su.pernova.matchers.MatcherContractTest;
import su.pernova.matchers.StringDescription;

class SameAsMatcherTest implements MatcherContractTest {

	@Override
	public Matcher createObject() {
		return new SameAsMatcher("same as ", this);
	}

	@Test
	void constructionThrowsWhenRelationIsNull() {
		final NullPointerException expected = assertThrows(NullPointerException.class, () -> new SameAsMatcher<>(null, this));
		assertEquals("relation is null", expected.getMessage());
	}

	@SuppressWarnings("StringOperationCanBeSimplified")
	@Test
	void customRelationIsAppendedToDescription() {
		final String instance = new String("instance");
		final String anotherInstance = new String("another instance");
		final SameAsMatcher<Object> matcher = new SameAsMatcher<>("has custom relation to ", instance);
		assertTrue(matcher.matches(instance));
		assertFalse(matcher.matches(anotherInstance));
		Description description = new StringDescription();
		matcher.describeTo(description);
		assertEquals("has custom relation to \"instance\"", description.toString());
	}
}
