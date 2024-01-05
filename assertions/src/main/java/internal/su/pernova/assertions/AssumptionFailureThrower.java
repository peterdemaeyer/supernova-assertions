package internal.su.pernova.assertions;

import java.util.ArrayList;
import java.util.List;

public class AssumptionFailureThrower extends CompositeFailureThrower {

	private static class Singleton {

		private static final AssumptionFailureThrower INSTANCE = new AssumptionFailureThrower();
	}

	private AssumptionFailureThrower() {
		super(loadThrowers());
	}

	private static List<FailureThrower> loadThrowers() {
		ArrayList<FailureThrower> throwers = new ArrayList<>(3);
		try {
			// JUnit 5.
			throwers.add(TestAbortedExceptionThrower.getInstance());
		} catch (NoClassDefFoundError ignored) {
			// Optional OpenTest4J dependency is not on runtime class path. Continue without it.
		}
		try {
			// JUnit 4.
			throwers.add(AssumptionViolatedExceptionThrower.getInstance());
		} catch (NoClassDefFoundError ignored) {
			// Optional JUnit 4 dependency is not on runtime class path. Continue without it.
		}
		throwers.add(AssertionErrorThrower.getInstance());
		throwers.trimToSize();
		return throwers;
	}

	public static AssumptionFailureThrower getInstance() {
		return Singleton.INSTANCE;
	}
}
