package su.pernova.assertions;

import static java.lang.reflect.Array.get;
import static java.lang.reflect.Array.getLength;
import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

/**
 * This description implementation appends text and arguments to a delegate {@link Appendable}.
 * It is instantiated and called by the framework.
 * Users typically don't need to instantiate nor call this class themselves.
 * Instances of this class are not thread-safe.
 *
 * @since 1.0.0
 */
public class AppendableDescription implements Description {

	private final Appendable appendable;

	private Object actual = null;

	private Object expected = null;

	/**
	 * Constructs an instance of this class delegating to a given appendable.
	 *
	 * @param appendable an appendable to delegate to, not {@code null}.
	 */
	public AppendableDescription(Appendable appendable) {
		this.appendable = requireNonNull(appendable, "appendable is null");
	}

	@Override
	public Description appendActual(Object actual) {
		this.actual = actual;
		return Description.super.appendActual(actual);
	}

	@Override
	public Description appendExpected(Object expected) {
		this.expected = expected;
		return Description.super.appendExpected(expected);
	}

	@Override
	public Object getActual() {
		return actual;
	}

	@Override
	public Object getExpected() {
		return expected;
	}

	/**
	 * Appends a given text as-is to this description.
	 *
	 * @param text a text to append to this description, not {@code null}.
	 * @return this description.
	 */
	public AppendableDescription appendText(CharSequence text) {
		try {
			appendable.append(text);
		} catch (IOException e) {
			throw new RuntimeException("failed to append text: " + quote(text), e);
		}
		return this;
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
			appendable.append(": ");
			appendQuoted(appendable, argument);
		} catch (IOException e) {
			throw new RuntimeException("failed to append argument: " + quote(argument), e);
		}
		return this;
	}

	private static StringBuilder quote(Object argument) {
		return quote(new StringBuilder(), argument);
	}

	static <A extends Appendable> A quote(A appendable, Object argument) {
		try {
			return appendQuoted(appendable, argument);
		} catch (IOException e) {
			throw new RuntimeException("failed to quote argument: " + argument, e);
		}
	}

	private static <A extends Appendable> A appendQuoted(A appendable, Object argument) throws IOException {
		if (argument instanceof Iterable) {
			appendable.append('[');
			int i = 0;
			for (Object element : (Iterable<? extends Object>) argument) {
				if (i++ > 0) {
					appendable.append(", ");
				}
				appendQuoted(appendable, element);
			}
			appendable.append(']');
		} else if (argument instanceof Map) {
			appendable.append('{');
			int i = 0;
			for (Entry<?, ?> entry : ((Map<?, ?>) argument).entrySet()) {
				if (i++ > 0) {
					appendable.append(", ");
				}
				appendQuoted(appendable, entry.getKey());
				appendable.append('=');
				appendQuoted(appendable, entry.getValue());
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
			appendable.append(((Class) argument).getName());
		} else if (argument.getClass().isArray()) {
			appendable.append('[');
			for (int i = 0, n = getLength(argument); i != n; i++) {
				if (i > 0) {
					appendable.append(", ");
				}
				appendQuoted(appendable, get(argument, i));
			}
			appendable.append(']');
		} else {
			appendable.append('"')
					.append(argument.toString().replace("\\", "\\\\").replace("\"", "\\\""))
					.append('"');
		}
		return appendable;
	}

	/**
	 * We need to recursively check the supertype hierarchy if this is an enum type.
	 * For example {@code java.util.concurrent.TimeUnit.HOURS.getClass().isEnum()} returns {@code false} because it's a
	 * subtype of an enum type, but we need to recognize it as an enum type.
	 */
	private static boolean isEnum(Class type) {
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
