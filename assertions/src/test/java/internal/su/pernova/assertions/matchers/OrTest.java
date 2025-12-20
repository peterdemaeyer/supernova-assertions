package internal.su.pernova.assertions.matchers;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static su.pernova.assertions.AssertionTestUtils.assertThrowsAssertionErrorWithMessage;
import static su.pernova.assertions.Assertions.assertThat;
import static su.pernova.assertions.Matchers.equalTo;
import static su.pernova.assertions.Matchers.instanceOf;
import static su.pernova.assertions.Matchers.is;

import java.util.Date;

import org.junit.jupiter.api.Test;

import su.pernova.assertions.AppendableDescription;
import su.pernova.assertions.Description;
import su.pernova.assertions.Matcher;

class OrTest {

	private static Description newDescription() {
		return new AppendableDescription(new StringBuilder());
	}

	@Test
	void descriptionOfIsIntOrInt() {
		assertEquals("is: 1 or: 2",
				is(1).or(2).describe(newDescription()).toString());
	}

	@Test
	void abstractSyntaxTreeAsStringValue() {
		assertEquals("or{is(1)}{i̶s̶(2)}",
				is(1).or(2).toString());
		assertEquals("is{or{equalTo(1)}{e̶q̶u̶a̶l̶T̶o̶(2)}}",
				is(equalTo(1).or(2)).toString());
		assertEquals("or{is{equalTo(a)}}{i̶s̶{instanceOf(java.util.Date)}}",
				is(equalTo('a')).or(instanceOf(Date.class)).toString());
	}

	@Test
	void descriptionOfIsFloatOrFloat() {
		assertEquals("is: 1.0 or: 2.0",
				is(1f).or(2f)
						.describe(newDescription()).toString());
	}

	@Test
	void descriptionOfIsInstanceOfClassOrIsEqualToObject() {
		final Matcher matcher = is(instanceOf(Date.class)).or(is(equalTo("abc")));
		assertEquals("is instance of: java.util.Date or is equal to: \"abc\"", matcher.describe(newDescription()).toString());
		assertEquals("or{is{instanceOf(java.util.Date)}}{i̶s̶{is{equalTo(abc)}}}", matcher.toString());
		assertTrue(matcher.match(new Date()));
		assertTrue(matcher.match("abc"));
		assertFalse(matcher.match(null));
	}

	/**
	 * Tests the logical OR operation with branches with a different explicit context.
	 * The left branch has an "is instance of" context, the right branch has an "is equal to" context.
	 */
	@Test
	void contextSensitiveMatchingPreservesExplicitContextOfBranches() {
		assertDoesNotThrow(() ->
				assertThat("abc", is(instanceOf(Date.class)).or(is(equalTo("abc")))));
		assertDoesNotThrow(() ->
				assertThat("abc", is(instanceOf(String.class)).or(is(equalTo("def")))));
		assertThrowsAssertionErrorWithMessage(() ->
				assertThat("abc", is(instanceOf(Date.class)).or(is(equalTo("def")))),
				String.format("expected that subject is instance of: %s or is equal to: \"def\"", Date.class.getName()),
				"but was: \"abc\""
		);
	}

	@Test
	void contextSensitiveMatching() {
		assertDoesNotThrow(() -> assertThat("this", is("this").or("that")));
		assertDoesNotThrow(() -> assertThat(1d, is(2d).or(1d)));
		assertThrowsAssertionErrorWithMessage(
				() -> assertThat(1d, is(2d).or(3d)),
				"expected that subject is: 2.0 or: 3.0",
				"but was: 1.0"
		);
		assertThat(1f, is(2f).or(1f));
		assertThat(1L, is(2L).or(1L));
		assertThat(1, is(2).or(1));
		assertThat((short) 1, is((short) 2).or((short) 1));
		assertThat((byte) 1, is((byte) 2).or((byte) 1));
		assertThat('1', is('2').or('1'));
		assertThat(false, is(true).or(false));
	}
}
