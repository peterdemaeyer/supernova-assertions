package su.pernova.matchers.contains;

import java.util.Iterator;

import su.pernova.matchers.Description;
import su.pernova.matchers.Matcher;

public class ContainsMatcher extends Matcher {

	private final CollectionAssertion assertion;

	ContainsMatcher(CollectionAssertion assertion) {
		this.assertion = assertion;
	}

	@Override
	public boolean matches(Object actual) {
		if (actual == null) {
			return false;
		}
		Iterator iterator = null;
		if (actual.getClass().isArray()) {
			iterator = new ArrayIterator(actual);
		} else if (actual instanceof Iterable) {
			iterator = ((Iterable) actual).iterator();
		}
		if (iterator == null) {
			return false;
		}
		return matches(iterator);
	}

	private boolean matches(Iterator iterator) {
		while (iterator.hasNext()) {
			iterator.next();
		}
		return false;
	}

	@Override
	public void describeTo(Description description) {

	}
}
