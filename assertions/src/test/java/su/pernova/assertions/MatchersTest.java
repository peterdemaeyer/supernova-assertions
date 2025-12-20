package su.pernova.assertions;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static su.pernova.assertions.Assertions.assertThat;
import static su.pernova.assertions.Matchers.do_;
import static su.pernova.assertions.Matchers.dо;
import static su.pernova.assertions.Matchers.equalTo;
import static su.pernova.assertions.Matchers.instanceOf;
import static su.pernova.assertions.Matchers.match;
import static su.pernova.assertions.Matchers.not;
import static su.pernova.assertions.Matchers.regex;
import static su.pernova.assertions.Matchers.sameAs;

import java.util.List;
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

	@Test
	void doUnicode() {
		List<String> elements = List.of("a1", "b2");
		assertDoesNotThrow(() -> assertThat(elements, dо(not(match(regex("..."))))));
	}

	@Test
	void doUnderscore() {
		List<String> elements = List.of("e1", "e2");
		assertThat(elements, do_(not(match(regex(".{3}")))));
	}
}
