package internal.su.pernova.assertions.matchers;

import static internal.su.pernova.assertions.DescribableUtils.toStrikethrough;

import internal.su.pernova.assertions.AnonymousDescribable;
import su.pernova.assertions.Matcher;

public class AnonymousMatcher extends AnonymousDescribable<Matcher> implements Matcher {

	public AnonymousMatcher(Matcher delegatee) {
		super(delegatee);
	}

	@Override
	public boolean match(Object actualValue) {
		return delegatee.match(actualValue);
	}

	@Override
	public Matcher and(Matcher matcher) {
		return delegatee.and(matcher);
	}

	@Override
	public Matcher and(Object expectedValue) {
		return delegatee.and(expectedValue);
	}

	@Override
	public Matcher and(double expectedValue) {
		return delegatee.and(expectedValue);
	}

	@Override
	public Matcher and(float expectedValue) {
		return delegatee.and(expectedValue);
	}

	@Override
	public Matcher and(long expectedValue) {
		return delegatee.and(expectedValue);
	}

	@Override
	public Matcher and(int expectedValue) {
		return delegatee.and(expectedValue);
	}

	@Override
	public Matcher and(short expectedValue) {
		return delegatee.and(expectedValue);
	}

	@Override
	public Matcher and(byte expectedValue) {
		return delegatee.and(expectedValue);
	}

	@Override
	public Matcher and(char expectedValue) {
		return delegatee.and(expectedValue);
	}

	@Override
	public Matcher and(boolean expectedValue) {
		return delegatee.and(expectedValue);
	}

	@Override
	public Matcher or(Matcher matcher) {
		return delegatee.or(matcher);
	}

	@Override
	public Matcher or(Object expectedValue) {
		return delegatee.or(expectedValue);
	}

	@Override
	public Matcher or(double expectedValue) {
		return delegatee.or(expectedValue);
	}

	@Override
	public Matcher or(float expectedValue) {
		return delegatee.or(expectedValue);
	}

	@Override
	public Matcher or(long expectedValue) {
		return delegatee.or(expectedValue);
	}

	@Override
	public Matcher or(int expectedValue) {
		return delegatee.or(expectedValue);
	}

	@Override
	public Matcher or(short expectedValue) {
		return delegatee.or(expectedValue);
	}

	@Override
	public Matcher or(byte expectedValue) {
		return delegatee.or(expectedValue);
	}

	@Override
	public Matcher or(char expectedValue) {
		return delegatee.or(expectedValue);
	}

	@Override
	public Matcher or(boolean expectedValue) {
		return delegatee.or(expectedValue);
	}

	@Override
	public String toString() {
		final String explicitStringValue = delegatee.toString();
		final int leftDelimiterIndex = indexOf(explicitStringValue, '(', '{');
		return leftDelimiterIndex >= 0
				? toStrikethrough(explicitStringValue.substring(0, leftDelimiterIndex)) + explicitStringValue.substring(leftDelimiterIndex)
				: toStrikethrough(explicitStringValue);
	}

	private static int indexOf(CharSequence charSequence, char... chars) {
		for (int i = 0, n = charSequence.length(); i != n; i++) {
			char candidate = charSequence.charAt(i);
			for (char c : chars) {
				if (candidate == c) {
					return i;
				}
			}
		}
		return -1;
	}
}
