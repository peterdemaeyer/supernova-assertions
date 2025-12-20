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
		this.name = name;
	}

	public CharSequence name() {
		return name;
	}

	@Override
	public Description describe(Description description) {
		return (name != null)
				? ((name.length() > 0)
				? description.appendText(name)
				: description)
				: Describable.super.describe(description);
	}

	@Override
	public String toString() {
		return toLowerCamelCase(name != null ? name : getDefaultName(this));
	}
}
