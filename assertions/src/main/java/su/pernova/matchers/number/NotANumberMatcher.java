package su.pernova.matchers.number;

import su.pernova.matchers.Description;
import su.pernova.matchers.Matcher;

class NotANumberMatcher<T> extends Matcher<T> {

	private static class Singleton {

		private static final NotANumberMatcher INSTANCE = new NotANumberMatcher();
	}

	private NotANumberMatcher() {
	}

	@Override
	public boolean matches(Object actual) {
		if (actual instanceof Double) {
			return ((Double) actual).isNaN();
		} else if (actual instanceof Float) {
			return ((Float) actual).isNaN();
		}
		return false;
	}

	@Override
	public void describeTo(Description description) {
		description.appendText("not a number");
	}

	static NotANumberMatcher getInstance() {
		return Singleton.INSTANCE;
	}
}
