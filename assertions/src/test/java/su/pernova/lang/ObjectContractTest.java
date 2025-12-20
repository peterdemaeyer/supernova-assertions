package su.pernova.lang;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import org.junit.jupiter.api.Test;

public interface ObjectContractTest {

	/**
	 * Provides an object of the class under test, not {@code null}.
	 * Implementations must implement this to return a non-null object of the class under test.
	 *
	 * @return an object of the class under test, not {@code null}.
	 */
	Object getInstance() throws Exception;

	/**
	 * Provides an object of the class under test equal to the one provided by {@link #getInstance()}, or {@code null}
	 * if not applicable.
	 * Equal objects are for example not applicable to singletons or glyphs (flyweights), because they have a finite
	 * enumeration of unique instances that have no equal.
	 *
	 * @return an object of the class under test equal to the one provided by {@link #getInstance()}, or {@code null}
	 * if not applicable.
	 */
	Object getEqualInstance() throws Exception;

	/**
	 * Provides an object of the class under test not equal to the one provided by {@link #getInstance()}, or
	 * {@code null} if not applicable.
	 * Non-equal objects are for example not applicable to singletons, because they only have a single instance.
	 *
	 * @return an object of the class under test not equal to the one provided by {@link #getInstance()}, or
	 * {@code null} if not applicable.
	 */
	Object[] getNonEqualInstances() throws Exception;

	@Test
	default void equalityWithNull() throws Exception {
		final Object object = getInstance();
		assertNotNull(object);
		assertFalse(object.equals(null));
	}

	@Test
	default void equalityWithSelf() throws Exception {
		final Object object = getInstance();
		assertNotNull(object);
		// Assert that the "is equal to" relation is reflexive.
		assertTrue(object.equals(object));
		assertEquals(object.hashCode(), object.hashCode());
	}

	@Test
	default void equalityWithEqualObject() throws Exception {
		final Object equalObject = getEqualInstance();
		assumeTrue(equalObject != null, "No equal object exists. This is typically the case for singletons and glyphs (flyweights).");
		final Object object = getInstance();
		assertNotNull(object);
		// Assert that the "is equal to" relation is symmetric.
		assertTrue(object.equals(equalObject));
		assertTrue(equalObject.equals(object));
		assertEquals(object.hashCode(), equalObject.hashCode());
		assertEquals(equalObject.hashCode(), object.hashCode());
	}

	@Test
	default void equalityWithNonEqualObject() throws Exception {
		final Object[] nonEqualObjects = getNonEqualInstances();
		assumeTrue(nonEqualObjects != null, "No non-equal object exists. This is typically the case for singletons.");
		final Object object = getInstance();
		assertNotNull(object);
		for (Object nonEqualObject : nonEqualObjects) {
			// Assert that the "is equal to" relation is symmetric.
			assertFalse(object.equals(nonEqualObjects));
			assertFalse(nonEqualObjects.equals(object));
			// We can't assert anything on the hash code.
			// The hash codes are likely to be different, but it's not a guarantee and not a strict requirement.
		}
	}

	@Test
	default void equalityWithNonEqualObjectOfOtherClass() throws Exception {
		final Object object = getInstance();
		assertNotNull(object);
		// Assert that the "is equal to" relation is symmetric.
		assertFalse(object.equals(this));
		// We can't assert anything on the hash code.
		// The hash codes are likely to be different, but it's not a guarantee and not a strict requirement.
	}

	@Test
	default void stringValueIsNotNull() throws Exception {
		final Object object = getInstance();
		assertNotNull(object.toString());
	}
}
