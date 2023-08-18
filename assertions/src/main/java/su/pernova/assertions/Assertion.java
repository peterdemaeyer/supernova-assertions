package su.pernova.assertions;

import static java.lang.System.lineSeparator;
import static java.util.Objects.requireNonNull;

import internal.su.pernova.assertions.matchers.MatcherDecorator;

/**
 * This builder class defines an assertion of the form <em>"assert that &lt;(actual) subject&gt; &lt;holds&gt;
 * &lt;relation to&gt; &lt;(expected) object&gt;"</em>, where:
 * <ol>
 *     <li><em>"&lt;assert that&gt;"</em> corresponds to this assertion class;</li>
 *     <li><em>"&lt;(actual) subject&gt;</em> corresponds to a subject created by calling a factory method on
 *     {@link Subjects}</li>
 *     <li><em>"&lt;holds&gt;"</em> corresponds to a matcher created by calling a method on an instance of this class.
 *     Such a method is typically defined by a verb;</li>
 *     <li><em>"&lt;relation to&gt;"</em> corresponds to a matcher, created by calling a factory method on {@link Matchers}.</li>
 *     <li><em>"&lt;(expected) object&gt;"</em> corresponds to the expected object, a parameter of a matcher.</li>
 * </ol>
 *
 * @since 1.0.0
 */
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
		return evaluate(new MatcherDecorator("matches", matcher));
	}

	/**
	 * Asserts that a given matcher matches.
	 * If the given matcher is {@code null}, this method
	 *
	 * @since 1.0.0
	 */
	public Assertion is(Matcher matcher) {
		return (matcher != null)
				? evaluate(new MatcherDecorator("is", matcher))
				: is((Object) null);
	}

	/**
	 * Asserts object identity and returns the assertion for further chaining.
	 * This is equivalent to {@code is(sameAs(expected))}.
	 *
	 * @param expected an expected value, which may be {@code null}.
	 * @return this object, not {@code null}.
	 * @since 1.0.0
	 */
	public Assertion is(Object expected) {
		return evaluate(Matchers.is(expected));
	}

	/**
	 *
	 * @param expected
	 * @return
	 * @since 2.0.0
	 */
	public Assertion is(double expected) {
		return evaluate(Matchers.is(expected));
	}

	/**
	 *
	 * @param expected
	 * @return
	 * @since 2.0.0
	 */
	public Assertion is(float expected) {
		return evaluate(Matchers.is(expected));
	}

	/**
	 *
	 * @param expected
	 * @return
	 * @since 2.0.0
	 */
	public Assertion is(long expected) {
		return evaluate(Matchers.is(expected));
	}

	/**
	 *
	 * @param expected
	 * @return
	 * @since 2.0.0
	 */
	public Assertion is(int expected) {
		return evaluate(Matchers.is(expected));
	}

	/**
	 *
	 * @param expected
	 * @return
	 * @since 2.0.0
	 */
	public Assertion is(short expected) {
		return evaluate(Matchers.is(expected));
	}

	/**
	 *
	 * @param expected
	 * @return
	 * @since 2.0.0
	 */
	public Assertion is(byte expected) {
		return evaluate(Matchers.is(expected));
	}

	/**
	 *
	 * @param expected
	 * @return
	 * @since 2.0.0
	 */
	public Assertion is(char expected) {
		return evaluate(Matchers.is(expected));
	}

	/**
	 *
	 * @param expected
	 * @return
	 * @since 2.0.0
	 */
	public Assertion is(boolean expected) {
		return evaluate(Matchers.is(expected));
	}

	/**
	 * Creates a "does" matcher, decorating a given matcher.
	 * The intended use case:
	 * <pre>{@code
	 * assertThat(() -> methodUnderTest()).does(not(throw_()));
	 * }</pre>
	 *
	 * @param matcher
	 * @return
	 * @since 2.0.0
	 */
//	public Assertion does(Matcher matcher) {
//		return evaluate(new MatcherDecorator("does", matcher));
//	}

	private Assertion evaluate(Matcher matcher) {
		if (!subject.match(matcher)) {
			Description description = new AppendableDescription(new StringBuilder())
					.appendText("expected that");
			subject.describe(description);
			matcher.describe(description);
			description.appendText(lineSeparator())
					.appendText("but");
			matcher.describeMismatch(description);
			subject.describeMismatch(description);
			throw new AssertionError(description);
		}
		return this;
	}
}
