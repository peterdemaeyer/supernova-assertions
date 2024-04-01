package internal.su.pernova.assertions.matchers;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static su.pernova.assertions.AssertionTestUtils.assertThrowsAssertionErrorWithMessage;
import static su.pernova.assertions.Assertions.assertThat;
import static su.pernova.assertions.Matchers.allOf;
import static su.pernova.assertions.Matchers.equalTo;
import static su.pernova.assertions.Matchers.instanceOf;
import static su.pernova.assertions.Matchers.is;
import static su.pernova.assertions.Matchers.sameAs;

import java.util.Date;

import org.junit.jupiter.api.Test;

class AllOfTest {

	@Test
	void allOfMatchersWithoutContext() {
		assertDoesNotThrow(() -> assertThat(null, allOf(is(null))));
	}

	@Test
	void allOfManyMatchers() {
		final Object object = "object";
		assertThat(object, is(allOf(
				instanceOf(String.class),
				equalTo("object"),
				sameAs(object)
		)));
		assertThrowsAssertionErrorWithMessage(
				() -> assertThat(object, is(allOf(
						instanceOf(String.class),
						equalTo("another object"),
						sameAs(object)
				))),
				"expected that subject is all of:",
				"\tinstance of: java.lang.String",
				"\tequal to: \"another object\"",
				"\tsame as: \"object\"",
				"but was: \"object\""
		);
	}

	@Test
	void allOfObjectsWithoutContext() {
		assertEquals("no context",
				assertThrows(IllegalStateException.class, () -> assertThat(null, allOf(new Object[] { null })))
						.getMessage());
	}

	@Test
	void allOfNullObjects() {
		assertThrows(NullPointerException.class, () -> assertThat((Object) null, is(allOf((Object[]) null))));
	}

	@Test
	void allOfNullDoubles() {
		assertThrows(NullPointerException.class, () -> assertThat((Object) null, is(allOf((double[]) null))));
	}

	@Test
	void allOfEmptyObjects() {
		assertThat(this, is(allOf(new Object[0])));
	}

	@Test
	void allOfEmptyDoubles() {
		assertThat(0d, is(allOf(new double[0])));
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
}
