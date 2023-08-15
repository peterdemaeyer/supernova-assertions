package su.pernova.assertions;

import static org.junit.jupiter.api.Assertions.*;

import static su.pernova.assertions.Matchers.equalTo;
import static su.pernova.assertions.Matchers.instanceOf;
import static su.pernova.assertions.Matchers.regex;
import static su.pernova.assertions.Matchers.sameAs;

import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;

import su.pernova.design.UtilityContractTest;

class MatchersTest implements UtilityContractTest {

	@Test
	void noMatcherIsNull() {
		assertNotNull(sameAs(this));
		assertNotNull(equalTo(this));
		assertNotNull(instanceOf(getClass()));
		assertNotNull(regex(".*"));
		assertNotNull(regex(Pattern.compile(".*")));
	}

	@Test
	void regexThrowsWhenNull() {
		assertEquals(
				"regex is null",
				assertThrows(NullPointerException.class, () -> regex((Pattern) null)).getMessage()
		);
		assertEquals(
				"regex is null",
				assertThrows(NullPointerException.class, () -> regex((String) null)).getMessage()
		);
	}
}
