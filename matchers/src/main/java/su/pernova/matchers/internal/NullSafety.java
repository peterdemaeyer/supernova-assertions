package su.pernova.matchers.internal;

import java.util.ArrayList;
import java.util.List;

import su.pernova.matchers.Matcher;
import su.pernova.matchers.core.Is;

public class NullSafety {

	public static <E> List<Matcher<? super E>> nullSafe(Matcher<? super E>[] itemMatchers) {
		final List<Matcher<? super E>> matchers = new ArrayList<>(itemMatchers.length);
		for (final Matcher<? super E> itemMatcher : itemMatchers) {
			matchers.add((itemMatcher == null) ? new Is<>(null) : itemMatcher);
		}
		return matchers;
	}
}
