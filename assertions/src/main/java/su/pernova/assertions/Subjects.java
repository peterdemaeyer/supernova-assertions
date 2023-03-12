package su.pernova.assertions;

import internal.su.pernova.assertions.subjects.Condition;
import internal.su.pernova.assertions.subjects.GenericSubject;

public final class Subjects {

	private Subjects() {
	}

	public static Subject subject(Object actual) {
		return new GenericSubject(actual);
	}

	public static Subject condition(Object actual) {
		return new Condition(actual);
	}
}
