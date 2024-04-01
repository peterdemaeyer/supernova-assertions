package su.pernova.design;

import static java.lang.reflect.Modifier.PRIVATE;
import static java.lang.reflect.Modifier.STATIC;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.junit.jupiter.api.Test;

public interface UtilityContractTest extends ContractTest {

	@Test
	default void utilityClassHasSinglePrivateParameterlessConstructor() throws Exception {
		final Constructor<?>[] constructors = getClassUnderTest().getDeclaredConstructors();
		assertEquals(1, constructors.length);
		final Constructor<?> constructor = constructors[0];
		assertEquals(PRIVATE, constructor.getModifiers() & PRIVATE);
		assertEquals(0, constructor.getParameterCount());
	}

	@Test
	default void utilityClassOnlyHasStaticMethods() throws Exception {
		for (final Method method : getClassUnderTest().getDeclaredMethods()) {
			assertEquals(STATIC, method.getModifiers() & STATIC);
		}
	}

	@Test
	default void utilityClassOnlyHasStaticFields() throws Exception {
		for (final Field field : getClassUnderTest().getDeclaredFields()) {
			assertEquals(STATIC, field.getModifiers() & STATIC);
		}
	}
}
