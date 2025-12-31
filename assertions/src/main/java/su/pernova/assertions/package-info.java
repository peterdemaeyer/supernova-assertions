/**
 * This package defines an assertions framework for Java, allowing the user to write assertions as flexible declarations
 * of intent.
 * The basic concepts:
 * <ol>
 *     <li>The utility class {@link su.pernova.assertions.Assertions} provides static factory methods for built-in
 *     assertions.
 *     It is recommended to use it with static imports for readability.
 *     The {@code assertThat} family of methods is the starting point of any assertion.</li>
 *     <li>The utility class {@link su.pernova.assertions.Matchers} provides static factory methods for creating
 *     built-in matchers.
 *     It is recommended to use it with static imports for readability.</li>
 *     <li>The interface {@link su.pernova.assertions.Matcher} allows writing user-defined matchers.</li>
 *     <li>The utility class {@link su.pernova.assertions.Subjects} provides static factory methods for built-in
 *     subjects.</li>
 *     <li>The interface {@link su.pernova.assertions.Subject} allows writing user-defined subjects.</li>
 * </ol>
 * <h1>Assertion failure</h1>
 * A failure is an assertion that fails instantly.
 * <pre>{@code
 * fail();
 * }</pre>
 * <h1>Default assertion</h1>
 * The default assertion has no given matchers, so it uses the {@code is(true)} matcher as default.
 * This assumes the subject is a condition.
 * <pre>{@code
 * assertThat(true);
 * }</pre>
 * For example:
 * <pre>{@code
 * assertThat(file.exists()); // Preferred implicit form for readability.
 * assertThat(file.exists(), is(true)); // Equivalent explicit form.
 * }</pre>
 *
 * <h1>Logical matchers</h1>
 *
 * Logical matchers allow combining matchers with logical operations "and", "or" and "not".
 * They also introduce additional identity matchers such as
 * {@link su.pernova.assertions.Matchers#does(su.pernova.assertions.Matcher)},
 * {@link su.pernova.assertions.Matchers#match(su.pernova.assertions.Matcher)}
 * to allow for grammatically correct assertions.
 *
 * <h2>"And" matchers</h2>
 *
 * {@link su.pernova.assertions.Matcher#and(su.pernova.assertions.Matcher)} allows combinations of two matchers.
 * {@link su.pernova.assertions.Matchers#allOf(su.pernova.assertions.Matcher...)} allows combinations of any number of
 * matchers.
 *
 * <pre>{@code
 * assertThat("this", is(instanceOf(String.class)).and(equalTo("this")));
 * assertThat("this", is(allOf(instanceOf(String.class), equalTo("this"))));
 * }</pre>
 *
 * <h2>"Or" matchers</h2>
 *
 * {@link su.pernova.assertions.Matcher#or(su.pernova.assertions.Matcher)} allows combinations of two matchers.
 * {@link su.pernova.assertions.Matchers#anyOf(su.pernova.assertions.Matcher...)} allows combinations of any number of
 * matchers.
 *
 * <h2>Context-sensitive matchers</h2>
 *
 * Context-sensitive matchers are those that have objects or primitive values as parameters,
 * the interpretation of which depends on <i>another</i> context-providing matcher earlier in the assertion expression.
 * Context-sensitive matchers <i>must</i> be used in combination with a context-providing matcher,
 * or else they will result in an {@link java.lang.IllegalStateException} when building the assertion expression.
 * {@link su.pernova.assertions.Matcher#and(su.pernova.assertions.Object)} and equivalent methods with primitive
 * parameters allow context-sensitive combinations of two values.
 *
 * Examples:
 * <pre>{@code
 * assertThat("this", is(instanceOf(String.class).and(CharSequence.class))); // Note the placement of the parenthesis.
 * assertThat("this", is(anyOf("this", "that", "foo")));
 * assertThat("this", is(instanceOf(anyOf(String.class, CharSequence.class, Object.class))));
 * }</pre>
 *
 * <h2>Context-providing matchers</h2>
 *
 * {@link su.pernova.assertions.Matchers#is(su.pernova.assertions.Matcher)} matches values by identity.
 * {@link su.pernova.assertions.Matchers#sameAs(su.pernova.assertions.Matcher)} is equivalent to the above, albeit more
 * explicit.
 * {@link su.pernova.assertions.Matchers#equalTo(su.pernova.assertions.Matcher)} matches values by object equality.
 * {@link su.pernova.assertions.Matchers#instanceOf(su.pernova.assertions.Matcher)} matches class values by
 * "instance of".
 *
 * <h2>"Not" matchers</h2>
 *
 * The {@link su.pernova.assertions.Matchers#not} family of matchers.
 *
 * Examples:
 * <pre>{@code
 * assertThat("this", is(not("that")));
 * assertThat("this", does(not(match(regex("[0-9]*")))));
 * }</pre>
 *
 * <h1>Context-sensitive matchers</h1>
 *
 * Context-sensitive matchers have no meaning on their own, but need to be completed with context from a
 * context-providing matcher in order to be valid. Examples of context-providing matchers are
 * {@link su.pernova.assertions.Matchers#anyOf}, {@link su.pernova.assertions.Matchers#allOf}, {@link su.pernova.assertions.Matchers#noneOf}.
 *
 * Examples:
 * <pre>{@code
 * assertThat(3, is(anyOf(1, 2, 3));
 * }</pre>
 *
 * @since 1.0.0
 */
package su.pernova.assertions;
