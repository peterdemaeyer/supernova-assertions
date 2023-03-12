package su.pernova.assertions;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import static su.pernova.assertions.Subjects.condition;
import static su.pernova.assertions.Subjects.subject;

import org.junit.jupiter.api.Test;

class SubjectsTest {

	@Test
	void noSubjectIsNull() {
		assertNotNull(subject(this));
		assertNotNull(condition(true));
	}
}
