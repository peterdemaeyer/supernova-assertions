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
 *
 * @since 1.0.0
 */
package su.pernova.assertions;
