package internal.su.pernova.assertions.matchers;

import static java.lang.System.lineSeparator;

import su.pernova.assertions.Matcher;

public class AllOf extends CompositeMatcher<AllOf> {

	public AllOf(CharSequence prefix, CharSequence separator, CharSequence suffix, Matcher... delegates) {
		super(prefix, separator, suffix, delegates);
	}

	@Override
	public boolean match(Object actualValue) {
		for (Matcher delegate : delegates) {
			if (!delegate.match(actualValue)) {
				return false;
			}
		}
		return true;
	}

	public static AllOf singleLine(Matcher... delegates) {
		return new AllOf("[", ", ", "]", delegates);
	}

	public static AllOf multiLine(Matcher... delegates) {
		return new AllOf(lineSeparator() + "\t", lineSeparator() + "\t", "", delegates);
	}
}
