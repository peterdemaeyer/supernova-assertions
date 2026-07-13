package internal.su.pernova.assertions.matchers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static su.pernova.assertions.Assertions.assertThat;
import static su.pernova.assertions.MatcherContractTest.newDescription;
import static su.pernova.assertions.Matchers.equalTo;
import static su.pernova.assertions.Matchers.is;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import internal.su.pernova.assertions.matchers.ExpectedValueMatcherTestUtils.IsPrimitiveValueArgumentsProvider;
import su.pernova.assertions.Context;
import su.pernova.assertions.Matcher;

class IntMatcherTest {

	@Test
	void assertionSucceeds() {
		assertThat(2, is(1).or(2));
		assertThat(2L, is(1L).or(2));
		assertThat(2d, is(1d).or(2));
		assertThat(2f, is(1f).or(2));
	}

	@ParameterizedTest
	@ArgumentsSource(IsPrimitiveValueArgumentsProvider.class)
	void isPrimitiveValueOrIntDescription(final Matcher isPrimitiveValue) {
		final Matcher matcher = isPrimitiveValue.or(2);
		final String description = matcher.describe(newDescription()).toString();
		assertTrue(description.matches("is: .+ or: 2"), description);
	}

	@ParameterizedTest
	@ArgumentsSource(IsPrimitiveValueArgumentsProvider.class)
	void contextualizedIsPrimitiveValueOrIntDescription(final Matcher isPrimitiveValue) {
		final Matcher matcher = isPrimitiveValue.or(2);
		final String description = matcher.describe(newDescription()).toString();
		assertEquals(description, matcher.contextualize(new Context()).describe(newDescription()).toString());
	}

	@Test
	void isObjectValueOrIntDescription() {
		final Matcher matcher = is(new Object()).or(2);
		final String description = matcher.describe(newDescription()).toString();
		assertTrue(description.matches("is: java\\.lang\\.Object@[0-9a-f]+ or: java\\.lang\\.Integer@[0-9a-f]+"), description);
	}

	@Test
	void contextualizedIsObjectValueOrIntDescription() {
		final Matcher matcher = is(new Object()).or(2);
		final String description = matcher.describe(newDescription()).toString();
		assertEquals(description, matcher.contextualize(new Context()).describe(newDescription()).toString());
	}

	@ParameterizedTest
	@ArgumentsSource(IsPrimitiveValueArgumentsProvider.class)
	void isPrimitiveValueOrIntStringValue(final Matcher isPrimitiveValue) {
		final String stringValue = isPrimitiveValue.or(2).toString();
		assertTrue(stringValue.matches("or\\{is\\(.+\\)}\\{\\(2\\)}"), stringValue);
	}

	@ParameterizedTest
	@ArgumentsSource(IsPrimitiveValueArgumentsProvider.class)
	void contextualizedIsPrimitiveValueOrIntStringValue(final Matcher isPrimitiveValue) {
		final String stringValue = isPrimitiveValue.or(2).contextualize(new Context()).toString();
		assertTrue(stringValue.matches("or\\{is\\(.+\\)}\\{i̶s̶\\(2\\)}"), stringValue);
	}

	@Test
	void isEqualToIntOrIntStringValue() {
		assertEquals("is{or{equalTo(1)}{(2)}}",
				is(equalTo(1).or(2)).toString());
	}

	@Test
	void contextualizedIsEqualToIntOrIntStringValue() {
		assertEquals("is{or{equalTo(1)}{e̶q̶u̶a̶l̶T̶o̶(2)}}",
				is(equalTo(1).or(2)).contextualize(new Context()).toString());
	}
}
