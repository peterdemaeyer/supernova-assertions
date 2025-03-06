package internal.su.pernova.assertions.matchers;

import su.pernova.assertions.Matcher;

public interface ContextSensitive {

	/**
	 * Evaluates this object in a given context, returning a matcher.
	 *
	 * @param context a context to evaluate this object in.
	 * @return a matcher, not {@code null}.
	 */
	Matcher evaluate(Context context);
}
