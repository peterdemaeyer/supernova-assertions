package internal.su.pernova.assertions;

import static java.lang.management.ManagementFactory.getThreadMXBean;

import static org.junit.jupiter.api.Assertions.assertFalse;

import static internal.su.pernova.assertions.FailureProvider.anyStackTraceMatches;

import java.lang.management.ThreadInfo;
import java.util.NoSuchElementException;
import java.util.concurrent.Semaphore;

import org.junit.jupiter.api.Test;

class FailureProviderTest {

	@Test
	void stackTraceDoesNotMatchNewOrTerminatedThreads() throws Exception {
		final Semaphore semaphore = new Semaphore(-1);
		final Thread waitingThread = new Thread(() -> {
			try {
				semaphore.acquire();
			} catch (InterruptedException interrupted) {
			}
		});
		waitingThread.start();
		try {
			final ThreadInfo[] allThreads = getThreadMXBean().dumpAllThreads(false, false);
			final ThreadInfo waitingThreadInfo = getStackTrace(allThreads, waitingThread.getId());
			final ThreadInfo[] selectedThreads = new ThreadInfo[] { waitingThreadInfo };
			assertFalse(anyStackTraceMatches(selectedThreads, "org.junit.runners.", "org.junit.jupiter."));
		} finally {
			waitingThread.interrupt();
			waitingThread.join();
		}
	}

	private static ThreadInfo getStackTrace(ThreadInfo[] allThreads, long threadId) {
		for (ThreadInfo threadInfo : allThreads) {
			if (threadInfo.getThreadId() == threadId) {
				return threadInfo;
			}
		}
		throw new NoSuchElementException();
	}
}
