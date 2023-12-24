package internal.su.pernova.assertions;

import java.util.ArrayList;
import java.util.List;

public abstract class FailureProvider {

	private static class Singleton {

		private static final FailureProvider INSTANCE = new CompositeFailureProvider(loadFailureProviders());
	}

	protected FailureProvider() {
	}

	public static FailureProvider getInstance() {
		return Singleton.INSTANCE;
	}

	private static List<FailureProvider> loadFailureProviders() {
		ArrayList<FailureProvider> providers = new ArrayList<>(2);
		try {
			// JUnit 5.
			providers.add(AssertionFailedErrorProvider.getInstance());
		} catch (NoClassDefFoundError ignored) {
			// Optional OpenTest4J dependency is not on runtime class path. Continue without it.
		}
		// JUnit 4, TestNG.
		providers.add(AssertionErrorProvider.getInstance());
		providers.trimToSize();
		return providers;
	}

	public abstract AssertionError newFailure();

	public abstract AssertionError newFailure(String message, Object expected, Object actual);
}
