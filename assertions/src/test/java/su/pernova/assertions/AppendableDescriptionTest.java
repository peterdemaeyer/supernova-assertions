package su.pernova.assertions;

import static java.util.Arrays.asList;
import static java.util.concurrent.TimeUnit.HOURS;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyChar;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

class AppendableDescriptionTest {

	private final StringBuilder appendable = new StringBuilder();

	private final Appendable throwingAppendable = mockThrowingAppendable(new IOException("induced by test"));

	@Test
	void constructionThrowsWhenAppendableIsNull() {
		final NullPointerException expected = assertThrows(NullPointerException.class, () -> new AppendableDescription(null));
		assertEquals("appendable is null", expected.getMessage());
	}

	@Test
	void appendingText() {
		new AppendableDescription(appendable)
				.appendText("Text \"...\".");
		assertEquals("Text \"...\".", appendable.toString());
	}

	@Test
	void appendingNullArgument() {
		new AppendableDescription(appendable)
				.appendText("argument")
				.appendPrompt()
				.appendArgument(null);
		assertEquals("argument: null", appendable.toString());
	}

	@Test
	void appendingEnumArgument() {
		new AppendableDescription(appendable)
				.appendText("time unit")
				.appendPrompt()
				.appendArgument(HOURS);
		assertEquals("time unit: HOURS", appendable.toString());
	}

	@Test
	void appendingByteArgument() {
		new AppendableDescription(appendable)
				.appendText("argument")
				.appendPrompt()
				.appendArgument((byte) 5);
		assertEquals("argument: 5", appendable.toString());
	}

	@Test
	void appendingShortArgument() {
		new AppendableDescription(appendable)
				.appendText("argument")
				.appendPrompt()
				.appendArgument((short) 5);
		assertEquals("argument: 5", appendable.toString());
	}

	@Test
	void appendingIntegerArgument() {
		new AppendableDescription(appendable)
				.appendText("argument")
				.appendPrompt()
				.appendArgument(-3);
		assertEquals("argument: -3", appendable.toString());
	}

	@Test
	void appendingLongArgument() {
		new AppendableDescription(appendable)
				.appendText("argument")
				.appendPrompt()
				.appendArgument(999_999_999_999L);
		assertEquals("argument: 999999999999", appendable.toString());
	}

	@Test
	void appendingFloatArgument() {
		new AppendableDescription(appendable)
				.appendText("argument")
				.appendPrompt()
				.appendArgument(1.2f);
		assertEquals("argument: 1.2", appendable.toString());
	}

	@Test
	void appendingDoubleArgument() {
		new AppendableDescription(appendable)
				.appendText("double")
				.appendPrompt()
				.appendArgument(3.4d);
		assertEquals("double: 3.4", appendable.toString());
	}

	@Test
	void appendingBooleanArgument() {
		new AppendableDescription(appendable)
				.appendText("boolean")
				.appendPrompt()
				.appendArgument(true);
		assertEquals("boolean: true", appendable.toString());
	}

	@Test
	void appendingCharacterArgument() {
		new AppendableDescription(appendable)
				.appendText("characters")
				.appendPrompt()
				.appendArgument('g')
				.appendPrompt()
				.appendArgument('\'')
				.appendPrompt()
				.appendArgument('\\');
		assertEquals("characters: 'g': '\\'': '\\\\'", appendable.toString());
	}

	@Test
	void appendingBigDecimalArgument() {
		new AppendableDescription(appendable)
				.appendText("argument")
				.appendPrompt()
				.appendArgument(BigDecimal.ONE);
		assertEquals("argument: 1", appendable.toString());
	}

	@Test
	void appendingCharSequenceArgument() {
		final String charSequence = "abc\" \t€\\";
		new AppendableDescription(appendable)
				.appendText("character sequence")
				.appendPrompt()
				.appendArgument(charSequence);
		assertEquals("character sequence: \"abc\\\" \t€\\\\\"", appendable.toString());
	}

	@Test
	void appendingCollectionArgument() {
		final List<Object> collection = asList(1, 2.4, "abc");
		new AppendableDescription(appendable)
				.appendText("collection")
				.appendPrompt()
				.appendArgument(collection);
		assertEquals("collection: [1, 2.4, \"abc\"]", appendable.toString());
	}

	@Test
	void appendingArrayArgument() {
		final Object[] array = {1, 2.4, "abc"};
		new AppendableDescription(appendable)
				.appendText("argument")
				.appendPrompt()
				.appendArgument(array);
		assertEquals("argument: [1, 2.4, \"abc\"]", appendable.toString());
	}

	@Test
	void appendingClassArgument() {
		final Class class_ = String.class;
		new AppendableDescription(appendable)
				.appendText("argument")
				.appendPrompt()
				.appendArgument(class_);
		assertEquals("argument: " + String.class.getName(), appendable.toString());
	}

	@Test
	void appendingMapArgument() {
		final Map<Object, Object> map = new LinkedHashMap<>();
		map.put(5, "five");
		map.put("seven point five", 7.5);
		new AppendableDescription(appendable)
				.appendText("map")
				.appendPrompt()
				.appendArgument(map);
		assertEquals("map: {5=\"five\", \"seven point five\"=7.5}", appendable.toString());
	}

	private static Appendable mockThrowingAppendable(IOException exception) {
		final Appendable appendable = mock(Appendable.class);
		try {
			when(appendable.append(any(CharSequence.class))).thenThrow(exception);
			when(appendable.append(any(CharSequence.class), anyInt(), anyInt())).thenThrow(exception);
			when(appendable.append(anyChar())).thenThrow(exception);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return appendable;
	}

	@Test
	void appendingTextThrows() {
		final RuntimeException expectedException = assertThrows(RuntimeException.class,
				() -> new AppendableDescription(throwingAppendable).appendText("abc"));
		assertEquals("failed to append text: \"abc\"", expectedException.getMessage());
	}

	@Test
	void appendingArgumentThrows() {
		final RuntimeException expectedException = assertThrows(RuntimeException.class,
				() -> new AppendableDescription(throwingAppendable).appendArgument(789));
		assertEquals("failed to append argument: 789", expectedException.getMessage());
	}

	@Test
	void quotingThrows() {
		final RuntimeException expectedException = assertThrows(RuntimeException.class,
				() -> new AppendableDescription(throwingAppendable).quote(throwingAppendable, "abc"));
		assertEquals("failed to quote argument: abc", expectedException.getMessage());
	}

	@Test
	void stringValue() {
		final Description description = new AppendableDescription(appendable)
				.appendText("string value")
				.appendPrompt()
				.appendArgument("argument");
		assertEquals(appendable.toString(), description.toString());
	}

	@Test
	void appendingTextAutomaticallyAddsSpaces() {
		new AppendableDescription(appendable)
				.appendText("")
				.appendText("The quick")
				.appendText("brown fox.")
				.appendText("Which one")
				.appendText("?")
				.appendText("That ")
				.appendText("one.");
		assertEquals("The quick brown fox. Which one? That one.", appendable.toString());
	}

	@Test
	void appendingExpectedAndActual() {
		Object expected = new Object();
		Object actual = new Object();
		Description description = new AppendableDescription(appendable)
				.appendActual(actual)
				.appendExpected(expected);
		assertEquals(expected, description.getExpected());
		assertEquals(actual, description.getActual());
	}
}
