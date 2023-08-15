package internal.su.pernova.assertions.matchers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static su.pernova.assertions.AssertionTestUtils.assertThrowsAssertionErrorWithMessage;
import static su.pernova.assertions.AssertionTestUtils.assertThrowsWithMessage;
import static su.pernova.assertions.Assertions.assertThat;
import static su.pernova.assertions.Matchers.regex;

import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;

import su.pernova.assertions.Matchers;

class RegexTest {

	@Test
	void creationThrowsWhenRegexPatternIsNull() {
		assertThrowsWithMessage(
				NullPointerException.class,
				() -> regex((Pattern) null),
				"regex is null"
		);
	}

	@Test
	void creationThrowsWhenRegexStringIsNull() {
		assertThrowsWithMessage(
				NullPointerException.class,
				() -> regex((String) null),
				"regex is null"
		);
	}

	@Test
	void regexMatchesString() {
		assertThat("abc").matches(regex("[a-c]*"));
	}

	@Test
	void regexDoesNotMatchString() {
		assertThrowsAssertionErrorWithMessage(
				() -> assertThat("def").matches(regex("[a-c]*")),
				String.format("expected that subject matches regex: \"[a-c]*\"%nbut was: \"def\"")
		);
	}

	@Test
	void regexDoesNotMatchNull() {
		assertThrowsAssertionErrorWithMessage(
				() -> assertThat(null).matches(regex(Pattern.compile(".*"))),
				String.format("expected that subject matches regex: \".*\"%nbut was: null")
		);
	}

	@Test
	void regexDoesNotMatchAnyObject() {
		assertThrowsAssertionErrorWithMessage(
				() -> assertThat(this).matches(regex(Pattern.compile(".*"))),
				String.format("expected that subject matches regex: \".*\"%nbut was: \"%s\"", this)
		);
	}

	@Test
	void stringValue() {
		assertEquals("regex: \".*\"", regex(".*").toString());
	}
}
