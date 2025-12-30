package su.pernova.assertions;

/**
 * @since 2.0.0
 */
public interface CompletionFunction {

	Matcher complete(MatcherFactory matcherFactory);
}
