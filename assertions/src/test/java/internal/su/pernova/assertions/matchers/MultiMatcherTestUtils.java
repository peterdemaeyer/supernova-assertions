package internal.su.pernova.assertions.matchers;

import static su.pernova.assertions.Matchers.is;

import su.pernova.assertions.Context;
import su.pernova.assertions.Matcher;

public final class MultiMatcherTestUtils {

	private MultiMatcherTestUtils() {
	}

	public static Matcher contextualize(Matcher matcher) {
		return contextualize(matcher, new Context());
	}

	public static Matcher contextualize(Matcher matcher, Context context) {
		return ((Is) is(matcher).contextualize(context)).destination;
	}
}
