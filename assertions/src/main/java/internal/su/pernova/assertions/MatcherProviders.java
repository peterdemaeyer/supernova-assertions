package internal.su.pernova.assertions;

import static java.util.ServiceLoader.load;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.Function;

import su.pernova.assertions.Descriptor;
import su.pernova.assertions.Matcher;
import su.pernova.assertions.MatcherProvider;

public final class MatcherProviders {

	private static final Comparator<MatcherProvider> BY_ORDER = (p1, p2) -> p2.order() - p1.order();

	private static final ArrayList<MatcherProvider> MATCHER_PROVIDERS = loadMatcherProviders();

	private static ArrayList<MatcherProvider> loadMatcherProviders() {
		ArrayList<MatcherProvider> matcherProviders = new ArrayList<>();
		for (MatcherProvider matcherProvider : load(MatcherProvider.class)) {
			matcherProviders.add(matcherProvider);
		}
		matcherProviders.sort(BY_ORDER);
		matcherProviders.trimToSize();
		return matcherProviders;
	}

	private MatcherProviders() {
	}

	public static Matcher getMatcher(Descriptor subjectDescriptor, Descriptor matcherDescriptor, Object expectedValue, Object... parameters) {
		return getMatcher(matcherProvider -> matcherProvider.provide(subjectDescriptor, matcherDescriptor, expectedValue, parameters));
	}

	public static Matcher getMatcher(Descriptor subjectDescriptor, Descriptor matcherDescriptor, double expectedValue, Object... parameters) {
		return getMatcher(matcherProvider -> matcherProvider.provide(subjectDescriptor, matcherDescriptor, expectedValue, parameters));
	}

	public static Matcher getMatcher(Descriptor subjectDescriptor, Descriptor matcherDescriptor, float expectedValue, Object... parameters) {
		return getMatcher(matcherProvider -> matcherProvider.provide(subjectDescriptor, matcherDescriptor, expectedValue, parameters));
	}

	public static Matcher getMatcher(Descriptor subjectDescriptor, Descriptor matcherDescriptor, long expectedValue, Object... parameters) {
		return getMatcher(matcherProvider -> matcherProvider.provide(subjectDescriptor, matcherDescriptor, expectedValue, parameters));
	}

	public static Matcher getMatcher(Descriptor subjectDescriptor, Descriptor matcherDescriptor, int expectedValue, Object... parameters) {
		return getMatcher(matcherProvider -> matcherProvider.provide(subjectDescriptor, matcherDescriptor, expectedValue, parameters));
	}

	public static Matcher getMatcher(Descriptor subjectDescriptor, Descriptor matcherDescriptor, short expectedValue, Object... parameters) {
		return getMatcher(matcherProvider -> matcherProvider.provide(subjectDescriptor, matcherDescriptor, expectedValue, parameters));
	}

	public static Matcher getMatcher(Descriptor subjectDescriptor, Descriptor matcherDescriptor, byte expectedValue, Object... parameters) {
		return getMatcher(matcherProvider -> matcherProvider.provide(subjectDescriptor, matcherDescriptor, expectedValue, parameters));
	}

	public static Matcher getMatcher(Descriptor subjectDescriptor, Descriptor matcherDescriptor, char expectedValue, Object... parameters) {
		return getMatcher(matcherProvider -> matcherProvider.provide(subjectDescriptor, matcherDescriptor, expectedValue, parameters));
	}

	public static Matcher getMatcher(Descriptor subjectDescriptor, Descriptor matcherDescriptor, boolean expectedValue, Object... parameters) {
		return getMatcher(matcherProvider -> matcherProvider.provide(subjectDescriptor, matcherDescriptor, expectedValue, parameters));
	}

	private static Matcher getMatcher(Function<MatcherProvider, Matcher> matcherFunction) {
		for (MatcherProvider matcherProvider : MATCHER_PROVIDERS) {
			Matcher matcher = matcherFunction.apply(matcherProvider);
			if (matcher != null) {
				return matcher;
			}
		}
		throw new IllegalStateException("no matcher provider");
	}
}
