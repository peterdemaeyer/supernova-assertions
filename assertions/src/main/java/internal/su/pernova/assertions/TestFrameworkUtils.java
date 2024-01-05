package internal.su.pernova.assertions;

import static java.lang.management.ManagementFactory.getThreadMXBean;

import java.lang.management.ThreadInfo;
import java.util.function.Predicate;

final class TestFrameworkUtils {

	private TestFrameworkUtils() {
	}

	static boolean isJUnit4() {
		return matches(
				threadInfo -> threadInfo.getThreadName().equals("main"),
				frame -> frame.getClassName().startsWith("org.junit.runners."),
				frame -> frame.getClassName().startsWith("org.junit.jupiter.")
		);
	}

	 static boolean isOpenTest4J() {
		return matches(
				threadInfo -> threadInfo.getThreadName().equals("main"),
				frame -> frame.getClassName().startsWith("org.junit.jupiter."),
				frame -> frame.getClassName().startsWith("org.junit.runners.")
		);
	}

	private static boolean matches(Predicate<ThreadInfo> filter, Predicate<StackTraceElement> accept, Predicate<StackTraceElement> reject) {
		final ThreadInfo[] threadInfoArray = getThreadMXBean().dumpAllThreads(false, false);
		for (ThreadInfo threadInfo : threadInfoArray) {
			if (filter.test(threadInfo)) {
				final StackTraceElement[] stackTrace = threadInfo.getStackTrace();
				if (matches(stackTrace, accept)) {
					return true;
				} else if (matches(stackTrace, reject)) {
					return false;
				}
			}
		}
		return false;
	}

	private static boolean matches(StackTraceElement[] stackTrace, Predicate<StackTraceElement> accept) {
		for (StackTraceElement frame : stackTrace) {
			if (accept.test(frame)) {
				return true;
			}
		}
		return false;
	}
}
