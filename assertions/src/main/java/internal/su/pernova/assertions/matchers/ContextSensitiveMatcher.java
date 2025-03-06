package internal.su.pernova.assertions.matchers;

import su.pernova.assertions.Description;
import su.pernova.assertions.Matcher;

/**
 * A context-sensitive matcher is a matcher that cannot be used without context.
 * It is for example an "anyOf" matcher that can only be used in the context of another context-providing matcher such
 * as "is" or "equalTo" matcher.
 * "assert that subject is any of ..." or "assert that subject is equal to any of".
 * Matchers such as "is", "equal to" or "instance of" which provide context for context-sensitive matchers are
 * context-providing matchers.
 *
 * Contexts to be forwarded to context-sensitives in a left-to-right context.
 * For example:
 * <pre>{@code
 * assertThat(1, is(2).or(1));
 *               |     |
 *               |     L context-sensitive "or" matcher is evaluated LAST.
 *               |
 *               L context-providing "is" matcher is evaluated FIRST.
 * }</pre>
 */
 public class ContextSensitiveMatcher implements ContextSensitive, Matcher {

	private final ContextSensitive contextSensitive;

	private Matcher delegate;

	public ContextSensitiveMatcher(ContextSensitive contextSensitive) {
		this.contextSensitive = contextSensitive;
	}

	private Matcher requireDelegate() {
		if (delegate == null) {
			throw new IllegalStateException("no context");
		}
		return delegate;
	}

	@Override
	public boolean match(Object actual) {
		return requireDelegate().match(actual);
	}

	@Override
	public Description describe(Description description) {
		return requireDelegate().describe(description);
	}

	@Override
	public Description describeMismatch(Description mismatchDescription) {
		return requireDelegate().describeMismatch(mismatchDescription);
	}

	@Override
	public Matcher evaluate(Context context) {
		return delegate = contextSensitive.evaluate(context);
	}
}
