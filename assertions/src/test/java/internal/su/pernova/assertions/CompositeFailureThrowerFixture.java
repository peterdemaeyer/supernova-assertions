package internal.su.pernova.assertions;

import java.lang.reflect.Field;
import java.util.Collection;

import su.pernova.junit.jupiter.extension.Fixture;

public abstract class CompositeFailureThrowerFixture implements Fixture {

	private Collection<? extends FailureThrower> initialThrowers;

	private final CompositeFailureThrower compositeThrower;

	private final Field throwersField;

	private boolean initiallyAccessible;

	CompositeFailureThrowerFixture(CompositeFailureThrower compositeThrower) throws NoSuchFieldException {
		this.compositeThrower = compositeThrower;
		throwersField = CompositeFailureThrower.class.getDeclaredField("throwers");
	}

	@Override
	public void before() throws Exception {
		initiallyAccessible = throwersField.canAccess(compositeThrower);
		if (!initiallyAccessible) {
			throwersField.setAccessible(true);
		}
		initialThrowers = (Collection<? extends FailureThrower>) throwersField.get(compositeThrower);
	}

	@Override
	public void after() throws Exception {
		throwersField.set(compositeThrower, initialThrowers);
		if (!initiallyAccessible) {
			throwersField.setAccessible(false);
		}
	}

	public void setThrowers(Collection<? extends FailureThrower> throwers) throws IllegalAccessException {
		throwersField.set(compositeThrower, throwers);
	}
}
