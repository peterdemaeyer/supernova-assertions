package su.pernova.assertions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import internal.su.pernova.assertions.matchers.Identity;
import internal.su.pernova.assertions.matchers.SameAs;
import internal.su.pernova.assertions.subjects.GenericSubject;

class GenericSubjectTest {

	private final Description description = new AppendableDescription(new StringBuilder());

	private final Description mismatchDescription = new AppendableDescription(new StringBuilder());

	@Test
	void subjectMatchesNull() {
		final GenericSubject subject = new GenericSubject(null);
		assertTrue(subject.match(new Identity("", new SameAs("", null))));
		subject.describe(description);
		assertEquals(" subject", description.toString());
		subject.describeMismatch(mismatchDescription);
		assertEquals(": null", mismatchDescription.toString());
	}
}
