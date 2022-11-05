package su.pernova.matchers;

import static java.lang.System.lineSeparator;
import static java.util.Arrays.stream;
import static java.util.concurrent.TimeUnit.MINUTES;
import static java.util.concurrent.TimeUnit.SECONDS;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static su.pernova.matchers.MatcherAssertions.assertThat;
import static su.pernova.matchers.Matchers.is;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import su.pernova.matchers.internal.core.SameAs;

class MatchersTest {

	@Test
	void isMatchesNull() {
		assertDoesNotThrow(() -> assertThat(null, is((Object) null)));
		final AssertionError expectedNull = assertThrows(AssertionError.class, () -> assertThat("not null", is((Object) null)));
		assertEquals(lineSeparator() + "Expected: is null" + lineSeparator() + "     but: was \"not null\"", expectedNull.getMessage());
		final AssertionError didNotExpectNull = assertThrows(AssertionError.class, () -> assertThat(null, is("not null")));
		assertEquals(lineSeparator() + "Expected: is \"not null\"" + lineSeparator() + "     but: was null", didNotExpectNull.getMessage());
	}

	@Test
	void isMatchesNullMatcher() {
		assertDoesNotThrow(() -> assertThat((Matcher) null, is((Matcher) null)));
		final AssertionError expected = assertThrows(AssertionError.class, () -> assertThat(new SameAs<>("", "test"), is((Matcher) null)));
		assertEquals(lineSeparator() + "Expected: is null" + lineSeparator() + "     but: was <\"test\">", expected.getMessage());
	}

	@ParameterizedTest(name = "{0}")
	@ArgumentsSource(IsArgumentsProvider.class)
	void isMatchesPrimitivesAndEnums(Object type, Object value, Object mismatch, String formattedValue, String formattedMismatch) {
		assertDoesNotThrow(() -> assertThat(value, is(value)));
		final AssertionError expected = assertThrows(AssertionError.class, () -> assertThat(mismatch, is(value)));
		assertEquals(lineSeparator() + "Expected: is " + formattedValue + lineSeparator() + "     but: was " + formattedMismatch, expected.getMessage());
	}

	@Test
	@SuppressWarnings("StringOperationCanBeSimplified")
	void isMatchesSameButNotEqualObjects() {
		String value = new String("a");
		String equalValue = new String("a");
		String nonEqualValue = "b";
		assertDoesNotThrow(() -> assertThat(value, is(value)));
		final AssertionError expectedSame = assertThrows(AssertionError.class, () -> assertThat(value, is(nonEqualValue)));
		assertEquals(expectedMessage("Expected: is \"b\"", "     but: was \"a\""), expectedSame.getMessage());
		final AssertionError expectedSameNotEqual = assertThrows(AssertionError.class, () -> assertThat(value, is(equalValue)));
		assertEquals(expectedMessage("Expected: is \"a\"", "     but: was \"a\""), expectedSameNotEqual.getMessage());
	}

	private static String expectedMessage(String... lines) {
		final StringBuilder messageBuilder = new StringBuilder();
		stream(lines).forEach(line -> messageBuilder.append(lineSeparator()).append(line));
		return messageBuilder.toString();
	}

	static class IsArgumentsProvider implements ArgumentsProvider {

		@Override
		public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
			return Stream.of(
					Arguments.of(int.class, 1, 2, "<1>", "<2>"),
					Arguments.of(long.class, 1L, 2L, "<1L>", "<2L>"),
					Arguments.of(short.class, (short) 1, (short) 2, "<1s>", "<2s>"),
					Arguments.of(byte.class, (byte) 1, (byte) 2, "<1b>", "<2b>"),
					Arguments.of(boolean.class, true, false, "<true>", "<false>"),
					Arguments.of(double.class, 1., 2., "<1.0>", "<2.0>"),
					Arguments.of(float.class, 1f, 2f, "<1.0F>", "<2.0F>"),
					Arguments.of(char.class, 'a', 'b', "\"a\"", "\"b\""),
					Arguments.of("enum", SECONDS, MINUTES, "<" + SECONDS + ">", "<" + MINUTES + ">")
			);
		}
	}
}
