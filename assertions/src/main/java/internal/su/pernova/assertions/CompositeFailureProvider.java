package internal.su.pernova.assertions;

import static java.util.Objects.requireNonNull;

import java.util.Collection;

class CompositeFailureProvider extends FailureProvider {

	private final Collection<FailureProvider> providers;

	CompositeFailureProvider(Collection<FailureProvider> providers) {
		this.providers = requireNonNull(providers, "providers are null");
	}

	@Override
	public AssertionError newFailure() {
		for (FailureProvider provider : providers) {
			final AssertionError failure = provider.newFailure();
			if (failure != null) {
				return failure;
			}
		}
		return null;
	}

	@Override
	public AssertionError newFailure(String message, Object expected, Object actual) {
		for (FailureProvider provider : providers) {
			final AssertionError failure = provider.newFailure(message, expected, actual);
			if (failure != null) {
				return failure;
			}
		}
		return null;
	}
}
