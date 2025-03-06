package su.pernova.assertions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import internal.su.pernova.assertions.matchers.DelegatingMatcher;
import internal.su.pernova.assertions.matchers.IsObject;
import internal.su.pernova.assertions.subjects.DefaultSubject;

class DefaultSubjectTest {

	private final Description description = new AppendableDescription(new StringBuilder());

	private final Description mismatchDescription = new AppendableDescription(new StringBuilder());

	@Test
	void subjectMatchesNull() {
		final DefaultSubject subject = new DefaultSubject(null);
		assertTrue(subject.match(new DelegatingMatcher("", new IsObject(null))));
		subject.describe(description);
		assertEquals("subject", description.toString());
		subject.describeMismatch(mismatchDescription);
		assertEquals(": null", mismatchDescription.toString());
	}
}
