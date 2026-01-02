package internal.su.pernova.assertions.subjects;

import internal.su.pernova.assertions.NamedDescribable;
import su.pernova.assertions.Description;
import su.pernova.assertions.Matcher;
import su.pernova.assertions.Subject;

/**
 * This class encapsulates an actual value as a subject with an optional description.
 * When the description is null, the description will automatically be derived from the class name.
 */
public class ObjectSubject extends NamedDescribable implements Subject {

	protected final Object actualValue;

	public ObjectSubject(CharSequence name, Object actualValue) {
		super(name);
		this.actualValue = actualValue;
	}

	public ObjectSubject(Object actualValue) {
		this("subject", actualValue);
	}

	@Override
	public boolean match(Matcher matcher) {
		return matcher.match(actualValue);
	}

	@Override
	public Description describeMismatch(Description mismatchDescription) {
		return mismatchDescription.appendPrompt().appendActualValue(actualValue);
	}

	@Override
	public String toString() {
		return super.toString() + "(" + actualValue + ")";
	}
}
