package internal.su.pernova.assertions;

import static java.util.Objects.requireNonNull;

import su.pernova.assertions.DelegatingDescription;
import su.pernova.assertions.Describable;
import su.pernova.assertions.Description;

public class ImplicitDescribable<D extends Describable> implements Describable {

	protected final D destination;

	public ImplicitDescribable(D destination) {
		this.destination = requireNonNull(destination, "destination is null");
	}

	@Override
	public Description describe(Description description) {
		return destination.describe(new DelegatingDescription(description) {

			@Override
			public Description appendText(CharSequence text) {
				// Inhibit appendText once, then continue as usual with description.
				return description;
			}
		});
	}

	@Override
	public Description describeMismatch(Description mismatchDescription) {
		return destination.describeMismatch(mismatchDescription);
	}
}
