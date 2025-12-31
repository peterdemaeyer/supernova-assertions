package internal.su.pernova.assertions;

import static java.lang.Character.isLetterOrDigit;
import static java.lang.Character.isLowerCase;
import static java.lang.Character.toUpperCase;

import su.pernova.assertions.Describable;

public final class DescribableUtils {

	private static final String[] PREFIXES_TO_STRIP = {
			"Base",
			"Basic",
			"Default",
			"Generic"
	};

	private static final String[] SUFFIXES_TO_STRIP = {
			"Impl"
	};

	private DescribableUtils() {
	}

	public static CharSequence getDefaultName(Describable describable) {
		String text = describable.getClass().getSimpleName();
		String previousText = text;
		while (true) {
			for (String prefix : PREFIXES_TO_STRIP) {
				text = stripPrefix(text, prefix);
			}
			for (String suffix : SUFFIXES_TO_STRIP) {
				text = stripSuffix(text, suffix);
			}
			if (text.length() == previousText.length()) {
				break;
			}
			previousText = text;
		}
		text = text.replaceAll("([A-Z]|\\d+)", " $1");
		if (!text.isEmpty()) {
			text = text.substring(1);
		}
		return text.toLowerCase();
	}

	public static String toLowerCamelCase(CharSequence sentenceCase) {
		final StringBuilder builder = new StringBuilder(sentenceCase.length());
		boolean capitalize = false;
		for (int index = 0, length = sentenceCase.length(); index != length; index++) {
			final char c = sentenceCase.charAt(index);
			if (c == ' ') {
				capitalize = true;
			} else if (capitalize) {
				builder.append(toUpperCase(c));
				capitalize = false;
			} else {
				builder.append(c);
			}
		}
		return builder.toString();
	}

	public static String toStrikethrough(CharSequence camelCase) {
		final StringBuilder builder = new StringBuilder(camelCase.length() * 2);
		for (int index = 0, length = camelCase.length(); index != length; index++) {
			final char c = camelCase.charAt(index);
			if (c != '\u0336') {
				builder.append(c);
			}
			if (isLetterOrDigit(c)) {
				builder.append('\u0336');
			}
		}
		return builder.toString();
	}

	private static String stripPrefix(String text, String prefix) {
		if (text.equals(prefix)) {
			return "";
		}
		if (text.startsWith(prefix) && !isLowerCase(text.charAt(prefix.length()))) {
			return text.substring(prefix.length());
		}
		return text;
	}

	private static String stripSuffix(String text, String suffix) {
		if (text.endsWith(suffix)) {
			return text.substring(0, text.length() - suffix.length());
		}
		return text;
	}
}
