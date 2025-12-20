package internal.su.pernova.assertions;

import static java.util.Objects.requireNonNull;

import su.pernova.assertions.DelegatingDescription;
import su.pernova.assertions.Describable;
import su.pernova.assertions.Description;

public class AnonymousDescribable<D extends Describable> implements Describable {

	protected final D delegatee;

	public AnonymousDescribable(D delegatee) {
		this.delegatee = requireNonNull(delegatee, "delegatee is null");
	}

	@Override
	public Description describe(Description description) {
		final boolean[] inhibited = { false };
		return delegatee.describe(new DelegatingDescription(description) {

			@Override
			public Description appendText(CharSequence text) {
				return description;
			}
		});
	}

	@Override
	public Description describeMismatch(Description mismatchDescription) {
		return delegatee.describeMismatch(mismatchDescription);
	}
}
