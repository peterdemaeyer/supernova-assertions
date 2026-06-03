package internal.su.pernova.assertions;

import static internal.su.pernova.assertions.DescribableUtils.getDefaultName;
import static internal.su.pernova.assertions.DescribableUtils.toLowerCamelCase;

import su.pernova.assertions.Describable;
import su.pernova.assertions.Description;

/**
 * This class adds an optional customizable name to a describable.
 */
public abstract class NamedDescribable implements Describable {

	protected final CharSequence name;

	protected NamedDescribable(CharSequence name) {
		this.name = name != null ? name : getDefaultName(this);
	}

	@Override
	public CharSequence name() {
		return name;
	}

	@Override
	public String toString() {
		return toLowerCamelCase(name);
	}
}
