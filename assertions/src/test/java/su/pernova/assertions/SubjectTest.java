package su.pernova.assertions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static su.pernova.assertions.Matchers.equalTo;
import static su.pernova.assertions.Matchers.is;

import org.junit.jupiter.api.Test;

import internal.su.pernova.assertions.subjects.ObjectSubject;

class SubjectTest {

	private final Description description = new AppendableDescription(new StringBuilder());

	private final Description mismatchDescription = new AppendableDescription(new StringBuilder());

	@Test
	void subjectMatchesNull() {
		Subject subject = new ObjectSubject(null);
		assertTrue(subject.match(is(equalTo(null))));
		subject.describe(description);
		assertEquals("subject", description.toString());
		subject.describeMismatch(mismatchDescription);
		assertEquals(": null", mismatchDescription.toString());
	}
}
