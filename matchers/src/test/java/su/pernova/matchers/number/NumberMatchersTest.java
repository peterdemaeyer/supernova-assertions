package su.pernova.matchers.number;

import static java.lang.Double.NaN;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static su.pernova.matchers.Matchers.is;
import static su.pernova.matchers.number.NumberMatchers.notANumber;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import su.pernova.matchers.Matcher;

class NumberMatchersTest {

	@ParameterizedTest
	@ArgumentsSource(MatcherInstanceArgumentsProvider.class)
	void matcherInstanceIsNotNull(Matcher matcher) {
		assertNotNull(matcher);
	}

	@Test
	void differenceBetweenIdentityMatcherAndNotANumberMatcher() {
		// The identity matcher based on "==" returns false for NaN == NaN.
		final Matcher identityMacher = is(NaN);
		assertFalse(identityMacher.matches(NaN));
		// The not-a-number matcher based on isNaN returns true.
		final Matcher notANumberMatcher = notANumber();
		assertTrue(notANumberMatcher.matches(NaN));
	}

	private static class MatcherInstanceArgumentsProvider implements ArgumentsProvider {

		@Override
		public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
			return Stream.of(
					Arguments.of(notANumber())
			);
		}
	}
}
