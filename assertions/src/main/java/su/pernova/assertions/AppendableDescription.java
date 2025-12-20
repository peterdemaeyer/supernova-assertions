package su.pernova.assertions;

import static java.lang.reflect.Array.get;
import static java.lang.reflect.Array.getLength;
import static java.util.Arrays.binarySearch;
import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;

/**
 * This description implementation appends text and arguments to a destination {@link Appendable}.
 * It is instantiated and called by the framework.
 * Users typically don't need to instantiate nor call this class themselves.
 * Instances of this class are not thread-safe.
 *
 * @since 1.0.0
 */
public class AppendableDescription implements Description {

	/**
	 * This array must be kept sorted because it is used with binarySearch.
	 */
	private static final char[] NO_SPACE_BEFORE = { '\t', '\n', '\r', ' ', '!', ')', ',', '.', ':', ';', '>', '?', ']', '}' };

	/**
	 * This array must be kept sorted because it is used with binarySearch.
	 */
	private static final char[] NO_SPACE_AFTER = { '\t', '\n', '\r', ' ', '(', '<', '[', '{' };

	private final Appendable appendable;

	private Object actualValue = null;

	private Object expectedValue = null;

	private boolean space;

	/**
	 * Constructs an instance of this class delegating to a given appendable.
	 *
	 * @param appendable an appendable to destination to, not {@code null}.
	 */
	public AppendableDescription(Appendable appendable) {
		this.appendable = requireNonNull(appendable, "appendable is null");
	}

	@Override
	public Description appendActualValue(Object actualValue) {
		this.actualValue = actualValue;
		return Description.super.appendActualValue(actualValue);
	}

	@Override
	public Description appendExpectedValue(Object expectedValue) {
		this.expectedValue = expectedValue;
		return Description.super.appendExpectedValue(expectedValue);
	}

	@Override
	public Object getActualValue() {
		return actualValue;
	}

	@Override
	public Object getExpectedValue() {
		return expectedValue;
	}

	/**
	 * Appends a given text as-is to this description.
	 *
	 * @param text a text to append to this description, not {@code null}.
	 * @return this description.
	 */
	public AppendableDescription appendText(CharSequence text) {
		if (!text.isEmpty()) {
			// Regardless of whether space is enabled, if the text already contains whitespace at the start,
			// we disable the space.
			if (hasNoSpaceBefore(text.charAt(0))) {
				space = false;
			}
			try {
				appendSpace();
				appendable.append(text);
			} catch (IOException e) {
				throw new RuntimeException("failed to append text: " + quote(text), e);
			}
			// If the last character of the text is NOT whitespace,
			// schedule a space to be added before the next text.
			space = !hasNoSpaceAfter(text.charAt(text.length() - 1));
		}
		return this;
	}

	private static boolean hasNoSpaceBefore(char c) {
		return binarySearch(NO_SPACE_BEFORE, c) >= 0;
	}

	private static boolean hasNoSpaceAfter(char c) {
		return binarySearch(NO_SPACE_AFTER, c) >= 0;
	}

	private void appendSpace() throws IOException {
		if (space) {
			appendable.append(' ');
		}
	}

	/**
	 * Appends a quoted argument to this description.
	 * Objects of enum types, numbers, classes are unquoted.
	 * Characters are single-quoted, for example 'c'.
	 * Collections and arrays are quoted recursively.
	 * Any other object, including {@link String}, is double-quoted, for example \"string\".
	 *
	 * @param argument an argument to append to this description, which may be {@code null}.
	 * @return this description.
	 */
	public AppendableDescription appendArgument(Object argument) {
		try {
			appendQuoted(appendable, argument);
		} catch (IOException e) {
			throw new RuntimeException("failed to append argument: " + quote(argument), e);
		}
		return this;
	}

	private StringBuilder quote(Object argument) {
		return quote(new StringBuilder(), argument);
	}

	<A extends Appendable> A quote(A appendable, Object argument) {
		try {
			return appendQuoted(appendable, argument);
		} catch (IOException e) {
			throw new RuntimeException("failed to quote argument: " + argument, e);
		}
	}

	private <A extends Appendable> A appendQuoted(A appendable, Object argument) throws IOException {
		appendSpace();
		recursivelyAppendQuoted(appendable, argument);
		space = true;
		return appendable;
	}

	private void recursivelyAppendQuoted(Appendable appendable, Object argument) throws IOException {
		if (argument instanceof Collection) {
			// Don't do instanceof Iterable, because it will also capture UnixPath and possibly other recursive iterables that causes StackOverflowError.
			appendable.append('[');
			int i = 0;
			for (Object element : (Collection<?>) argument) {
				if (i++ > 0) {
					appendable.append(", ");
				}
				recursivelyAppendQuoted(appendable, element);
			}
			appendable.append(']');
		} else if (argument instanceof Map) {
			appendable.append('{');
			int i = 0;
			for (Entry<?, ?> entry : ((Map<?, ?>) argument).entrySet()) {
				if (i++ > 0) {
					appendable.append(", ");
				}
				recursivelyAppendQuoted(appendable, entry.getKey());
				appendable.append('=');
				recursivelyAppendQuoted(appendable, entry.getValue());
			}
			appendable.append('}');
		} else if ((argument == null)
				|| (argument instanceof Number)
				|| (argument instanceof Boolean)
				|| isEnum(argument.getClass())) {
			appendable.append(String.valueOf(argument));
		} else if (argument instanceof Character) {
			appendable.append('\'');
			final char c = (char) argument;
			if ((c == '\'') || (c == '\\')) {
				appendable.append('\\');
			}
			appendable.append((char) argument)
					.append('\'');
		} else if (argument instanceof Class) {
			appendable.append(((Class<?>) argument).getName());
		} else if (argument.getClass().isArray()) {
			appendable.append('[');
			for (int i = 0, n = getLength(argument); i != n; i++) {
				if (i > 0) {
					appendable.append(", ");
				}
				recursivelyAppendQuoted(appendable, get(argument, i));
			}
			appendable.append(']');
		} else {
			appendable.append('"')
					.append(argument.toString().replace("\\", "\\\\").replace("\"", "\\\""))
					.append('"');
		}
	}

	/**
	 * We need to recursively check the supertype hierarchy if this is an enum type.
	 * For example {@code java.util.concurrent.TimeUnit.HOURS.getClass().isEnum()} returns {@code false} because it's a
	 * subtype of an enum type, but we need to recognize it as an enum type.
	 */
	private static boolean isEnum(Class<?> type) {
		while (type != null) {
			if (type.isEnum()) {
				return true;
			}
			type = type.getSuperclass();
		}
		return false;
	}

	@Override
	public String toString() {
		return appendable.toString();
	}
}
