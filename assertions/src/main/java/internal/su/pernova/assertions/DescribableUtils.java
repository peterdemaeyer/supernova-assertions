package internal.su.pernova.assertions;

import static java.lang.Character.isLowerCase;

import su.pernova.assertions.AppendableDescription;
import su.pernova.assertions.Describable;
import su.pernova.assertions.Description;

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

	public static CharSequence getDefaultDescriptionText(Describable describable) {
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
		return text.replaceAll("([A-Z]|\\d+)", " $1").toLowerCase();
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
