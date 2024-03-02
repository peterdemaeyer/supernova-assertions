package su.pernova.assertions;

import static java.util.Collections.emptyList;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.mock;

import static su.pernova.assertions.Assertions.assertThat;
import static su.pernova.assertions.Matchers.does;
import static su.pernova.assertions.Matchers.is;
import static su.pernova.assertions.Matchers.match;
import static su.pernova.assertions.Matchers.not;
import static su.pernova.assertions.Matchers.regex;
import static su.pernova.assertions.Subjects.subject;
import static su.pernova.junit.jupiter.extension.Fixtures.forEach;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import internal.su.pernova.assertions.AssertionFailureThrowerFixture;
import internal.su.pernova.assertions.FailureThrower;
import su.pernova.design.UtilityContractTest;

class AssertionsTest implements UtilityContractTest {

	@RegisterExtension
	final AssertionFailureThrowerFixture fixture = forEach(new AssertionFailureThrowerFixture());

	AssertionsTest() throws Exception {
	}

	@Test
	void noInstantFailureWhenThrowerDoesNotThrow() {
		assertDoesNotThrow(() -> su.pernova.assertions.Assertions.fail(mock(FailureThrower.class)));
	}

	@Test
	void noFailureWhenThrowerDoesNotThrow() {
		assertDoesNotThrow(() -> su.pernova.assertions.Assertions.verifyThat(
				mock(FailureThrower.class), subject(true), is(false)));
	}

	@Test
	void noFailureWhenAssertionFailureThrowerDoesNotThrow() throws Exception {
		fixture.setThrowers(emptyList());
		assertDoesNotThrow(() -> su.pernova.assertions.Assertions.fail());
	}

	@Test
	void negatedMatches() {
		assertDoesNotThrow(() -> assertThat("apple", does(not(match(regex("[0-9]*"))))));
	}

	@Test
	void emphasizedMatches() {
		assertDoesNotThrow(() -> assertThat("123", does(match(regex("[0-9]*")))));
	}
}
