package internal.su.pernova.assertions;

public class AssertionFailureThrowerFixture extends CompositeFailureThrowerFixture {

	public AssertionFailureThrowerFixture() throws NoSuchFieldException {
		super(AssertionFailureThrower.getInstance());
	}
}
