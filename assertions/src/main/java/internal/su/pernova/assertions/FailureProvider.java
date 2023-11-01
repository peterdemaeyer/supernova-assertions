package internal.su.pernova.assertions;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.lang.management.ManagementFactory.getThreadMXBean;

import java.lang.management.ThreadInfo;
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
			providers.add(new AssertionFailedErrorFailureProvider());
		} catch (NoClassDefFoundError ignored) {
			// Optional OpenTest4J dependency is not on runtime class path. Continue without it.
		}
		// JUnit 4, TestNG.
		providers.add(new AssertionErrorFailureProvider());
		providers.trimToSize();
		return providers;
	}

	public abstract Error newAssertionFailure();

	public abstract Error newAssertionFailure(String message, Object expected, Object actual);

	/**
	 * Searches all stack traces top-down for an occurrence of a package name prefix to reject or accept.
	 * If the first hit is to reject, then this method returns {@code false}.
	 * If the first hit is to accept, then this method returns {@code true}.
	 * If there is no hit at all, then this method returns {@code false}.
	 *
	 * @param packageNamePrefixToReject a package name prefix to reject, which must not be {@code null}.
	 * @param packageNamePrefixToAccept a package name prefix to accept, which must not be {@code null}.
	 * @return {@code true} if any stack trace contains an entry matching the package name prefix to accept before it
	 * contains that to reject, {@code false} otherwise.
	 */
	protected static boolean anyStackTraceMatches(String packageNamePrefixToReject, String packageNamePrefixToAccept) {
		return anyStackTraceMatches(getThreadMXBean().dumpAllThreads(false, false), packageNamePrefixToReject, packageNamePrefixToAccept);
	}

	static boolean anyStackTraceMatches(ThreadInfo[] allThreads, String packageNamePrefixToReject, String packageNamePrefixToAccept) {
		for (ThreadInfo threadInfo : allThreads) {
			Boolean stackTraceMatches = stackTraceMatches(threadInfo.getStackTrace(), packageNamePrefixToReject, packageNamePrefixToAccept);
			if (stackTraceMatches != null) {
				return stackTraceMatches;
			}
		}
		return false;
	}

	private static Boolean stackTraceMatches(StackTraceElement[] stackTrace, String packageNamePrefixToReject, String packageNamePrefixToAccept) {
		for (int i = stackTrace.length; --i > -1; ) {
			StackTraceElement stackTraceElement = stackTrace[i];
			if (stackTraceElement.getClassName().startsWith(packageNamePrefixToReject)) {
				return FALSE;
			} else if (stackTraceElement.getClassName().startsWith(packageNamePrefixToAccept)) {
				return TRUE;
			}
		}
		return null;
	}
}
