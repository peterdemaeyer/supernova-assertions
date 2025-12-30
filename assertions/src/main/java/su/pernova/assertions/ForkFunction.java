package su.pernova.assertions;

/**
 * @since 2.0.0
 */
public interface ForkFunction {

	Matcher fork(Matcher... destinations);
}
