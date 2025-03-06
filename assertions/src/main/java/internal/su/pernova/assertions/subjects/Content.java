package internal.su.pernova.assertions.subjects;

import su.pernova.assertions.Matcher;

public class Content extends DefaultSubject {

	public Content(Object actual) {
		super(actual);
	}

	@Override
	public boolean match(Matcher matcher) {
		return matcher.match(null/*contentFactory.get()*/);
	}
}
