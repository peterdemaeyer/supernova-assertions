package internal.su.pernova.assertions.matchers;

import java.util.function.Function;

import su.pernova.assertions.Description;
import su.pernova.assertions.Matcher;

public class ContextSensitiveMatcher implements ContextSensitive, Matcher {

	private final Function<Context, Matcher> function;

	private Matcher delegate;

	public ContextSensitiveMatcher(Function<Context, Matcher> function) {
		this.function = function;
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
	public void apply(Context context) {
		delegate = function.apply(context);
	}
}
