package su.pernova.matchers.number;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import su.pernova.matchers.Description;
import su.pernova.matchers.Matcher;
import su.pernova.matchers.MatcherContractTest;
import su.pernova.matchers.StringDescription;

class NotMatcherANumberMatcherTest implements MatcherContractTest {

	private final Description description = new StringDescription();

	@Override
	public NotANumberMatcher createObject() {
		return NotANumberMatcher.getInstance();
	}

	@Test
	void matchesNothingButNaN() {
		final Matcher matcher = createObject();
		assertTrue(matcher.matches(Double.NaN));
		assertTrue(matcher.matches(Float.NaN));
		assertFalse(matcher.matches(null));
		assertFalse(matcher.matches(this));
		assertFalse(matcher.matches(Double.POSITIVE_INFINITY));
		assertFalse(matcher.matches(1.25d));
	}

	@Test
	void description() {
		final Matcher matcher = createObject();
		matcher.describeTo(description);
		assertEquals("not a number", description.toString());
	}
}
