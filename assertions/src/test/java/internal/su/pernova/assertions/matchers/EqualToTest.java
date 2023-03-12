package internal.su.pernova.assertions.matchers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;

import org.junit.jupiter.api.Test;

class EqualToTest {

	@Test
	void constructionThrowsWhenExpectedIsNull() {
		final NullPointerException exception = assertThrows(NullPointerException.class, () -> new EqualTo(null));
		assertEquals("expected is null", exception.getMessage());
	}

	@Test
	void equalToDoesNotMatchNull() {
		final EqualTo equalTo = new EqualTo(this);
		assertFalse(equalTo.match(null));
	}

	@Test
	void equalToMatchesObjectIdentity() {
		final EqualTo equalTo = new EqualTo(this);
		assertTrue(equalTo.match(this));
	}

	@Test
	void equalToMatchesObjectEquality() {
		final EqualTo equalTo = new EqualTo(new Date(999_999_999L));
		assertTrue(equalTo.match(new Date(999_999_999L)));
	}

	@Test
	void equalToDoesNotMatchObjectOfDifferentClass() {
		final EqualTo equalTo = new EqualTo(Integer.valueOf(1));
		assertFalse(equalTo.match(Long.valueOf(1L)));
	}
}
