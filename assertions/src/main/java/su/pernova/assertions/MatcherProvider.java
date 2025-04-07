package su.pernova.assertions;

public interface MatcherProvider {

	default int order() {
		return 0;
	}

	Matcher provide(MethodFamily methodFamily, Object expected);
}
