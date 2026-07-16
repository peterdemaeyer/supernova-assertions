package internal.su.pernova.assertions.matchers;

import static java.lang.String.format;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static su.pernova.assertions.AssertionTestUtils.assertThrowsAssertionErrorWithMessage;
import static su.pernova.assertions.Assertions.assertThat;
import static su.pernova.assertions.Matchers.instanceOf;
import static su.pernova.assertions.Matchers.is;

import org.junit.jupiter.api.Test;

import su.pernova.assertions.Context;
import su.pernova.assertions.MatcherContractTest;

class InstanceOfTest implements MatcherContractTest {

	@Override
	public InstanceOf getInstance() {
		return assertInstanceOf(InstanceOf.class, instanceOf(String.class));
	}

	@Test
	void nullDoesNotMatch() {
		assertEquals(format("expected that subject is instance of: java.lang.String%n" +
						"but was: null"),
				assertThrows(AssertionError.class,
						() -> assertThat(null, is(instanceOf(String.class)))).getMessage());
	}

	@Test
	void objectThatIsNotInstanceOfDoesNotMatch() {
		final Object notInstanceOf = new Object();
		assertEquals(format("expected that subject is instance of: java.lang.Number%n"
						+ "but was: \"%s\"", notInstanceOf),
				assertThrows(AssertionError.class,
						() -> assertThat(notInstanceOf, is(instanceOf(Number.class)))).getMessage());
	}

	@Test
	void objectThatIsInstanceOfClassMatches() {
		assertThat("abc", is(instanceOf(String.class)));
	}

	@Test
	void objectThatIsInstanceOfSubclassMatches() {
		assertThat("abc", is(instanceOf(CharSequence.class)));
	}

	@Test
	void objectThatIsInstanceOfSuperclassDoesNotMatch() {
		final Exception instanceOfSuperclass = new Exception();
		assertEquals(format("expected that subject is instance of: java.lang.RuntimeException%n" +
						"but was: \"%s\"", instanceOfSuperclass),
				assertThrows(AssertionError.class,
						() -> assertThat(instanceOfSuperclass, is(instanceOf(RuntimeException.class)))).getMessage());
	}

	@Test
	void stringValue() {
		assertEquals("instanceOf(java.lang.Void)", instanceOf(Void.class).toString());
	}

	@Test
	void contextualizedStringValue() {
		assertEquals("instanceOf(java.lang.Void)",
				instanceOf(Void.class).contextualize(new Context()).toString());
	}
}
