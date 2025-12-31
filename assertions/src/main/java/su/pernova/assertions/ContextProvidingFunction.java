package su.pernova.assertions;

/**
 * @since 2.0.0
 */
public interface ContextProvidingFunction {

	Matcher provide(MatcherFactory matcherFactory);
}
