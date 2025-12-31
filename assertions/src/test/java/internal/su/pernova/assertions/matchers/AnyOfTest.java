package internal.su.pernova.assertions.matchers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static su.pernova.assertions.AssertionTestUtils.assertThrowsAssertionErrorWithMessage;
import static su.pernova.assertions.Assertions.assertThat;
import static su.pernova.assertions.Matchers.anyOf;
import static su.pernova.assertions.Matchers.equalTo;
import static su.pernova.assertions.Matchers.instanceOf;
import static su.pernova.assertions.Matchers.is;
import static su.pernova.assertions.Matchers.sameAs;

import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import org.jspecify.annotations.NonNull;
import org.junit.jupiter.api.Named;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.support.ParameterDeclarations;

import su.pernova.assertions.Matcher;

class AnyOfTest implements MultiMatcherContractTest {

	@Override
	public Matcher getInstance() {
		return is(anyOf(new Object[0]));
	}

	@Override
	public Matcher getIncompleteInstance(Object... expectedValues) {
		return anyOf(expectedValues);
	}

	@Override
	public Matcher getIncompleteInstance(double... expectedValues) {
		return anyOf(expectedValues);
	}

	@Override
	public Matcher getIncompleteInstance(float... expectedValues) {
		return anyOf(expectedValues);
	}

	@Override
	public Matcher getIncompleteInstance(long... expectedValues) {
		return anyOf(expectedValues);
	}

	@Override
	public Matcher getIncompleteInstance(int... expectedValues) {
		return anyOf(expectedValues);
	}

	@Override
	public Matcher getIncompleteInstance(short... expectedValues) {
		return anyOf(expectedValues);
	}

	@Override
	public Matcher getIncompleteInstance(byte... expectedValues) {
		return anyOf(expectedValues);
	}

	@Override
	public Matcher getIncompleteInstance(char... expectedValues) {
		return anyOf(expectedValues);
	}

	@Override
	public Matcher getIncompleteInstance(boolean... expectedValues) {
		return anyOf(expectedValues);
	}

	@ParameterizedTest
	@ArgumentsSource(EmptyArrayArgumentsProvider.class)
	void nothingMatchesEmptyArray(Matcher incompleteMatcher) {
		final Matcher matcher = is(incompleteMatcher);
		assertFalse(matcher.match(null));
		assertFalse(matcher.match(new Object()));
		assertFalse(matcher.match(0d));
		assertFalse(matcher.match(0f));
		assertFalse(matcher.match(0L));
		assertFalse(matcher.match(0));
		assertFalse(matcher.match((short) 0));
		assertFalse(matcher.match((byte) 0));
		assertFalse(matcher.match('0'));
		assertFalse(matcher.match(false));
	}

	private static class EmptyArrayArgumentsProvider implements ArgumentsProvider {

		@Override
		public @NonNull Stream<? extends Arguments> provideArguments(@NonNull ParameterDeclarations parameters, @NonNull ExtensionContext context) throws Exception {
			return Stream.of(
					Arguments.of(Named.of("Object[]", anyOf(new Object[0]))),
					Arguments.of(Named.of("double[]", anyOf(new double[0]))),
					Arguments.of(Named.of("float[]", anyOf(new float[0]))),
					Arguments.of(Named.of("long[]", anyOf(new long[0]))),
					Arguments.of(Named.of("int[]", anyOf(new int[0]))),
					Arguments.of(Named.of("short[]", anyOf(new short[0]))),
					Arguments.of(Named.of("byte[]", anyOf(new byte[0]))),
					Arguments.of(Named.of("char[]", anyOf(new char[0]))),
					Arguments.of(Named.of("boolean[]", anyOf(new boolean[0])))
			);
		}
	}

	@Test
	void anyOfEmptyObjects() {
		assertThrowsAssertionErrorWithMessage(
				() -> assertThat(this, is(anyOf(new Object[0]))),
				"expected that subject is any of: []",
				"but was: \"" + this + "\""
		);
	}

	@Test
	void anyOfEmptyDoubles() {
		assertThrowsAssertionErrorWithMessage(
				() -> assertThat(1d, is(anyOf(new double[0]))),
				"expected that subject is any of: []",
				"but was: 1.0"
		);
	}

	@Test
	void anyOfOneObject() {
		final String object = "object";
		final String equalObject = new String(object);
		assertThat(object, is(anyOf(object)));
		assertThrowsAssertionErrorWithMessage(
				() -> assertThat(object, is(anyOf(equalObject))),
				"expected that subject is any of: [\"object\"]",
				"but was: \"object\""
		);
	}

	@Test
	void anyOfOneDouble() {
		assertThat(1d, is(anyOf(new double[] { 1d })));
		assertThrowsAssertionErrorWithMessage(
				() -> assertThat(1d, is(anyOf(new double[] { 2d }))),
				"expected that subject is any of: [2.0]",
				"but was: 1.0"
		);
	}

	@Test
	void anyOfManyObjects() {
		final String object1 = "object1";
		final String object2 = "object2";
		final String object3 = "object3";
		assertThat(object1, is(anyOf(object1, object2)));
		assertThrowsAssertionErrorWithMessage(
				() -> assertThat(object1, is(anyOf(object3, object2))),
				"expected that subject is any of: [\"object3\", \"object2\"]",
				"but was: \"object1\""
		);
	}

	@Test
	void anyOfManyDoubles() {
		assertThat(1d, is(anyOf(new double[] { 1d, 2d, 3d })));
	}

	@Test
	void anyOfWithIdentityContext() {
		final Object object1 = "object1";
		final Object equalObject1 = new String("object1");
		final Object object2 = "object2";
		assertThat(object2, is(anyOf(object1, equalObject1, object2)));
		assertThrowsAssertionErrorWithMessage(
				() -> assertThat(object1, is(anyOf(equalObject1, object2))),
				"expected that subject is any of: [\"object1\", \"object2\"]\nbut was: \"object1\""
		);
	}

	@Test
	void anyOfWithEqualityContext() {
		final Object object1 = "object1";
		final Object object2 = "object2";
		final Object object3 = "object3";
		final Object equalObject3 = new String("object3");
		assertThat(object3, is(equalTo(anyOf(object1, object2, equalObject3))));
		assertThrowsAssertionErrorWithMessage(
				() -> assertThat(object2, is(equalTo(anyOf(object1, object3)))),
				"expected that subject is equal to any of: [\"object1\", \"object3\"]",
				"but was: \"object2\""
		);
	}

	@Test
	void instanceOfAnyOfManyClasses() {
		assertThat("actual", is(instanceOf(anyOf(List.class, Date.class, String.class))));
		assertThrowsAssertionErrorWithMessage(
				() -> assertThat("actual", is(instanceOf(anyOf(List.class, Date.class)))),
				"expected that subject is instance of any of: [java.util.List, java.util.Date]",
				"but was: \"actual\""
		);
	}

	@Test
	void sameAsAnyOfOneObject() {
		final String object = "object";
		final String equalObject = new String(object);
		final Matcher matcher = is(sameAs(anyOf(object)));
		assertEquals("is{sameAs{anyOf{s̶a̶m̶e̶A̶s̶(object)}}}", matcher.toString());
		assertThat(object, matcher);
		assertThrowsAssertionErrorWithMessage(
				() -> assertThat(object, is(sameAs(anyOf(equalObject)))),
				"expected that subject is same as any of: [\"object\"]",
				"but was: \"object\""
		);
	}

	@Test
	void isAnyOfPrimitiveDoubleValues() {
		final Matcher matcher = is(anyOf(new double[] { 0d, 1d }));
		assertTrue(matcher.match(0d));
		// Expect that this matcher behaves as == on primitive doubles.
		assertTrue(matcher.match(-0d));
		assertTrue(matcher.match(1d));
		assertTrue(matcher.match(1));
		assertFalse(matcher.match(-1d));
	}

	@Test
	void isAnyOfPrimitiveFloatValues() {
		final Matcher matcher = is(anyOf(new float[] { 0f, 1f}));
		assertTrue(matcher.match(0f));
		assertTrue(matcher.match(-0f));
		assertTrue(matcher.match(1f));
		assertFalse(matcher.match(-1f));
	}

	@Test
	void isAnyOfPrimitiveLongValues() {
		final Matcher matcher = is(anyOf(new long[] { 0L, 1L }));
		assertTrue(matcher.match(0));
		assertTrue(matcher.match(1d));
		assertFalse(matcher.match(-1L));
		assertFalse(matcher.match(null));
	}

	@Test
	void isAnyOfPrimitiveIntValues() {
		final Matcher matcher = is(anyOf(new int[] { 0, 1 }));
		assertEquals("is{anyOf{i̶s̶I̶n̶t̶(0)}{i̶s̶I̶n̶t̶(1)}}", matcher.toString());
		assertTrue(matcher.match(-0d));
		assertTrue(matcher.match(1));
		assertFalse(matcher.match(-1));
	}

	@Test
	void isAnyOfObjectDoubleValues() {
		final Matcher matcher = is(anyOf(new Object[] { 0d, 1d }));
		// Expectation behaves as == on objects
		assertFalse(matcher.match(-0d));
		assertFalse(matcher.match(0));
	}

	@Test
	void anyOfIdenticalPrimitives() {
		assertThat(0d, is(anyOf(new double[] { 0d, -0d })));
		assertThat(0f, is(anyOf(new float[] { 0f, -0f })));
		assertThat(0L, is(anyOf(new long[] { 0L })));
		assertThat(0, is(anyOf(new int[] { 0 })));
		assertThat((short) 0, is(anyOf(new short[] { 0 })));
		assertThat((byte) 0, is(anyOf(new byte[] { 0 })));
		assertThat(false, is(anyOf(new boolean[] { false })));
		assertThat('h', is(anyOf(new char[] { 'h' })));
	}

	@Test
	void anyOfEqualPrimitives() {
		// Pick the NaN example because it only works with equality, not with identity!
		assertThat(Double.NaN, is(equalTo(anyOf(new double[]{ Double.NaN }))));
		assertThat(Float.NaN, is(equalTo(anyOf(new float[] { Float.NaN }))));
		assertThat(0L, is(equalTo(anyOf(new long[] { 0L }))));
		assertThat(0, is(equalTo(anyOf(new int[] { 0 }))));
		assertThat((short) 0, is(equalTo(anyOf(new short[] { 0 }))));
		assertThat((byte) 0, is(equalTo(anyOf(new byte[] { 0 }))));
		assertThat(false, is(equalTo(anyOf(new boolean[] { false }))));
		assertThat('h', is(equalTo(anyOf(new char[] { 'h' }))));
	}
}
