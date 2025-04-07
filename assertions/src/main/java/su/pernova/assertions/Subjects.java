package su.pernova.assertions;

import static java.util.ServiceLoader.load;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import internal.su.pernova.assertions.subjects.Condition;
import internal.su.pernova.assertions.subjects.ContentOf;

/**
 * This utility class provides factory methods for all subjects.
 * By design, subjects can be created for any <em>actual</em> object, including {@code null},
 * which means the {@code actual} parameter to create a subject for is never typed but always a generic {@code Object}.
 * Even though it may not seem logical for some subjects to apply to any object,
 * bear in mind that subjects represent the <em>actual</em> value of an assertion,
 * which must be capable of modeling any kind of mismatch, so any kind of object.
 * Take a condition as an example: even though you <em>expect</em> a boolean, you <em>actually</em> may get anything,
 * which will mismatch if it's not a boolean, but you must be able to model it.
 *
 * @since 1.0.0
 */
public final class Subjects {

	private static final List<MatcherProvider> MATCHER_PROVIDERS = loadMatcherProviders();

	private static List<MatcherProvider> loadMatcherProviders() {
		ArrayList<MatcherProvider> matcherProviders = new ArrayList<>();
		for (MatcherProvider matcherProvider : load(MatcherProvider.class)) {
			matcherProviders.add(matcherProvider);
		}
		matcherProviders.trimToSize();
		matcherProviders.sort((p1, p2) -> p2.order() - p1.order());
		return matcherProviders;
	}

	private static final MethodFamily CONTENT_OF = new MethodFamily(Subjects.class, "contentOf");

	private Subjects() {
	}

	/**
	 * Creates a subject for a given object.
	 *
	 * @param actual an object to create a subject for, possibly {@code null}.
	 * @return a subject, not {@code null}.
	 * @since 1.0.0
	 */
	public static Subject subject(Object actual) {
		return new Subject(actual);
	}

	/**
	 * Creates a condition for a given object.
	 *
	 * @param actual an object to create a condition for, possibly {@code null}.
	 * @return a condition, not {@code null}.
	 * @since 1.0.0
	 */
	public static Subject condition(Object actual) {
		return new Condition(actual);
	}

	/**
	 * Creates content for a given object.
	 *
	 * @param actual an object to create content for, possibly {@code null}.
	 * @return content, not {@code null}.
	 * @since 2.0.0
	 */
	public static Subject contentOf(Object actual) {
		return contentOf(actual, null);
	}

	/**
	 * Creates character-encoded content for a given object.
	 *
	 * @param actual an object to create content for, possibly {@code null}.
	 * @param charset a charset, not {@code null}.
	 * @return character-encoded content, not {@code null}.
	 * @since 2.0.0
	 */
	public static Subject contentOf(Object actual, Charset charset) {
		return Context.set(new ContentOf(actual))
				.transformation(ContentOf.transformation(charset))
				.get();
	}

	static Subject defaultSubject(Object actual, Matcher... matchers) {
		return (matchers.length == 0) ? condition(actual) : subject(actual);
	}

	private static Matcher loadMatcher(MethodFamily methodFamily, Object actual) {
		for (MatcherProvider matcherProvider : MATCHER_PROVIDERS) {
			Matcher matcher = matcherProvider.provide(methodFamily, actual);
			if (matcher != null) {
				return matcher;
			}
		}
		return null;
	}
}
