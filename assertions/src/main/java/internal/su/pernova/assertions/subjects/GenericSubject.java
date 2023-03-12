package internal.su.pernova.assertions.subjects;

import su.pernova.assertions.Description;
import su.pernova.assertions.Matcher;
import su.pernova.assertions.Subject;

public class GenericSubject implements Subject {

	protected final Object actual;

	public GenericSubject(Object actual) {
		this.actual = actual;
	}

	@Override
	public boolean match(Matcher matcher) {
		return matcher.match(actual);
	}

	@Override
	public Description describeMismatch(Description mismatchDescription) {
		return mismatchDescription.appendArgument(actual);
	}
}
