package su.pernova.design;

import static java.lang.reflect.Modifier.PRIVATE;
import static java.lang.reflect.Modifier.STATIC;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.junit.jupiter.api.Test;

public interface UtilityContractTest {

	@Test
	default Class<?> getUtilityClass() throws Exception {
		String className = getClass().getName();
		if (className.endsWith("Test")) {
			className = className.substring(0, className.length() - 4);
		}
		return Class.forName(className);
	}

	@Test
	default void utilityClassHasSinglePrivateParameterlessConstructor() throws Exception {
		final Constructor<?>[] constructors = getUtilityClass().getDeclaredConstructors();
		assertEquals(1, constructors.length);
		final Constructor<?> constructor = constructors[0];
		assertEquals(PRIVATE, constructor.getModifiers() & PRIVATE);
		assertEquals(0, constructor.getParameterCount());
	}

	@Test
	default void utilityClassOnlyHasStaticMethods() throws Exception {
		for (Method method : getUtilityClass().getDeclaredMethods()) {
			assertEquals(STATIC, method.getModifiers() & STATIC);
		}
	}

	@Test
	default void utilityClassOnlyHasStaticFields() throws Exception {
		for (Field field : getUtilityClass().getDeclaredFields()) {
			assertEquals(STATIC, field.getModifiers() & STATIC);
		}
	}
}
