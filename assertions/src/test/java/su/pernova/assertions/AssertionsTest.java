package su.pernova.assertions;

import static java.lang.System.lineSeparator;
import static java.util.regex.Pattern.DOTALL;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static su.pernova.assertions.Assertions.assertThat;
import static su.pernova.assertions.Matchers.equalTo;
import static su.pernova.assertions.Matchers.instanceOf;
import static su.pernova.assertions.Matchers.regex;
import static su.pernova.assertions.Matchers.sameAs;
import static su.pernova.assertions.Subjects.condition;

import java.math.BigInteger;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;

class AssertionsTest {

	@Test
	void defaultAssertionSucceedsWhenIdentityOfNull() {
		assertThat((Object) null).is((Object) null);
		assertThat((Subject) null).is((Matcher) null);
	}

	@Test
	void defaultAssertionSucceedsWhenIdentityOfObject() {
		assertThat(this).is(this);
	}

	@Test
	void defaultAssertionFailsWhenEqualObjectsWithDifferentIdentity() {
		BigInteger instance = new BigInteger("1");
		BigInteger equalInstance = new BigInteger("1");
		AssertionError expected = assertThrows(AssertionError.class, () -> assertThat(equalInstance).is(instance));
		assertEquals("expected that subject is: 1" + lineSeparator() + "but was: 1", expected.getMessage());
	}

	@Test
	void defaultAssertionFailsWhenNonEqualObjects() {
		BigInteger instance = new BigInteger("1");
		BigInteger nonEqualInstance = new BigInteger("2");
		assertNotEquals(instance, nonEqualInstance);
		assertNotSame(instance, nonEqualInstance);
		AssertionError expected = assertThrows(AssertionError.class, () -> assertThat(nonEqualInstance).is(instance));
		assertEquals("expected that subject is: 1" + lineSeparator() + "but was: 2", expected.getMessage());
	}

	@Test
	void assertionOfConditionSucceeds() {
		assertThat(condition(true)).is(true);
		assertThat(condition(false)).is(false);
	}

	@Test
	void assertionOfConditionFails() {
		AssertionError expected = assertThrows(AssertionError.class, () -> assertThat(condition(false)).is(true));
		assertEquals("expected that condition is: true" + lineSeparator() + "but was: false", expected.getMessage());
	}

	@Test
	void assertionSucceedsWhenActualIsSameAsExpected() {
		assertThat(this).is(sameAs(this));
	}

	@Test
	void assertionSucceedsWhenActualIsEqualToExpected() {
		assertThat(this).is(equalTo(this));
	}

	@Test
	void assertionSucceedsWhenActualIsInstanceOfExpectedClass() {
		assertThat("abc").is(instanceOf(String.class));
	}

	@Test
	void assertionFailsWhenActualIsNotInstanceOfExpectedClass() {
		final AssertionError error = assertThrows(AssertionError.class, () -> assertThat("abc").is(instanceOf(Double.class)));
		assertEquals("expected that subject is instance of: " + Double.class.getName() + lineSeparator() + "but was: \"abc\"", error.getMessage());
	}

	@Test
	void assertionSucceedsWhenRegexMatches() {
		assertThat("abc").matches(regex(".*"));
		assertThat("abc\ndef").matches(regex(Pattern.compile(".*", DOTALL)));
	}

	@Test
	void assertFailsWhenRegexMismatches() {
		final AssertionError error = assertThrows(AssertionError.class, () -> assertThat("abc").matches(regex("[0-1]*")));
		assertEquals("expected that subject matches regex: \"[0-1]*\"" + lineSeparator() + "but was: \"abc\"", error.getMessage());
	}
}
