package su.pernova.matchers;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static su.pernova.matchers.MatcherAssertions.NULL;
import static su.pernova.matchers.MatcherAssertions.assertThat;
import static su.pernova.matchers.Matchers.equalTo;
import static su.pernova.matchers.Matchers.sameAs;
import static su.pernova.matchers.core.CoreMatchers.instanceOf;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public final class MatcherAssertionsTest {

	private final Object object = new String("object");

	private final Object equalObject = new String("object");

	private final Object differentObject = new String("different object");

	@Test
	void assertThatObjectIsNull() {
		assertNotNull(assertThat((Object) null).is((Object) null));
		assertThrows(AssertionError.class, () -> assertThat(this).is((Object) null));
		assertNotNull(assertThat((Object) null).is(NULL));
		assertThrows(AssertionError.class, () -> assertThat(this).is(NULL));
	}

	@Test
	void assertThatObjectIs() {
		assertNotNull(assertThat(object).is(object));
		assertThrows(AssertionError.class, () -> assertThat(object).is(differentObject));
	}

	@Test
	void assertThatObjectIsSameAs() {
		assertThat(object).is(sameAs(object));
		assertThrows(AssertionError.class, () -> assertThat((Object) null).is(sameAs(object)));
		assertThrows(AssertionError.class, () -> assertThat(differentObject).is(sameAs(object)));
	}

	@Test
	void assertThatObjectIsEqualTo() {
		assertNotNull(assertThat(object).is(equalTo(object)));
		assertThrows(AssertionError.class, () -> assertThat((Object) null).is(equalTo(object)));
		assertNotNull(assertThat(object).is(equalTo(equalObject)));
		assertThrows(AssertionError.class, () -> assertThat(object).is(equalTo(differentObject)));
	}

	@Test
	void assertThatObjectIsInstanceOf() {
		assertThrows(AssertionError.class, () -> assertThat((Object) null).is(instanceOf(Double.class)));
		assertThat(object).is(instanceOf(Object.class));
		assertThat(5).is(instanceOf(Number.class));
		assertThrows(AssertionError.class, () -> assertThat(6L).is(instanceOf(Double.class)));
	}

	@Test
	void basicAssertion() {
		MatcherAssertions.assertThat();
	}

	@Test
	void assertjTest() {
		Assertions.assertThat("abc".startsWith("a"));
	}
//
//	@Test
//	public void
//	includesDescriptionOfTestedValueInErrorMessage() {
//		String expected = "expected";
//		String actual = "actual";
//		String endLine = System.lineSeparator();
//
//		String expectedMessage = "identifier" + endLine + "Expected: \"expected\"" + endLine + "     but: was \"actual\"";
//
//		try {
//			assertThat(actual, equalTo(expected), "identifier");
//		} catch (AssertionError e) {
//			assertTrue(e.getMessage().startsWith(expectedMessage));
//			return;
//		}
//
//		fail("should have failed");
//	}
//
//	@Test
//	public void
//	descriptionCanBeElided() {
//		String expected = "expected";
//		String actual = "actual";
//		String endLine = System.lineSeparator();
//
//		String expectedMessage = endLine + "Expected: \"expected\"" + endLine + "     but: was \"actual\"";
//
//		try {
//			assertThat(actual, equalTo(expected));
//		} catch (AssertionError e) {
//			assertTrue(e.getMessage().startsWith(expectedMessage));
//			return;
//		}
//
//		fail("should have failed");
//	}
//
//	@Test
//	public void
//	canTestBooleanDirectly() {
//		assertThat(true, "success reason message");
//
//		try {
//			assertThat(false, "failing reason message");
//		} catch (AssertionError e) {
//			assertEquals("failing reason message", e.getMessage());
//			return;
//		}
//
//		fail("should have failed");
//	}
//
//	@Test
//	public void
//	includesMismatchDescription() {
//		Matcher<String> matcherWithCustomMismatchDescription = new Matcher<String>() {
//			@Override
//			public boolean matches(Object item) {
//				return false;
//			}
//
//			@Override
//			public void describeTo(Description description) {
//				description.appendText("Something cool");
//			}
//
//			@Override
//			public void describeMismatch(Object actual, Description mismatchDescription) {
//				mismatchDescription.appendText("Not cool");
//			}
//		};
//
//		String endLine = System.lineSeparator();
//		String expectedMessage = endLine + "Expected: Something cool" + endLine + "     but: Not cool";
//
//		try {
//			assertThat("Value", matcherWithCustomMismatchDescription);
//			fail("should have failed");
//		} catch (AssertionError e) {
//			assertEquals(expectedMessage, e.getMessage());
//		}
//	}
//
//	@Test
//	public void
//	canAssertSubtypes() {
//		assertThat(1, equalTo((Number) 1));
//	}
}
