package su.pernova.matchers;

import java.math.BigDecimal;
import java.util.EventObject;
import java.util.regex.Pattern;
import javax.xml.namespace.NamespaceContext;

import org.w3c.dom.Node;

import su.pernova.matchers.core.AllOf;
import su.pernova.matchers.core.AnyOf;
import su.pernova.matchers.core.CombinableMatcher;
import su.pernova.matchers.core.CoreMatchers;
import su.pernova.matchers.core.DescribedAs;
import su.pernova.matchers.core.EqualsMatcher;
import su.pernova.matchers.core.Every;
import su.pernova.matchers.core.IsAnything;
import su.pernova.matchers.core.IsInstanceOf;
import su.pernova.matchers.core.IsIterableContaining;
import su.pernova.matchers.core.SameAsMatcher;
import su.pernova.matchers.core.StringContains;
import su.pernova.matchers.core.StringEndsWith;
import su.pernova.matchers.core.StringRegularExpression;
import su.pernova.matchers.core.StringStartsWith;
import su.pernova.matchers.number.BigDecimalCloseTo;
import su.pernova.matchers.number.IsCloseTo;
import su.pernova.matchers.number.NumberMatchers;
import su.pernova.matchers.object.HasToString;
import su.pernova.matchers.object.IsCompatibleType;
import su.pernova.matchers.object.IsEventFrom;
import su.pernova.matchers.text.CharSequenceLength;
import su.pernova.matchers.text.IsBlankString;
import su.pernova.matchers.text.IsEmptyString;
import su.pernova.matchers.text.IsEqualCompressingWhiteSpace;
import su.pernova.matchers.text.IsEqualIgnoringCase;
import su.pernova.matchers.text.MatchesPattern;
import su.pernova.matchers.text.StringContainsInOrder;

public final class Matchers {

	private Matchers() {
		// Prevent instantiation.
	}

	/**
	 * Creates a matcher that matches if the examined object matches <b>ALL</b> of the specified matchers.
	 * For example:
	 * <pre>assertThat("myValue", allOf(startsWith("my"), containsString("Val")))</pre>
	 */
	public static <T> Matcher<T> allOf(Iterable<Matcher<? super T>> matchers) {
		return AllOf.allOf(matchers);
	}

	/**
	 * Creates a matcher that matches if the examined object matches <b>ALL</b> of the specified matchers.
	 * For example:
	 * <pre>assertThat("myValue", allOf(startsWith("my"), containsString("Val")))</pre>
	 */
	@SafeVarargs
	public static <T> Matcher<T> allOf(Matcher<? super T>... matchers) {
		return AllOf.allOf(matchers);
	}

	/**
	 * Creates a matcher that matches if the examined object matches <b>ALL</b> of the specified matchers.
	 * For example:
	 * <pre>assertThat("myValue", allOf(startsWith("my"), containsString("Val")))</pre>
	 */
	public static <T> Matcher<T> allOf(Matcher<? super T> first, Matcher<? super T> second) {
		return AllOf.allOf(first, second);
	}

	/**
	 * Creates a matcher that matches if the examined object matches <b>ALL</b> of the specified matchers.
	 * For example:
	 * <pre>assertThat("myValue", allOf(startsWith("my"), containsString("Val")))</pre>
	 */
	public static <T> Matcher<T> allOf(Matcher<? super T> first, Matcher<? super T> second, Matcher<? super T> third) {
		return AllOf.allOf(first, second, third);
	}

	/**
	 * Creates a matcher that matches if the examined object matches <b>ALL</b> of the specified matchers.
	 * For example:
	 * <pre>assertThat("myValue", allOf(startsWith("my"), containsString("Val")))</pre>
	 */
	public static <T> Matcher<T> allOf(Matcher<? super T> first, Matcher<? super T> second, Matcher<? super T> third, Matcher<? super T> fourth) {
		return AllOf.allOf(first, second, third, fourth);
	}

	/**
	 * Creates a matcher that matches if the examined object matches <b>ALL</b> of the specified matchers.
	 * For example:
	 * <pre>assertThat("myValue", allOf(startsWith("my"), containsString("Val")))</pre>
	 */
	public static <T> Matcher<T> allOf(Matcher<? super T> first, Matcher<? super T> second, Matcher<? super T> third, Matcher<? super T> fourth, Matcher<? super T> fifth) {
		return AllOf.allOf(first, second, third, fourth, fifth);
	}

	/**
	 * Creates a matcher that matches if the examined object matches <b>ALL</b> of the specified matchers.
	 * For example:
	 * <pre>assertThat("myValue", allOf(startsWith("my"), containsString("Val")))</pre>
	 */
	public static <T> Matcher<T> allOf(Matcher<? super T> first, Matcher<? super T> second, Matcher<? super T> third, Matcher<? super T> fourth, Matcher<? super T> fifth, Matcher<? super T> sixth) {
		return AllOf.allOf(first, second, third, fourth, fifth, sixth);
	}

	/**
	 * Creates a matcher that matches if the examined object matches <b>ANY</b> of the specified matchers.
	 * For example:
	 * <pre>assertThat("myValue", anyOf(startsWith("foo"), containsString("Val")))</pre>
	 */
	public static <T> AnyOf<T> anyOf(Iterable<Matcher<? super T>> matchers) {
		return AnyOf.anyOf(matchers);
	}

	/**
	 * Creates a matcher that matches if the examined object matches <b>ANY</b> of the specified matchers.
	 * For example:
	 * <pre>assertThat("myValue", anyOf(startsWith("foo"), containsString("Val")))</pre>
	 */
	@SafeVarargs
	public static <T> AnyOf<T> anyOf(Matcher<? super T>... matchers) {
		return AnyOf.anyOf(matchers);
	}

	/**
	 * Creates a matcher that matches if the examined object matches <b>ANY</b> of the specified matchers.
	 * For example:
	 * <pre>assertThat("myValue", anyOf(startsWith("foo"), containsString("Val")))</pre>
	 */
	public static <T> AnyOf<T> anyOf(Matcher<? super T> first, Matcher<? super T> second) {
		return AnyOf.anyOf(first, second);
	}

	/**
	 * Creates a matcher that matches if the examined object matches <b>ANY</b> of the specified matchers.
	 * For example:
	 * <pre>assertThat("myValue", anyOf(startsWith("foo"), containsString("Val")))</pre>
	 */
	public static <T> AnyOf<T> anyOf(Matcher<? super T> first, Matcher<? super T> second, Matcher<? super T> third) {
		return AnyOf.anyOf(first, second, third);
	}

	/**
	 * Creates a matcher that matches if the examined object matches <b>ANY</b> of the specified matchers.
	 * For example:
	 * <pre>assertThat("myValue", anyOf(startsWith("foo"), containsString("Val")))</pre>
	 */
	public static <T> AnyOf<T> anyOf(Matcher<? super T> first, Matcher<? super T> second, Matcher<? super T> third, Matcher<? super T> fourth) {
		return AnyOf.anyOf(first, second, third, fourth);
	}

	/**
	 * Creates a matcher that matches if the examined object matches <b>ANY</b> of the specified matchers.
	 * For example:
	 * <pre>assertThat("myValue", anyOf(startsWith("foo"), containsString("Val")))</pre>
	 */
	public static <T> AnyOf<T> anyOf(Matcher<? super T> first, Matcher<? super T> second, Matcher<? super T> third, Matcher<? super T> fourth, Matcher<? super T> fifth) {
		return AnyOf.anyOf(first, second, third, fourth, fifth);
	}

	/**
	 * Creates a matcher that matches if the examined object matches <b>ANY</b> of the specified matchers.
	 * For example:
	 * <pre>assertThat("myValue", anyOf(startsWith("foo"), containsString("Val")))</pre>
	 */
	public static <T> AnyOf<T> anyOf(Matcher<? super T> first, Matcher<? super T> second, Matcher<? super T> third, Matcher<? super T> fourth, Matcher<? super T> fifth, Matcher<? super T> sixth) {
		return AnyOf.anyOf(first, second, third, fourth, fifth, sixth);
	}

	/**
	 * Creates a matcher that matches when both of the specified matchers match the examined object.
	 * For example:
	 * <pre>assertThat("fab", both(containsString("a")).and(containsString("b")))</pre>
	 */
	public static <LHS> CombinableMatcher.CombinableBothMatcher<LHS> both(Matcher<? super LHS> matcher) {
		return CombinableMatcher.both(matcher);
	}

	/**
	 * Creates a matcher that matches when either of the specified matchers match the examined object.
	 * For example:
	 * <pre>assertThat("fan", either(containsString("a")).or(containsString("b")))</pre>
	 */
	public static <LHS> CombinableMatcher.CombinableEitherMatcher<LHS> either(Matcher<? super LHS> matcher) {
		return CombinableMatcher.either(matcher);
	}

	/**
	 * Wraps an existing matcher, overriding its description with that specified.  All other functions are
	 * delegated to the decorated matcher, including its mismatch description.
	 * For example:
	 * <pre>describedAs("a big decimal equal to %0", equalTo(myBigDecimal), myBigDecimal.toPlainString())</pre>
	 *
	 * @param description the new description for the wrapped matcher
	 * @param matcher the matcher to wrap
	 * @param values optional values to insert into the tokenized description
	 */
	public static <T> Matcher<T> describedAs(String description, Matcher<T> matcher, Object... values) {
		return DescribedAs.describedAs(description, matcher, values);
	}

	/**
	 * Creates a matcher for {@link Iterable}s that only matches when a single pass over the
	 * examined {@link Iterable} yields items that are all matched by the specified
	 * <code>itemMatcher</code>.
	 * For example:
	 * <pre>assertThat(Arrays.asList("bar", "baz"), everyItem(startsWith("ba")))</pre>
	 *
	 * @param itemMatcher the matcher to apply to every item provided by the examined {@link Iterable}
	 */
	public static <U> Matcher<Iterable<? extends U>> everyItem(Matcher<U> itemMatcher) {
		return Every.everyItem(itemMatcher);
	}

	/**
	 * Decorates another matcher, preserving its behaviour, but allowing assertions to be more expressive.
	 * When given {@code null} as delegate, this method will return a null matcher.
	 *
	 * <h3>Examples</h3>
	 *
	 * <pre>{@code assertThat(instance, is(equalTo(anotherInstance)))}</pre>
	 * instead of:
	 * <pre>{@code assertThat(instance, equalTo(anotherInstance))}</pre>
	 *
	 * @since 1.0.0
	 */
	public static <T> Matcher<T> is(Matcher<T> delegate) {
		return CoreMatchers.is(delegate);
	}

	/**
	 * Creates a matcher that matches actual values for identity against a given expected value, including {@code null}.
	 * Such a matcher implements the "is" relation, corresponding to the "==" operator in Java.
	 * Beware that this matcher matches for identity <i>only</i>, it does <i>not</i> match for equality.
	 *
	 * <h3>Examples</h13
	 *
	 * <pre>{@code
	 * assertThat(value, is(null)); // Assert that a value is null.
	 * assertThat(intValue, is(2)); // Assert that an int value is 2.
	 * assertThat(value, is(expected)); // Assert that a value is ("==") an expected value.
	 * }</pre>
	 *
	 * @param expected an expected value to match against for identity, which may be {@code null}.
	 * @return a matcher that matches against a given expected value, not {@code null}.
	 * @since 1.0.0
	 */
	public static <T> Matcher<T> is(T expected) {
		return new SameAsMatcher<>("is ", expected);
	}

	/**
	 * Creates a matcher that matches for identity against a given value.
	 * Such a matcher implement the "same as" relating, corresponding to the "==" operator in Java.
	 *
	 * <h1>Examples</h1>
	 *
	 * <pre>{@code
	 * assertThat(value, is(sameAs(expected))); // Assert that a value is ("==") an expected value.
	 * }</pre>
	 *
	 * @param expected an expected value to match against for identity, which may be {@code null}.
	 * @return a matcher that matches against a given expected value, not {@code null}.
	 * @since 1.0.0
	 */
	public static <T> Matcher<T> sameAs(T expected) {
		return new SameAsMatcher<>("same as ", expected);
	}

	/**
	 * A shortcut to the frequently used <code>is(instanceOf(SomeClass.class))</code>.
	 * For example:
	 * <pre>assertThat(cheese, isA(Cheddar.class))</pre>
	 * instead of:
	 * <pre>assertThat(cheese, is(instanceOf(Cheddar.class)))</pre>
	 */
	public static <T> Matcher<T> isA(Class<?> type) {
		return CoreMatchers.isA(type);
	}

	/**
	 * Creates a matcher that always matches, regardless of the examined object.
	 */
	public static Matcher<Object> anything() {
		return IsAnything.anything();
	}

	/**
	 * Creates a matcher that always matches, regardless of the examined object, but describes
	 * itself with the specified {@link String}.
	 *
	 * @param description a meaningful {@link String} used when describing itself
	 */
	public static Matcher<Object> anything(String description) {
		return IsAnything.anything(description);
	}

	/**
	 * Creates a matcher for {@link Iterable}s that only matches when a single pass over the
	 * examined {@link Iterable} yields at least one item that is matched by the specified
	 * <code>itemMatcher</code>.  Whilst matching, the traversal of the examined {@link Iterable}
	 * will stop as soon as a matching item is found.
	 * For example:
	 * <pre>assertThat(Arrays.asList("foo", "bar"), hasItem(startsWith("ba")))</pre>
	 *
	 * @param itemMatcher the matcher to apply to items provided by the examined {@link Iterable}
	 */
	public static <T> Matcher<Iterable<? super T>> hasItem(Matcher<? super T> itemMatcher) {
		return IsIterableContaining.hasItem(itemMatcher);
	}

	/**
	 * Creates a matcher for {@link Iterable}s that only matches when a single pass over the
	 * examined {@link Iterable} yields at least one item that is equal to the specified
	 * <code>item</code>.  Whilst matching, the traversal of the examined {@link Iterable}
	 * will stop as soon as a matching item is found.
	 * For example:
	 * <pre>assertThat(Arrays.asList("foo", "bar"), hasItem("bar"))</pre>
	 *
	 * @param item the item to compare against the items provided by the examined {@link Iterable}
	 */
	public static <T> Matcher<Iterable<? super T>> hasItem(T item) {
		return IsIterableContaining.hasItem(item);
	}

	/**
	 * Creates a matcher for {@link Iterable}s that matches when consecutive passes over the
	 * examined {@link Iterable} yield at least one item that is matched by the corresponding
	 * matcher from the specified <code>itemMatchers</code>.  Whilst matching, each traversal of
	 * the examined {@link Iterable} will stop as soon as a matching item is found.
	 * For example:
	 * <pre>assertThat(Arrays.asList("foo", "bar", "baz"), hasItems(endsWith("z"), endsWith("o")))</pre>
	 *
	 * @param itemMatchers the matchers to apply to items provided by the examined {@link Iterable}
	 */
	@SafeVarargs
	public static <T> Matcher<Iterable<T>> hasItems(Matcher<? super T>... itemMatchers) {
		return IsIterableContaining.hasItems(itemMatchers);
	}

	/**
	 * Creates a matcher for {@link Iterable}s that matches when consecutive passes over the
	 * examined {@link Iterable} yield at least one item that is equal to the corresponding
	 * item from the specified <code>items</code>.  Whilst matching, each traversal of the
	 * examined {@link Iterable} will stop as soon as a matching item is found.
	 * For example:
	 * <pre>assertThat(Arrays.asList("foo", "bar", "baz"), hasItems("baz", "foo"))</pre>
	 *
	 * @param items the items to compare against the items provided by the examined {@link Iterable}
	 */
	@SafeVarargs
	public static <T> Matcher<Iterable<T>> hasItems(T... items) {
		return IsIterableContaining.hasItems(items);
	}

	/**
	 * Creates a matcher that matches when the examined object is logically equal to the specified
	 * <code>operand</code>, as determined by calling the {@link Object#equals} method on
	 * the <b>examined</b> object.
	 *
	 * <p>If the specified operand is <code>null</code> then the created matcher will only match if
	 * the examined object's <code>equals</code> method returns <code>true</code> when passed a
	 * <code>null</code> (which would be a violation of the <code>equals</code> contract), unless the
	 * examined object itself is <code>null</code>, in which case the matcher will return a positive
	 * match.</p>
	 *
	 * <p>The created matcher provides a special behaviour when examining <code>Array</code>s, whereby
	 * it will match if both the operand and the examined object are arrays of the same length and
	 * contain items that are equal to each other (according to the above rules) <b>in the same
	 * indexes</b>.</p>
	 * For example:
	 * <pre>
	 * assertThat("foo", equalTo("foo"));
	 * assertThat(new String[] {"foo", "bar"}, equalTo(new String[] {"foo", "bar"}));
	 * </pre>
	 */
	public static <T> Matcher<T> equalTo(T operand) {
		return EqualsMatcher.equalTo(operand);
	}

	/**
	 * Creates an {@link EqualsMatcher} matcher that does not enforce the values being
	 * compared to be of the same static type.
	 */
	public static Matcher<Object> equalToObject(Object operand) {
		return EqualsMatcher.equalToObject(operand);
	}

	/**
	 * Creates a matcher that matches when the examined object is an instance of the specified <code>type</code>,
	 * as determined by calling the {@link Class#isInstance(Object)} method on that type, passing the
	 * the examined object.
	 *
	 * <p>The created matcher forces a relationship between specified type and the examined object, and should be
	 * used when it is necessary to make generics conform, for example in the JMock clause
	 * <code>with(any(Thing.class))</code></p>
	 * For example:
	 * <pre>assertThat(new Canoe(), instanceOf(Canoe.class));</pre>
	 */
	public static <T> Matcher<T> any(Class<T> type) {
		return IsInstanceOf.any(type);
	}

	/**
	 * Creates a matcher that matches when the examined object is an instance of the specified <code>type</code>,
	 * as determined by calling the {@link Class#isInstance(Object)} method on that type, passing the
	 * the examined object.
	 *
	 * <p>The created matcher assumes no relationship between specified type and the examined object.</p>
	 * For example:
	 * <pre>assertThat(new Canoe(), instanceOf(Paddlable.class));</pre>
	 */
	public static <T> Matcher<T> instanceOf(Class<?> type) {
		return IsInstanceOf.instanceOf(type);
	}

	/**
	 * Creates a matcher that matches if the examined {@link String} contains the specified
	 * {@link String} anywhere.
	 * For example:
	 * <pre>assertThat("myStringOfNote", containsString("ring"))</pre>
	 *
	 * @param substring the substring that the returned matcher will expect to find within any examined string
	 */
	public static Matcher<String> containsString(String substring) {
		return StringContains.containsString(substring);
	}

	/**
	 * Creates a matcher that matches if the examined {@link String} contains the specified
	 * {@link String} anywhere, ignoring case.
	 * For example:
	 * <pre>assertThat("myStringOfNote", containsStringIgnoringCase("Ring"))</pre>
	 *
	 * @param substring the substring that the returned matcher will expect to find within any examined string
	 */
	public static Matcher<String> containsStringIgnoringCase(String substring) {
		return StringContains.containsStringIgnoringCase(substring);
	}

	/**
	 * <p>
	 * Creates a matcher that matches if the examined {@link String} starts with the specified
	 * {@link String}.
	 * </p>
	 * For example:
	 * <pre>assertThat("myStringOfNote", startsWith("my"))</pre>
	 *
	 * @param prefix the substring that the returned matcher will expect at the start of any examined string
	 */
	public static Matcher<String> startsWith(String prefix) {
		return StringStartsWith.startsWith(prefix);
	}

	/**
	 * <p>
	 * Creates a matcher that matches if the examined {@link String} starts with the specified
	 * {@link String}, ignoring case
	 * </p>
	 * For example:
	 * <pre>assertThat("myStringOfNote", startsWithIgnoringCase("My"))</pre>
	 *
	 * @param prefix the substring that the returned matcher will expect at the start of any examined string
	 */
	public static Matcher<String> startsWithIgnoringCase(String prefix) {
		return StringStartsWith.startsWithIgnoringCase(prefix);
	}

	/**
	 * Creates a matcher that matches if the examined {@link String} ends with the specified
	 * {@link String}.
	 * For example:
	 * <pre>assertThat("myStringOfNote", endsWith("Note"))</pre>
	 *
	 * @param suffix the substring that the returned matcher will expect at the end of any examined string
	 */
	public static Matcher<String> endsWith(String suffix) {
		return StringEndsWith.endsWith(suffix);
	}

	/**
	 * Creates a matcher that matches if the examined {@link String} ends with the specified
	 * {@link String}, ignoring case.
	 * For example:
	 * <pre>assertThat("myStringOfNote", endsWithIgnoringCase("note"))</pre>
	 *
	 * @param suffix the substring that the returned matcher will expect at the end of any examined string
	 */
	public static Matcher<String> endsWithIgnoringCase(String suffix) {
		return StringEndsWith.endsWithIgnoringCase(suffix);
	}

	/**
	 * Validate a string with a {@link Pattern}.
	 *
	 * <pre>
	 * assertThat(&quot;abc&quot;, matchesRegex(Pattern.compile(&quot;&circ;[a-z]$&quot;));
	 * </pre>
	 *
	 * @param pattern the pattern to be used.
	 * @return The matcher.
	 */
	public static Matcher<String> matchesRegex(Pattern pattern) {
		return StringRegularExpression.matchesRegex(pattern);
	}

	/**
	 * Validate a string with a regex.
	 *
	 * <pre>
	 * assertThat(&quot;abc&quot;, matchesRegex(&quot;&circ;[a-z]+$&quot;));
	 * </pre>
	 *
	 * @param regex The regex to be used for the validation.
	 * @return The matcher.
	 */
	public static Matcher<String> matchesRegex(String regex) {
		return StringRegularExpression.matchesRegex(Pattern.compile(regex));
	}

	/**
	 * Creates a matcher of {@link Double}s that matches when an examined double is equal
	 * to the specified <code>operand</code>, within a range of +/- <code>error</code>.
	 * For example:
	 * <pre>assertThat(1.03, is(closeTo(1.0, 0.03)))</pre>
	 *
	 * @param operand the expected value of matching doubles
	 * @param error the delta (+/-) within which matches will be allowed
	 */
	public static Matcher<Double> closeTo(double operand, double error) {
		return IsCloseTo.closeTo(operand, error);
	}

	/**
	 * Creates a matcher of {@link BigDecimal}s that matches when an examined BigDecimal is equal
	 * to the specified <code>operand</code>, within a range of +/- <code>error</code>. The comparison for equality
	 * is done by BigDecimals {@link BigDecimal#compareTo(BigDecimal)} method.
	 * For example:
	 * <pre>assertThat(new BigDecimal("1.03"), is(closeTo(new BigDecimal("1.0"), new BigDecimal("0.03"))))</pre>
	 *
	 * @param operand the expected value of matching BigDecimals
	 * @param error the delta (+/-) within which matches will be allowed
	 */
	public static Matcher<BigDecimal> closeTo(BigDecimal operand, BigDecimal error) {
		return BigDecimalCloseTo.closeTo(operand, error);
	}

	/**
	 * Creates a matcher of {@link String} that matches when the examined string is equal to
	 * the specified expectedString, ignoring case.
	 * For example:
	 * <pre>assertThat("Foo", equalToIgnoringCase("FOO"))</pre>
	 *
	 * @param expectedString the expected value of matched strings
	 */
	public static Matcher<String> equalToIgnoringCase(String expectedString) {
		return IsEqualIgnoringCase.equalToIgnoringCase(expectedString);
	}

	/**
	 * @param expectedString the expected value of matched strings
	 * @deprecated {@link #equalToCompressingWhiteSpace(String)}
	 */
	public static Matcher<String> equalToIgnoringWhiteSpace(String expectedString) {
		return equalToCompressingWhiteSpace(expectedString);
	}

	/**
	 * Creates a matcher of {@link String} that matches when the examined string is equal to
	 * the specified expectedString, when whitespace differences are (mostly) ignored.  To be
	 * exact, the following whitespace rules are applied:
	 * <ul>
	 *   <li>all leading and trailing whitespace of both the expectedString and the examined string are ignored</li>
	 *   <li>any remaining whitespace, appearing within either string, is collapsed to a single space before comparison</li>
	 * </ul>
	 * For example:
	 * <pre>assertThat("   my\tfoo  bar ", equalToIgnoringWhiteSpace(" my  foo bar"))</pre>
	 *
	 * @param expectedString the expected value of matched strings
	 */
	public static Matcher<String> equalToCompressingWhiteSpace(String expectedString) {
		return IsEqualCompressingWhiteSpace.equalToCompressingWhiteSpace(expectedString);
	}

	/**
	 * Creates a matcher of {@link String} that matches when the examined string is <code>null</code>, or
	 * has zero length.
	 * For example:
	 * <pre>assertThat(((String)null), is(emptyOrNullString()))</pre>
	 */
	public static Matcher<String> emptyOrNullString() {
		return IsEmptyString.emptyOrNullString();
	}

	/**
	 * Creates a matcher of {@link String} that matches when the examined string has zero length.
	 * For example:
	 * <pre>assertThat("", is(emptyString()))</pre>
	 */
	public static Matcher<String> emptyString() {
		return IsEmptyString.emptyString();
	}

	/**
	 * Creates a matcher of {@link String} that matches when the examined string is <code>null</code>, or
	 * has zero length.
	 * For example:
	 * <pre>assertThat(((String)null), isEmptyOrNullString())</pre>
	 *
	 * @deprecated use is(emptyOrNullString()) instead
	 */
	@SuppressWarnings("deprecation")
	public static Matcher<String> isEmptyOrNullString() {
		return IsEmptyString.isEmptyOrNullString();
	}

	/**
	 * Creates a matcher of {@link String} that matches when the examined string has zero length.
	 * For example:
	 * <pre>assertThat("", isEmptyString())</pre>
	 *
	 * @deprecated use is(emptyString()) instead
	 */
	@SuppressWarnings("deprecation")
	public static Matcher<String> isEmptyString() {
		return IsEmptyString.isEmptyString();
	}

	/**
	 * Creates a matcher of {@link String} that matches when the examined string is <code>null</code>, or
	 * contains zero or more whitespace characters and nothing else.
	 * For example:
	 * <pre>assertThat(((String)null), is(blankOrNullString()))</pre>
	 */
	public static Matcher<String> blankOrNullString() {
		return IsBlankString.blankOrNullString();
	}

	/**
	 * Creates a matcher of {@link String} that matches when the examined string contains
	 * zero or more whitespace characters and nothing else.
	 * For example:
	 * <pre>assertThat("  ", is(blankString()))</pre>
	 */
	public static Matcher<String> blankString() {
		return IsBlankString.blankString();
	}

	/**
	 * Creates a matcher of {@link String} that matches when the examined string
	 * exactly matches the given {@link Pattern}.
	 */
	public static Matcher<String> matchesPattern(Pattern pattern) {
		return MatchesPattern.matchesPattern(pattern);
	}

	/**
	 * Creates a matcher of {@link String} that matches when the examined string
	 * exactly matches the given regular expression, treated as a {@link Pattern}.
	 */
	public static Matcher<String> matchesPattern(String regex) {
		return MatchesPattern.matchesPattern(regex);
	}

	/**
	 * Creates a matcher of {@link String} that matches when the examined string contains all of
	 * the specified substrings, considering the order of their appearance.
	 * For example:
	 * <pre>assertThat("myfoobarbaz", stringContainsInOrder(Arrays.asList("bar", "foo")))</pre>
	 * fails as "foo" occurs before "bar" in the string "myfoobarbaz"
	 *
	 * @param substrings the substrings that must be contained within matching strings
	 */
	public static Matcher<String> stringContainsInOrder(Iterable<String> substrings) {
		return StringContainsInOrder.stringContainsInOrder(substrings);
	}

	/**
	 * Creates a matcher of {@link String} that matches when the examined string contains all of
	 * the specified substrings, considering the order of their appearance.
	 * For example:
	 * <pre>assertThat("myfoobarbaz", stringContainsInOrder("bar", "foo"))</pre>
	 * fails as "foo" occurs before "bar" in the string "myfoobarbaz"
	 *
	 * @param substrings the substrings that must be contained within matching strings
	 */
	public static Matcher<String> stringContainsInOrder(String... substrings) {
		return StringContainsInOrder.stringContainsInOrder(substrings);
	}

	/**
	 * Creates a matcher of {@link CharSequence} that matches when a char sequence has the length
	 * of the specified <code>argument</code>.
	 * For example:
	 *
	 * <pre>
	 * assertThat("text", length(4))
	 * </pre>
	 *
	 * @param length the expected length of the string
	 */
	public static Matcher<CharSequence> hasLength(int length) {
		return CharSequenceLength.hasLength(length);
	}

	/**
	 * Creates a matcher that matches any examined object whose <code>toString</code> method
	 * returns a value that satisfies the specified matcher.
	 * For example:
	 * <pre>assertThat(true, hasToString(equalTo("TRUE")))</pre>
	 *
	 * @param toStringMatcher the matcher used to verify the toString result
	 */
	public static <T> Matcher<T> hasToString(Matcher<? super String> toStringMatcher) {
		return HasToString.hasToString(toStringMatcher);
	}

	/**
	 * Creates a matcher that matches any examined object whose <code>toString</code> method
	 * returns a value equalTo the specified string.
	 * For example:
	 * <pre>assertThat(true, hasToString("TRUE"))</pre>
	 *
	 * @param expectedToString the expected toString result
	 */
	public static <T> Matcher<T> hasToString(String expectedToString) {
		return HasToString.hasToString(expectedToString);
	}

	/**
	 * Creates a matcher of {@link Class} that matches when the specified baseType is
	 * assignable from the examined class.
	 * For example:
	 * <pre>assertThat(Integer.class, typeCompatibleWith(Number.class))</pre>
	 *
	 * @param baseType the base class to examine classes against
	 */
	public static <T> Matcher<Class<?>> typeCompatibleWith(Class<T> baseType) {
		return IsCompatibleType.typeCompatibleWith(baseType);
	}

	/**
	 * Creates a matcher of {@link EventObject} that matches any object
	 * derived from <var>eventClass</var> announced by <var>source</var>.
	 * For example:
	 * <pre>assertThat(myEvent, is(eventFrom(PropertyChangeEvent.class, myBean)))</pre>
	 *
	 * @param eventClass the class of the event to match on
	 * @param source the source of the event
	 */
	public static Matcher<EventObject> eventFrom(Class<? extends EventObject> eventClass, Object source) {
		return IsEventFrom.eventFrom(eventClass, source);
	}

	/**
	 * Creates a matcher of {@link EventObject} that matches any EventObject
	 * announced by <var>source</var>.
	 * For example:
	 * <pre>assertThat(myEvent, is(eventFrom(myBean)))</pre>
	 *
	 * @param source the source of the event
	 */
	public static Matcher<EventObject> eventFrom(Object source) {
		return IsEventFrom.eventFrom(source);
	}

	/**
	 * @see CoreMatchers#not(Matcher)
	 * @since 1.0.0
	 */
	public static <T> Matcher<T> not(Matcher<T> delegate) {
		return CoreMatchers.not(delegate);
	}

	public static <T> Matcher<T> not(T expected) {
		return CoreMatchers.not(expected);
	}

	/**
	 * @see NumberMatchers#notANumber()
	 * @since 1.0.0
	 */
	public static Matcher notANumber() {
		return NumberMatchers.notANumber();
	}
}
