package su.pernova.matchers.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

import su.pernova.matchers.Description;
import su.pernova.matchers.Matcher;
import su.pernova.matchers.MatcherContractTest;
import su.pernova.matchers.StringDescription;

class IdentityMatcherTest implements MatcherContractTest {

	@Override
	public IdentityMatcher createObject() {
		return new IdentityMatcher("identical to ", mock(Matcher.class));
	}

	@Test
	void constructionThrowsWhenRelationIsNull() {
		final NullPointerException expected = assertThrows(NullPointerException.class, () -> new IdentityMatcher<>(null, mock(Matcher.class)));
		assertEquals("relation is null", expected.getMessage());
	}

	@Test
	void constructionThrowsWhenDelegateIsNull() {
		final NullPointerException expected = assertThrows(NullPointerException.class, () -> new IdentityMatcher<>("identical to ", null));
		assertEquals("delegate is null", expected.getMessage());
	}

	@Test
	void matchesWhenDelegateMatches() {
		final Matcher delegate = mock(Matcher.class);
		final IdentityMatcher matcher = new IdentityMatcher("is ", delegate);
		final Object match = new Object();
		final Object mismatch = new Object();
		when(delegate.matches(match)).thenReturn(true);
		when(delegate.matches(mismatch)).thenReturn(false);
		assertTrue(matcher.matches(match));
		verify(delegate).matches(match);
		assertFalse(matcher.matches(mismatch));
		verify(delegate).matches(mismatch);
	}

	@Test
	void description() {
		final Matcher delegate = mock(Matcher.class);
		doAnswer(invocation -> invocation.<Description>getArgument(0).appendText("equal to ").appendValue("match")).when(delegate).describeTo(any(Description.class));
		final IdentityMatcher matcher = new IdentityMatcher("is ", delegate);
		final StringDescription description = new StringDescription();
		matcher.describeTo(description);
		assertEquals("is equal to \"match\"", description.toString());
		final StringDescription mismatchDescription = new StringDescription();
		matcher.describeMismatch("mismatch", mismatchDescription);
		assertEquals("was \"mismatch\"", mismatchDescription.toString());
	}
}
