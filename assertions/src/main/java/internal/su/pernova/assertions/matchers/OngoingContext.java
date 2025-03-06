package internal.su.pernova.assertions.matchers;

import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

import su.pernova.assertions.Matcher;
import su.pernova.assertions.Matchers;

public class OngoingContext {

	private static final ThreadLocal<OngoingContext> INSTANCES = ThreadLocal.withInitial(OngoingContext::new);

	private final Map<Matcher, Context> contextsByMatcher = new IdentityHashMap<>();

	/**
	 * Context-sensitives to be evaluated later.
	 */
	private final List<ContextSensitive> contextSensitives = new ArrayList<>();

	private Context context;

	private OngoingContext() {
	}

	public static OngoingContext getInstance() {
		return INSTANCES.get();
	}

	public static void clear() {
		INSTANCES.remove();
	}

	public Matcher fork(Matcher parent, Matcher matcher) {
		context = contextsByMatcher.computeIfAbsent(parent, m -> context);
		return matcher;
	}

	/**
	 * Updates the context being built with a context-providing matcher.
	 * Any pending context-sensitives will be evaluated in the given context.
	 * An example of a context-providing matcher is {@link Matchers#equalTo(Matcher)}.
	 * An example of a context-sensitive matcher is {@link Matchers#anyOf(Object...)}.
	 *
	 * @param matcher a context-providing matcher, not {@code null}.
	 * @param context the context provided by the context-providing matcher, not {@code null}.
	 * @return the given matcher, not {@code null}.
	 */
	public Matcher provideContext(Matcher matcher, Context context) {
		contextsByMatcher.put(matcher, this.context = context);
		try {
			contextSensitives.forEach(contextSensitive -> contextSensitive.evaluate(context));
		} catch (RuntimeException | Error e) {
			INSTANCES.remove();
			throw e;
		}
		contextSensitives.clear();
		return matcher;
	}

	public Matcher forkAndEvaluateNow(Matcher parent, ContextSensitiveMatcher matcher) {
		if (context == null) {
			INSTANCES.remove();
			throw new IllegalStateException("no context");
		}
		try {
			return fork(parent, matcher.evaluate(context));
		} catch (RuntimeException | Error e) {
			INSTANCES.remove();
			throw e;
		}
	}

	public ContextSensitiveMatcher evaluateLater(ContextSensitiveMatcher matcher) {
		contextSensitives.add(matcher);
		return matcher;
	}
}
