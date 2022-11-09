package su.pernova.matchers.core;

import static su.pernova.matchers.AbstractMatcherTest.assertDescription;
import static su.pernova.matchers.AbstractMatcherTest.assertDoesNotMatch;
import static su.pernova.matchers.AbstractMatcherTest.assertMatches;
import static su.pernova.matchers.AbstractMatcherTest.assertMismatchDescription;
import static su.pernova.matchers.AbstractMatcherTest.assertNullSafe;
import static su.pernova.matchers.AbstractMatcherTest.assertUnknownTypeSafe;
import static su.pernova.matchers.MatcherAssertions.assertThat;
import static su.pernova.matchers.core.AllOf.allOf;
import static su.pernova.matchers.core.CoreMatchers.is;
import static su.pernova.matchers.core.EqualsMatcher.equalTo;
import static su.pernova.matchers.core.StringContains.containsString;
import static su.pernova.matchers.core.StringEndsWith.endsWith;
import static su.pernova.matchers.core.StringStartsWith.startsWith;

import org.junit.jupiter.api.Test;

import su.pernova.matchers.Matcher;

public final class AllOfTest {

	@Test
	public void
	copesWithNullsAndUnknownTypes() {
		Matcher<String> matcher = allOf(equalTo("irrelevant"), startsWith("irr"));

		assertNullSafe(matcher);
		assertUnknownTypeSafe(matcher);
	}

	@Test
	public void
	evaluatesToTheTheLogicalConjunctionOfTwoOtherMatchers() {
		Matcher<String> matcher = allOf(startsWith("goo"), endsWith("ood"));

		assertMatches("didn't pass both sub-matchers", matcher, "good");
		assertDoesNotMatch("didn't fail first sub-matcher", matcher, "mood");
		assertDoesNotMatch("didn't fail second sub-matcher", matcher, "goon");
		assertDoesNotMatch("didn't fail both sub-matchers", matcher, "fred");
	}

	@Test
	public void
	evaluatesToTheTheLogicalConjunctionOfManyOtherMatchers() {
		Matcher<String> matcher = allOf(startsWith("g"), startsWith("go"), endsWith("d"), startsWith("go"), startsWith("goo"));

		assertMatches("didn't pass all sub-matchers", matcher, "good");
		assertDoesNotMatch("didn't fail middle sub-matcher", matcher, "goon");
	}

	@Test
	public void
	supportsMixedTypes() {
		final Matcher<SampleSubClass> matcher = allOf(
				equalTo(new SampleBaseClass("bad")),
				is(CoreMatchers.not(null)),
				equalTo(new SampleBaseClass("good")),
				equalTo(new SampleSubClass("ugly")));

		assertDoesNotMatch("didn't fail last sub-matcher", matcher, new SampleSubClass("good"));
	}

	@Test
	public void
	hasAReadableDescription() {
		assertDescription("(\"good\" and \"bad\" and \"ugly\")",
				allOf(equalTo("good"), equalTo("bad"), equalTo("ugly")));
	}

	@Test
	public void
	hasAMismatchDescriptionDescribingTheFirstFailingMatch() {
		assertMismatchDescription("\"good\" was \"bad\"", allOf(equalTo("bad"), equalTo("good")), "bad");
	}

	@Test
	public void
	varargs() {
		assertThat("the text!", new AllOf<>(startsWith("the"), containsString("text"), endsWith("!")));
	}
}
