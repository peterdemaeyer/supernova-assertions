package internal.su.pernova.assertions;

import su.pernova.assertions.AppendableDescription;
import su.pernova.assertions.Describable;
import su.pernova.assertions.Description;

/**
 * This class adds a default {@link Object#toString()} implementation to the {@link Describable} interface.
 * This is useful for debugging, but has no real function.
 * Consider extending this class instead of implementing {@code Describable}.
 */
public abstract class DefaultDescribable implements Describable {

	@Override
	public String toString() {
		Description description = new AppendableDescription(new StringBuilder());
		describe(description);
		return description.toString().trim();
	}
}
