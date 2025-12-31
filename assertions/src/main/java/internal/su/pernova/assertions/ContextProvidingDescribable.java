package internal.su.pernova.assertions;

import static java.util.Objects.requireNonNull;

import su.pernova.assertions.Describable;
import su.pernova.assertions.Description;

public class ContextProvidingDescribable<D extends Describable> extends NamedDescribable implements Describable {

	protected final D destination;

	public ContextProvidingDescribable(CharSequence name, D destination) {
		super(name);
		this.destination = requireNonNull(destination, "destination is null");
	}

	@Override
	public Description describe(Description description) {
		return destination.describe(super.describe(description));
	}

	@Override
	public Description describeMismatch(Description mismatchDescription) {
		return mismatchDescription.appendText("was");
	}

	@Override
	public String toString() {
		return super.toString() + "{" + destination.toString() + "}";
	}
}
