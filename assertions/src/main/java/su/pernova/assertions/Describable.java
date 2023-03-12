package su.pernova.assertions;

import static internal.su.pernova.assertions.DescribableUtils.getDefaultDescriptionText;

public interface Describable {

	default Description describe(Description description) {
		return description.appendText(getDefaultDescriptionText(this));
	}

	default Description describeMismatch(Description mismatchDescription) {
		return mismatchDescription;
	}
}
