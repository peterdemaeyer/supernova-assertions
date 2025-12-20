package su.pernova.assertions;

/**
 * @since 2.0.0
 */
public interface MatcherProvider {

	default int order() {
		return 0;
	}

	Matcher provide(Descriptor subjectDescriptor, Descriptor matcherDescriptor, Object expectedValue, Object... parameters);

	default Matcher provide(Descriptor subjectDescriptor, Descriptor matcherDescriptor, double expectedValue, Object... parameters) {
		return provide(subjectDescriptor, matcherDescriptor, (Object) expectedValue, parameters);
	}

	default Matcher provide(Descriptor subjectDescriptor, Descriptor matcherDescriptor, float expectedValue, Object... parameters) {
		return provide(subjectDescriptor, matcherDescriptor, (Object) expectedValue, parameters);
	}

	default Matcher provide(Descriptor subjectDescriptor, Descriptor matcherDescriptor, long expected, Object... parameters) {
		return provide(subjectDescriptor, matcherDescriptor, (Object) expected, parameters);
	}

	default Matcher provide(Descriptor subjectDescriptor, Descriptor matcherDescriptor, int expected, Object... parameters) {
		return provide(subjectDescriptor, matcherDescriptor, (Object) expected, parameters);
	}

	default Matcher provide(Descriptor subjectDescriptor, Descriptor matcherDescriptor, short expected, Object... parameters) {
		return provide(subjectDescriptor, matcherDescriptor, (Object) expected, parameters);
	}

	default Matcher provide(Descriptor subjectDescriptor, Descriptor matcherDescriptor, byte expected, Object... parameters) {
		return provide(subjectDescriptor, matcherDescriptor, (Object) expected, parameters);
	}

	default Matcher provide(Descriptor subjectDescriptor, Descriptor matcherDescriptor, char expected, Object... parameters) {
		return provide(subjectDescriptor, matcherDescriptor, (Object) expected, parameters);
	}

	default Matcher provide(Descriptor subjectDescriptor, Descriptor matcherDescriptor, boolean expected, Object... parameters) {
		return provide(subjectDescriptor, matcherDescriptor, (Object) expected, parameters);
	}
}
