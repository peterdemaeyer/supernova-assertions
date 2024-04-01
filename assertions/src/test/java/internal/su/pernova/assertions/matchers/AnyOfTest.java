package internal.su.pernova.assertions.matchers;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static su.pernova.assertions.AssertionTestUtils.assertThrowsAssertionErrorWithMessage;
import static su.pernova.assertions.Assertions.assertThat;
import static su.pernova.assertions.Matchers.allOf;
import static su.pernova.assertions.Matchers.anyOf;
import static su.pernova.assertions.Matchers.equalTo;
import static su.pernova.assertions.Matchers.instanceOf;
import static su.pernova.assertions.Matchers.is;
import static su.pernova.assertions.Matchers.sameAs;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;

class AnyOfTest {

	/**
	 * Matchers have their own context, therefore, "any of matchers" is a legal state of the assertion builder,
	 * even though it's grammatically incorrect and therefore not the preferred syntax.
	 */
	@Test
	void anyOfMatchersWithoutContext() {
		assertDoesNotThrow(() -> assertThat(null, anyOf(is(null))));
	}

	@Test
	void anyOfManyMatchers() {
		final Object object = "object";
		assertThat(object, is(anyOf(
				instanceOf(Date.class),
				equalTo("object"),
				sameAs(object)
		)));
		assertThrowsAssertionErrorWithMessage(
				() -> assertThat(object, is(anyOf(
						instanceOf(Date.class),
						equalTo("another object"),
						sameAs(null)
				))),
				"expected that subject is any of:",
				"\tinstance of: java.util.Date",
				"\tequal to: \"another object\"",
				"\tsame as: null",
				"but was: \"object\""
		);
	}

	/**
	 * Objects and primitives don't have context of their own.
	 * Context needs to be provided by another context-providing matcher such as "is", "is equal to" or
	 * "is instance of".
	 * Without context, "any of objects" is an illegal state of the assertion builder.
	 */
	@Test
	void anyOfObjectsWithoutContext() {
		assertEquals("no context",
				assertThrows(IllegalStateException.class, () -> assertThat(null, anyOf(new Object[] { null })))
						.getMessage());
	}

	@Test
	void anyOfNullObjects() {
		assertThrows(NullPointerException.class, () -> assertThat(this, is(anyOf((Object[]) null))));
	}

	@Test
	void anyOfNullDoubles() {
		assertThrows(NullPointerException.class, () -> assertThat(this, is(anyOf((double[]) null))));
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
		assertThat(object, is(sameAs(anyOf(object))));
		assertThrowsAssertionErrorWithMessage(
				() -> assertThat(object, is(sameAs(anyOf(equalObject)))),
				"expected that subject is same as any of: [\"object\"]",
				"but was: \"object\""
		);
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
