package su.pernova.assertions;

import su.pernova.lang.ObjectContractTest;

class DescriptorTest implements ObjectContractTest {

	@Override
	public Descriptor getInstance() {
		return new Descriptor(getClass(), "testDescriptor");
	}

	@Override
	public Descriptor getEqualInstance() {
		return new Descriptor(getClass(), "testDescriptor");
	}

	@Override
	public Descriptor[] getNonEqualInstances() {
		return new Descriptor[] {
				new Descriptor(getClass(), "anotherTestDescriptor"),
				new Descriptor(super.getClass(), "testDescriptor")
		};
	}
}
