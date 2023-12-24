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
 * @since 1.0.0
 */
package su.pernova.assertions;
