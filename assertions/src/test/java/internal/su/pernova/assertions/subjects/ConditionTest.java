package internal.su.pernova.assertions.subjects;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import su.pernova.assertions.AppendableDescription;
import su.pernova.assertions.Description;

class ConditionTest {

	@Test
	void description() {
		final Condition condition = new Condition(true);
		final Description description = condition.describe(new AppendableDescription(new StringBuilder()));
		assertEquals(" condition", description.toString());
	}
}
