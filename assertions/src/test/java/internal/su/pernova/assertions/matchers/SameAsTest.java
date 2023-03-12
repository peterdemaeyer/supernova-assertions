package internal.su.pernova.assertions.matchers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static su.pernova.assertions.Assertions.assertThat;
import static su.pernova.assertions.Matchers.sameAs;

import org.junit.jupiter.api.Test;

class SameAsTest {

	@Test
	void sameAsNull() {
		final SameAs sameAsNull = (SameAs) sameAs(null);
		assertThat(null).is(sameAsNull);
		final AssertionError assertionFailure = assertThrows(AssertionError.class, () -> assertThat("not null").is(sameAsNull));
		assertEquals("expected that subject is same as: null\nbut was: \"not null\"", assertionFailure.getMessage());
	}
}
