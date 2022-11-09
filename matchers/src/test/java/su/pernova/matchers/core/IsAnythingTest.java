package su.pernova.matchers.core;

import static su.pernova.matchers.AbstractMatcherTest.assertDescription;
import static su.pernova.matchers.AbstractMatcherTest.assertMatches;
import static su.pernova.matchers.MatcherAssertions.assertThat;
import static su.pernova.matchers.core.CoreMatchers.is;
import static su.pernova.matchers.core.IsAnything.anything;

import org.junit.jupiter.api.Test;

import su.pernova.matchers.Matcher;

public final class IsAnythingTest {

	private final Matcher<Object> matcher = anything();

	private static class CustomThing {
	}

	@Test
	public void
	alwaysEvaluatesToTrue() {
		assertMatches("didn't match null", matcher, null);
		assertMatches("didn't match Object", matcher, new Object());
		assertMatches("didn't match custom object", matcher, new CustomThing());
		assertMatches("didn't match String", matcher, "hi");
	}

	@Test
	public void compilesWithoutTypeWarnings() {
		assertThat(new CustomThing(), is(anything()));
	}

	@Test
	public void
	hasUsefulDefaultDescription() {
		assertDescription("ANYTHING", matcher);
	}

	@Test
	public void
	canOverrideDescription() {
		String description = "description";
		assertDescription(description, anything(description));
	}

}
