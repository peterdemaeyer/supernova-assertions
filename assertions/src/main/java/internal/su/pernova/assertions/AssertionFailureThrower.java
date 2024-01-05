package internal.su.pernova.assertions;

import java.util.ArrayList;
import java.util.List;

public final class AssertionFailureThrower extends CompositeFailureThrower {

	private static class Singleton {

		private static final AssertionFailureThrower INSTANCE = new AssertionFailureThrower();
	}

	private AssertionFailureThrower() {
		super(loadThrowers());
	}

	public static AssertionFailureThrower getInstance() {
		return Singleton.INSTANCE;
	}

	private static List<FailureThrower> loadThrowers() {
		ArrayList<FailureThrower> throwers = new ArrayList<>(2);
		try {
			// JUnit 5.
			throwers.add(AssertionFailedErrorThrower.getInstance());
		} catch (NoClassDefFoundError ignored) {
			// Optional OpenTest4J dependency is not on runtime class path. Continue without it.
		}
		// JUnit 4, TestNG.
		throwers.add(AssertionErrorThrower.getInstance());
		throwers.trimToSize();
		return throwers;
	}
}
