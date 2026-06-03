package internal.su.pernova.assertions.subjects;

import su.pernova.assertions.Context;
import su.pernova.assertions.Subject;

public class Condition extends ObjectSubject {

	public Condition(Object actualValue) {
		super(null, actualValue);
	}

	@Override
	public Subject contextualize(Context context) {
		return context.contextualize(this, actualValue, Condition::new);
	}
}
