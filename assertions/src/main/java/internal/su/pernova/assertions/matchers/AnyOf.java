package internal.su.pernova.assertions.matchers;

import static java.lang.System.lineSeparator;

import su.pernova.assertions.Matcher;

public class AnyOf extends CompositeMatcher {

	public AnyOf(CharSequence prefix, CharSequence separator, CharSequence suffix, Matcher... delegates) {
		super(prefix, separator, suffix, delegates);
	}

	@Override
	public boolean match(Object actual) {
		for (Matcher delegate : delegates) {
			if (delegate.match(actual)) {
				return true;
			}
		}
		return false;
	}

	public static AnyOf singleLine(Matcher... delegates) {
		return new AnyOf("[", ", ", "]", delegates);
	}

	public static AnyOf multiLine(Matcher... delegates) {
		return new AnyOf(lineSeparator() + "\t", lineSeparator() + "\t", "", delegates);
	}
}
