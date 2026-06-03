package internal.su.pernova.assertions;

import static java.util.Objects.requireNonNull;

import static internal.su.pernova.assertions.DescribableUtils.toStrikethrough;

import su.pernova.assertions.DelegatingDescription;
import su.pernova.assertions.Describable;
import su.pernova.assertions.Description;

/**
 * An implicit describable is a decorator for a describable destination, inhibiting its appendage to a description.
 * Its {@link #toString()} implementation, used for debugging purposes, returns the string value of the destination in
 * strikethrough.
 * It is a parent of {@link internal.su.pernova.assertions.subjects.ImplicitSubject} and
 * {@link internal.su.pernova.assertions.matchers.ImplicitMatcher}
 *
 * @since 2.0.0
 */
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

	@Override
	public String toString() {
		final String explicitStringValue = destination.toString();
		final int leftDelimiterIndex = indexOf(explicitStringValue, '(', '{');
		return leftDelimiterIndex >= 0
				? toStrikethrough(explicitStringValue.substring(0, leftDelimiterIndex)) + explicitStringValue.substring(leftDelimiterIndex)
				: toStrikethrough(explicitStringValue);
	}

	private static int indexOf(CharSequence charSequence, char... chars) {
		for (int i = 0, n = charSequence.length(); i != n; i++) {
			char candidate = charSequence.charAt(i);
			for (char c : chars) {
				if (candidate == c) {
					return i;
				}
			}
		}
		return -1;
	}
}
