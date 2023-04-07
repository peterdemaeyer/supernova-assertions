package su.pernova.assertions;

import static java.lang.System.lineSeparator;
import static java.util.Objects.requireNonNull;

import internal.su.pernova.assertions.matchers.EqualTo;
import internal.su.pernova.assertions.matchers.Identity;
import internal.su.pernova.assertions.matchers.SameAs;

public class Assertion {

	private Subject subject;

	Assertion(Subject subject) {
		this.subject = requireNonNull(subject, "subject is null");
	}

	/**
	 * Descriptive decorator for another matcher, which must not be {@code null}.
	 * The mismatch description is "expected that subject <i>matches</i> &lt;matcher&gt; but was: &lt;actual&gt;".
	 *
	 * @since 1.1.0
	 */
	public Assertion matches(Matcher matcher) {
		return evaluate(new Identity("matches", matcher));
	}

	/**
	 * Asserts
	 *
	 * @since 1.0.0
	 */
	public Assertion is(Matcher matcher) {
		return (matcher != null)
				? evaluate(new Identity("is", matcher))
				: is((Object) null);
	}

	/**
	 * @since 1.0.0
	 */
	public Assertion is(Object expected) {
		return evaluate(new SameAs("is", expected));
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
