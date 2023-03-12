package su.pernova.assertions;

import static su.pernova.assertions.Subjects.subject;

public final class Assertions {

	private Assertions() {
	}

	public static Assertion assertThat(Object actual) {
		return assertThat(subject(actual));
	}

	public static Assertion assertThat(Subject subject) {
		return (subject != null) ? new Assertion(subject) : assertThat((Object) null);
	}
}
