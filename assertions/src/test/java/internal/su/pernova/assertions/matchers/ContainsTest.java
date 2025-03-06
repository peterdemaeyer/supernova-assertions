package internal.su.pernova.assertions.matchers;

import static java.lang.String.format;
import static java.util.Collections.singleton;
import static su.pernova.assertions.AssertionTestUtils.assertThrowsAssertionErrorWithMessage;
import static su.pernova.assertions.Assertions.assertThat;
import static su.pernova.assertions.Matchers.allOf;
import static su.pernova.assertions.Matchers.anyOf;
import static su.pernova.assertions.Matchers.contains;
import static su.pernova.assertions.Matchers.instanceOf;

import java.util.List;

import org.junit.jupiter.api.Test;

class ContainsTest {

	@Test
	void containsCharSequence() {
		assertThat("ababa", contains("aba"));
		assertThrowsAssertionErrorWithMessage(
				() -> assertThat("ababa", contains("cde")),
				"expected that subject contains: \"cde\"",
				"but was: \"ababa\""
		);
		assertThrowsAssertionErrorWithMessage(
				() -> assertThat(this, contains("aba")),
				"expected that subject contains: \"aba\"",
				format("but was: \"%s\"", this)
		);
	}

	@Test
	void containsElement() {
		assertThat(singleton(null), contains(null));
		assertThrowsAssertionErrorWithMessage(
				() -> assertThat(List.of(1, 2), contains(3)),
				"expected that subject contains: 3",
				"but was: [1, 2]"
		);
		assertThrowsAssertionErrorWithMessage(
				() -> assertThat(this, contains(ContainsTest.class)),
				format("expected that subject contains: %s", ContainsTest.class.getName()),
				format("but was: \"%s\"", this)
		);
	}

	@Test
	void containsInstanceOf() {
		assertThat(singleton(this), contains(instanceOf(ContainsTest.class)));
	}

	@Test
	void containsAnyOf() {
		assertThat(List.of(1, 2), contains(anyOf(new int[] { 2, 3 })));
	}

	@Test
	void containsAllOf() {
		assertThat(List.of(1, 2, 3), contains(allOf(new int[] { 3, 1 })));
	}

	@Test
	void containsWithOr() {
		assertThat(List.of(1, 2, 3), contains(4).or(3));
	}
}
