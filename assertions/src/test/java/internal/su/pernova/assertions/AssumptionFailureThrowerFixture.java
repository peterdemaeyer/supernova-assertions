package internal.su.pernova.assertions;

public class AssumptionFailureThrowerFixture extends CompositeFailureThrowerFixture {

	public AssumptionFailureThrowerFixture() throws NoSuchFieldException {
		super(AssumptionFailureThrower.getInstance());
	}
}
