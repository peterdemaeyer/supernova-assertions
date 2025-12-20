package internal.su.pernova.assertions.subjects;

public class Condition extends ObjectSubject {

	public Condition(CharSequence name, Object actualValue) {
		super(name, actualValue);
	}

	public Condition(Object actualValue) {
		this(null, actualValue);
	}
}
