/**
 * Supernova Matchers lets users write intuitive assertions using customizable matchers.
 *
 * <h1>Compared to Hamcrest</h1>
 *
 * Being a fork of Hamcrest, it defeats the same purpose, but some additional principles:
 *
 * <ul>
 *     <li>
 *         Restrict the API to the minimum.
 *         Hamcrest has too many classes and methods on its API, making it harder to make compatible API changes.
 *         For example {@code org.hamcrest.core.Is} matcher is on the API while it shouldn't.
 *         It is an implementation detail of the API method {@code Matchers.is}.
 *         The latter should have been the only one of those two on the API.
 *     </li>
 *     <li>
 *         Consistently name methods and matcher building blocks for maximal flexibility.
 *         In particular, verbs and articles such as "is", "does" and "has" are always modeled as separate matchers.
 *         For example {@code assertThat(expected, is(equalTo(actual))}.
 *         Hamcrest does not always use naming consistently.
 *         For example {@code MatcherAssert.instanceOf} is named after the relation "instance of" without being prefixed
 *         with the article "an", while {@code FileMatchers.anExistingDirectory} on the other hand <i>is</i> prefixed
 *         with the article "an".
 *     </li>
 *     <li>
 *         The optional failure reason is the last argument.
 *         This is more intuitive and aligned with JUnit 5, which has a similar change in its API compared to JUnit 4.
 *         Hamcrest is modeled after JUnit 4, which puts the optional failure reason as <i>first</i> argument, which is
 *         less intuitive.
 *     </li>
 * </ul>
 *
 * <h1>Features</h1>
 *
 * <h2>Core Matchers</h2>
 *
 * <h3>"Same as" relation</h3>
 * A matcher for the "same as" relation.
 * It corresponds to the "==" operator in Java.
 * This can be used to test primitives, enums and objects for identity, including {@code null}.
 *
 * {@link su.pernova.matchers.Matchers#is(java.lang.Object)}
 *
 * <pre>{@code
 * assertThat(actual, is(expected));
 * assertThat(
 * }</pre>
 *
 * <h1>Conventions</h1>
 *
 * <table>
 *     <tr>
 *         <th>Applicability</th>
 *         <th>Recommended</th>
 *         <th>Minimalistic</th>
 *     </tr>
 *     <tr>
 *         <td>When asserting object identity and primitive type or enum type equality.</td>
 *         <td>{@code assertThat(actual, is(expected))}</td>
 *         <td></td>
 *     </tr>
 *     <tr>
 *         <td>When asserting an object's "instance of" relation.</td>
 *         <td>{@code assertThat(actual, is(instanceOf(expectedClass)))}</td>
 *         <td>{@code assertThat(actual, instanceOf(expectedClass))}</td>
 *     </tr>
 * </table>
 *
 * <h1>Migrating from JUnit 5</h1>
 *
 * <table>
 *     <tr>
 *         <th>JUnit 5</th>
 *         <th>Supernova Matchers</th>
 *     </tr>
 *     <tr>
 *         <td>{@code assertEquals(expected, actual)}</td>
 *         <td>{@code assertThat(actual, is(equalTo(expected))}</td>
 *     </tr>
 * </table>
 *
 * <h1>Migrating from Hamcrest</h1>
 *
 * <table>
 *     <tr>
 *         <th>Hamcrest</th>
 *         <th>Supernova Matchers</th>
 *     </tr>
 * </table>
 *
 * @author Peter De Maeyer
 */
package su.pernova.matchers;
