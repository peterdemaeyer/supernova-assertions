package su.pernova.assertions;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import su.pernova.design.ContractTest;

public interface MatcherFactoryContractTest extends ContractTest {

	MatcherFactory getInstance();

	@Test
	default void creationWithNullDoesNotThrow() {
		assertDoesNotThrow(() -> getInstance().create(null));
	}

	@Test
	default void creationWithObjectDoesNotThrow() {
		assertDoesNotThrow(() -> getInstance().create(new Object()));
	}

	@Test
	default void creationWithDoubleDoesNotThrow() {
		assertDoesNotThrow(() -> getInstance().create(0d));
		assertDoesNotThrow(() -> getInstance().create(Double.NaN));
		assertDoesNotThrow(() -> getInstance().create(Double.MIN_VALUE));
		assertDoesNotThrow(() -> getInstance().create(Double.MAX_VALUE));
		assertDoesNotThrow(() -> getInstance().create(Double.MIN_NORMAL));
		assertDoesNotThrow(() -> getInstance().create(Double.NEGATIVE_INFINITY));
		assertDoesNotThrow(() -> getInstance().create(Double.POSITIVE_INFINITY));
	}

	@Test
	default void creationWithFloatDoesNotThrow() {
		assertDoesNotThrow(() -> getInstance().create(0f));
		assertDoesNotThrow(() -> getInstance().create(Float.NaN));
		assertDoesNotThrow(() -> getInstance().create(Float.MIN_VALUE));
		assertDoesNotThrow(() -> getInstance().create(Float.MAX_VALUE));
		assertDoesNotThrow(() -> getInstance().create(Float.MIN_NORMAL));
		assertDoesNotThrow(() -> getInstance().create(Float.NEGATIVE_INFINITY));
		assertDoesNotThrow(() -> getInstance().create(Float.POSITIVE_INFINITY));
	}

	@Test
	default void creationWithLongDoesNotThrow() {
		assertDoesNotThrow(() -> getInstance().create(Long.MAX_VALUE));
		assertDoesNotThrow(() -> getInstance().create(Long.MIN_VALUE));
	}

	@Test
	default void creationWithIntDoesNotThrow() {
		assertDoesNotThrow(() -> getInstance().create(Integer.MIN_VALUE));
		assertDoesNotThrow(() -> getInstance().create(Integer.MAX_VALUE));
	}

	@Test
	default void creationWithShortDoesNotThrow() {
		assertDoesNotThrow(() -> getInstance().create(Short.MIN_VALUE));
		assertDoesNotThrow(() -> getInstance().create(Short.MAX_VALUE));
	}

	@Test
	default void creationWithByteDoesNotThrow() {
		assertDoesNotThrow(() -> getInstance().create(Byte.MIN_VALUE));
		assertDoesNotThrow(() -> getInstance().create(Byte.MAX_VALUE));
	}

	@Test
	default void creationWithCharDoesNotThrow() {
		assertDoesNotThrow(() -> getInstance().create('0'));
		assertDoesNotThrow(() -> getInstance().create('♥'));
	}

	@Test
	default void creationWithBooleanDoesNotThrow() {
		assertDoesNotThrow(() -> getInstance().create(false));
		assertDoesNotThrow(() -> getInstance().create(true));
	}
}
