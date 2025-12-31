package su.pernova.design;

import static java.lang.reflect.Modifier.FINAL;
import static java.lang.reflect.Modifier.PRIVATE;
import static java.lang.reflect.Modifier.STATIC;
import static java.util.Arrays.asList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public interface SingletonContractTest extends ContractTest {

	@Test
	default void singletonClassHasSinglePrivateConstructor() throws Exception {
		final Constructor<?>[] constructors = getClassUnderTest().getDeclaredConstructors();
		assertEquals(1, constructors.length);
		for (final Constructor<?> constructor : constructors) {
			assertEquals(PRIVATE, constructor.getModifiers() & PRIVATE);
		}
	}

	@Test
	default void singletonClassHasStaticInstanceAccessor() throws Exception {
		for (final Method method : getClassUnderTest().getDeclaredMethods()) {
			if (method.getName().equals("getInstance")) {
				assertEquals(STATIC, method.getModifiers() & STATIC);
				return;
			}
		}
		fail();
	}

	@Test
	default void singletonClassImplementsAnInterface() throws Exception {
		final List<Class<?>> interfaces = new ArrayList<>();
		for (Class<?> class_ = getClassUnderTest(); class_ != null; class_ = class_.getSuperclass()) {
			interfaces.addAll(asList(class_.getInterfaces()));
		}
		assertFalse(interfaces.isEmpty());
	}

	@Test
	default void singletonClassHasInnerSingletonClass() throws Exception {
		final Class<?> innerSingletonClass = Class.forName(getClassUnderTest().getName() + "$Singleton");
		assertEquals(PRIVATE, innerSingletonClass.getModifiers() & PRIVATE);
		assertEquals(STATIC, innerSingletonClass.getModifiers() & STATIC);
	}

	@Test
	default void innerSingletonClassHasInstanceConstant() throws Exception {
		final Class<?> innerSingletonClass = Class.forName(getClassUnderTest().getName() + "$Singleton");
		final Field instanceField = innerSingletonClass.getDeclaredField("INSTANCE");
		assertEquals(PRIVATE, instanceField.getModifiers() & PRIVATE);
		assertEquals(STATIC, instanceField.getModifiers() & STATIC);
		assertEquals(FINAL, instanceField.getModifiers() & FINAL);
	}
}
