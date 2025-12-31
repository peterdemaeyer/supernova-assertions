package internal.su.pernova.assertions.matchers;

import static org.junit.jupiter.api.Assertions.assertTrue;

import static su.pernova.assertions.AssertionTestUtils.assertThrowsAssertionErrorWithMessage;
import static su.pernova.assertions.Assertions.assertThat;
import static su.pernova.assertions.Matchers.allOf;
import static su.pernova.assertions.Matchers.equalTo;
import static su.pernova.assertions.Matchers.instanceOf;
import static su.pernova.assertions.Matchers.is;

import java.util.Date;
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

class AllOfTest implements MultiMatcherContractTest {

	@Override
	public Matcher getInstance() {
		return is(allOf(new Object(), new Object(), new Object()));
	}

	@Override
	public Matcher getInstance(Object... expectedValues) {
		return allOf(expectedValues);
	}

	@Override
	public Matcher getInstance(double... expectedValues) {
		return allOf(expectedValues);
	}

	@Override
	public Matcher getInstance(float... expectedValues) {
		return allOf(expectedValues);
	}

	@Override
	public Matcher getInstance(long... expectedValues) {
		return allOf(expectedValues);
	}

	@Override
	public Matcher getInstance(int... expectedValues) {
		return allOf(expectedValues);
	}

	@Override
	public Matcher getInstance(short... expectedValues) {
		return allOf(expectedValues);
	}

	@Override
	public Matcher getInstance(byte... expectedValues) {
		return allOf(expectedValues);
	}

	@Override
	public Matcher getInstance(char... expectedValues) {
		return allOf(expectedValues);
	}

	@Override
	public Matcher getInstance(boolean... expectedValues) {
		return allOf(expectedValues);
	}

	@ParameterizedTest
	@ArgumentsSource(EmptyArrayArgumentsProvider.class)
	void anythingMatchesEmptyArray(Matcher incompleteMatcher) {
		final Matcher matcher = is(incompleteMatcher);
		assertTrue(matcher.match(null));
		assertTrue(matcher.match(new Object()));
		assertTrue(matcher.match(0d));
		assertTrue(matcher.match(0f));
		assertTrue(matcher.match(0L));
		assertTrue(matcher.match(0));
		assertTrue(matcher.match((short) 0));
		assertTrue(matcher.match((byte) 0));
		assertTrue(matcher.match('0'));
		assertTrue(matcher.match(false));
	}

	private static class EmptyArrayArgumentsProvider implements ArgumentsProvider {

		@Override
		public @NonNull Stream<? extends Arguments> provideArguments(@NonNull ParameterDeclarations parameters, @NonNull ExtensionContext context) throws Exception {
			return Stream.of(
					Arguments.of(Named.of("Object[]", allOf(new Object[0]))),
					Arguments.of(Named.of("double[]", allOf(new double[0]))),
					Arguments.of(Named.of("float[]", allOf(new float[0]))),
					Arguments.of(Named.of("long[]", allOf(new long[0]))),
					Arguments.of(Named.of("int[]", allOf(new int[0]))),
					Arguments.of(Named.of("short[]", allOf(new short[0]))),
					Arguments.of(Named.of("byte[]", allOf(new byte[0]))),
					Arguments.of(Named.of("char[]", allOf(new char[0]))),
					Arguments.of(Named.of("boolean[]", allOf(new boolean[0])))
			);
		}
	}

	@Test
	void allOfOneObject() {
		final String object = "object";
		final String equalObject = new String("object");
		assertThat(object, is(allOf(object)));
		assertThrowsAssertionErrorWithMessage(
				() -> assertThat(object, is(allOf(equalObject))),
				"expected that subject is all of: [\"object\"]",
				"but was: \"object\""
		);
	}

	@Test
	void allOfOneDouble() {
		assertThat(1d, is(allOf(new double[] { 1d })));
		assertThrowsAssertionErrorWithMessage(
				() -> assertThat(1d, is(allOf(new double[] { 2d }))),
				"expected that subject is all of: [2.0]",
				"but was: 1.0"
		);
	}

	@Test
	void instanceOfAllOfManyClasses() {
		assertThat("actual", is(instanceOf(allOf(Object.class, CharSequence.class, String.class))));
		assertThrowsAssertionErrorWithMessage(
				() -> assertThat("actual", is(instanceOf(allOf(String.class, Date.class)))),
				"expected that subject is instance of all of: [java.lang.String, java.util.Date]",
				"but was: \"actual\""
		);
	}

	@Test
	void allOfManyObjects() {
		final String object = "object";
		final String equalObject = new String(object);
		assertThat(object, is(equalTo(allOf(object, equalObject))));
		assertThrowsAssertionErrorWithMessage(
				() -> assertThat(object, is(allOf(object, equalObject))),
				"expected that subject is all of: [\"object\", \"object\"]",
				"but was: \"object\""
		);
	}

	@Test
	void allOfManyDoubles() {
		assertThat(0d, is(allOf(new double[] { 0d, -0d })));
	}

	@Test
	void allOfIdenticalPrimitives() {
		assertThat(0d, is(allOf(new double[] { 0d, -0d })));
		assertThat(0f, is(allOf(new float[] { 0f, -0f })));
		assertThat(0L, is(allOf(new long[] { 0L })));
		assertThat(0, is(allOf(new int[] { 0 })));
		assertThat((short) 0, is(allOf(new short[] { 0 })));
		assertThat((byte) 0, is(allOf(new byte[] { 0 })));
		assertThat(false, is(allOf(new boolean[] { false })));
		assertThat('h', is(allOf(new char[] { 'h' })));
	}

	@Test
	void allOfEqualPrimitives() {
		// Pick the NaN example because it only works with equality, not with identity!
		assertThat(Double.NaN, is(equalTo(allOf(new double[] { Double.NaN }))));
		assertThat(Float.NaN, is(equalTo(allOf(new float[] { Float.NaN }))));
		assertThat(0L, is(equalTo(allOf(new long[] { 0L }))));
		assertThat(0, is(equalTo(allOf(new int[] { 0 }))));
		assertThat((short) 0, is(equalTo(allOf(new short[] { 0 }))));
		assertThat((byte) 0, is(equalTo(allOf(new byte[] { 0 }))));
		assertThat(false, is(equalTo(allOf(new boolean[] { false }))));
		assertThat('h', is(equalTo(allOf(new char[] { 'h' }))));
	}

	@Test
	void allOfOr() {
		final Matcher matcher = is(allOf(new int[] { 0, 0, 0 }).or(allOf(new int[] { 0, 0 })));
		matcher.match(0);
	}
}
