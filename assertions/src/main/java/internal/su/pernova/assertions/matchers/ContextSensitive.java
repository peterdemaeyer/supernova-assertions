package internal.su.pernova.assertions.matchers;

import java.util.ArrayList;
import java.util.List;

/**
 * A context-sensitive matcher is a matcher that cannot be used without context.
 * It is for example an "anyOf" matcher that can only be used in the context of an "is" or "equalTo" matcher.
 * "assert that subject is any of ..." or "assert that subject is equal to any of".
 * Matchers such as "is", "equal to" or "instance of" which provide context for context-sensitive matchers are
 * contextual matchers.
 */
public interface ContextSensitive {

	void apply(Context context);

	ThreadLocal<List<ContextSensitive>> THREAD_LOCAL_REGISTRY = ThreadLocal
			.withInitial(ArrayList::new);

	static <C extends ContextSensitive> C requireContext(C contextSensitive) {
		THREAD_LOCAL_REGISTRY.get().add(contextSensitive);
		return contextSensitive;
	}

	static void provideContext(Context context) {
		try {
			THREAD_LOCAL_REGISTRY.get().forEach(contextSensitive -> contextSensitive.apply(context));
		} finally {
			// If contextSensitive.apply(context) throws, we also need to remove all context-sensitives.
			// Otherwise, they will pollute static state, causing a subsequent assertThat statement to fail.
			THREAD_LOCAL_REGISTRY.remove();
		}
	}
}
