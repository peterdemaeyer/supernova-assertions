package su.pernova.matchers;

import static su.pernova.matchers.MatcherAssertions.assertThat;
import static su.pernova.matchers.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public final class MatcherAssertionsTest {

	@Test
	public void
	includesDescriptionOfTestedValueInErrorMessage() {
		String expected = "expected";
		String actual = "actual";
		String endLine = System.lineSeparator();

		String expectedMessage = "identifier" + endLine + "Expected: \"expected\"" + endLine + "     but: was \"actual\"";

		try {
			assertThat(actual, equalTo(expected), "identifier");
		} catch (AssertionError e) {
			assertTrue(e.getMessage().startsWith(expectedMessage));
			return;
		}

		fail("should have failed");
	}

	@Test
	public void
	descriptionCanBeElided() {
		String expected = "expected";
		String actual = "actual";
		String endLine = System.lineSeparator();

		String expectedMessage = endLine + "Expected: \"expected\"" + endLine + "     but: was \"actual\"";

		try {
			assertThat(actual, equalTo(expected));
		} catch (AssertionError e) {
			assertTrue(e.getMessage().startsWith(expectedMessage));
			return;
		}

		fail("should have failed");
	}

	@Test
	public void
	canTestBooleanDirectly() {
		assertThat(true, "success reason message");

		try {
			assertThat(false, "failing reason message");
		} catch (AssertionError e) {
			assertEquals("failing reason message", e.getMessage());
			return;
		}

		fail("should have failed");
	}

	@Test
	public void
	includesMismatchDescription() {
		Matcher<String> matcherWithCustomMismatchDescription = new Matcher<String>() {
			@Override
			public boolean matches(Object item) {
				return false;
			}

			@Override
			public void describeTo(Description description) {
				description.appendText("Something cool");
			}

			@Override
			public void describeMismatch(Object actual, Description mismatchDescription) {
				mismatchDescription.appendText("Not cool");
			}
		};

		String endLine = System.lineSeparator();
		String expectedMessage = endLine + "Expected: Something cool" + endLine + "     but: Not cool";

		try {
			assertThat("Value", matcherWithCustomMismatchDescription);
			fail("should have failed");
		} catch (AssertionError e) {
			assertEquals(expectedMessage, e.getMessage());
		}
	}

	@Test
	public void
	canAssertSubtypes() {
		assertThat(1, equalTo((Number) 1));
	}
}
