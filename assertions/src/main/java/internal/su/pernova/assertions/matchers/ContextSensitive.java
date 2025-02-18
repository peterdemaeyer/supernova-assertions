package internal.su.pernova.assertions.matchers;

import java.util.ArrayList;
import java.util.List;

import su.pernova.assertions.Matcher;

/**
 * A context-sensitive matcher is a matcher that cannot be used without context.
 * It is for example an "anyOf" matcher that can only be used in the context of an "is" or "equalTo" matcher.
 * "assert that subject is any of ..." or "assert that subject is equal to any of".
 * Matchers such as "is", "equal to" or "instance of" which provide context for context-sensitive matchers are
 * contextual matchers.
 */
public interface ContextSensitive {

	void apply(Context context);

	ThreadLocal<List<ContextSensitive>> CONTEXT_SENSITIVES = ThreadLocal.withInitial(ArrayList::new);

	/**
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
	ThreadLocal<List<Context>> CONTEXTS = ThreadLocal.withInitial(ArrayList::new);

	static <C extends ContextSensitive> C requireBackwardContext(C contextSensitive) {
		CONTEXT_SENSITIVES.get().add(contextSensitive);
		return contextSensitive;
	}

	static <C extends ContextSensitive> C requireForwardContext(C contextSensitive) {
		CONTEXTS.get().forEach(context -> contextSensitive.apply(context));
		return contextSensitive;
	}

	static <M extends Matcher> M provideContext(M matcher, Context context) {
		CONTEXTS.get().add(context);
		CONTEXT_SENSITIVES.get().forEach(contextSensitive -> contextSensitive.apply(context));
		return matcher;
	}

	static void clear() {
		CONTEXTS.remove();
		CONTEXT_SENSITIVES.remove();
	}
}
