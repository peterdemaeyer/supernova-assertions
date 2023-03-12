package su.pernova.assertions;

import internal.su.pernova.assertions.matchers.EqualTo;
import internal.su.pernova.assertions.matchers.InstanceOf;
import internal.su.pernova.assertions.matchers.SameAs;

public final class Matchers {

	private Matchers() {
	}

	public static Matcher equalTo(Object expected) {
		return new EqualTo(expected);
	}

	public static Matcher instanceOf(Class clazz) {
		return new InstanceOf(clazz);
	}

	public static Matcher sameAs(Object expected) {
		return new SameAs(expected, null);
	}
}
