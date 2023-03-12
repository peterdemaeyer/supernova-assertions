package su.pernova.assertions;

import static java.lang.System.lineSeparator;
import static java.util.Objects.requireNonNull;

import internal.su.pernova.assertions.matchers.Is;
import internal.su.pernova.assertions.matchers.SameAs;

public class Assertion {

	private Subject subject;

	Assertion(Subject subject) {
		this.subject = requireNonNull(subject, "subject is null");
	}

	public Assertion is(Matcher matcher) {
		return (matcher != null) ? evaluate(new Is(matcher)) : is((Object) null);
	}

	public Assertion is(Object expected) {
		return is(new SameAs(expected, ""));
	}

	private Assertion evaluate(Matcher matcher) {
		if (!subject.match(matcher)) {
			StringBuilder message = new StringBuilder();
			Description desc = new AppendableDescription(message)
					.appendText("expected that");
			subject.describe(desc);
			matcher.describe(desc);
			desc.appendText(lineSeparator())
					.appendText("but");
			matcher.describeMismatch(desc);
			subject.describeMismatch(desc);
			throw new AssertionError(message.toString());
		}
		return this;
	}
}
