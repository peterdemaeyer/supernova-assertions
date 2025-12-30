package su.pernova.assertions;

public interface ContextRequire {

	Matcher receive(Matcher matcher, MatcherFactory matcherFactory);
}
