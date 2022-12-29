package su.pernova.matchers.contains;

import su.pernova.matchers.Assertion;
import su.pernova.matchers.Matcher;

public class CollectionAssertion extends Assertion {

	private boolean inOrder;

	CollectionAssertion(CharSequence subject, Object actual) {
		super(subject, actual);
	}

	CollectionAssertion contains(Object... elements) {
		return this;
	}

	CollectionAssertion contains(Matcher elementMatcher) {
		return this;
	}

	CollectionAssertion contains(Matcher... elementMatcher) {
		return this;
	}

	public CollectionAssertion inOrder(boolean inOrder) {
		this.inOrder = inOrder;
		return this;
	}

	public CollectionAssertion inOrder() {
		return inOrder(true);
	}

	boolean isInOrder() {
		return inOrder;
	}
}
