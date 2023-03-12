package su.pernova.assertions;

import static java.lang.reflect.Array.get;
import static java.lang.reflect.Array.getLength;
import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

public class AppendableDescription implements Description {

	private final Appendable appendable;

	public AppendableDescription(Appendable appendable) {
		this.appendable = requireNonNull(appendable, "appendable is null");
	}

	public AppendableDescription appendText(CharSequence text) {
		try {
			appendable.append(text);
		} catch (IOException e) {
			throw new RuntimeException("failed to append text: " + quote(text), e);
		}
		return this;
	}

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
