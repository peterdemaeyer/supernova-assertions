package internal.su.pernova.assertions.subjects;

import internal.su.pernova.assertions.ImplicitDescribable;
import su.pernova.assertions.Matcher;
import su.pernova.assertions.Subject;

public class ImplicitSubject extends ImplicitDescribable<Subject> implements Subject {

	public ImplicitSubject(Subject destination) {
		super(destination);
	}

	@Override
	public boolean match(Matcher matcher) {
		return destination.match(matcher);
	}
}
