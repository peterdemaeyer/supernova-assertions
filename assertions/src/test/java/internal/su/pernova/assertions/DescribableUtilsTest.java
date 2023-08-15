package internal.su.pernova.assertions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static internal.su.pernova.assertions.DescribableUtils.getDefaultDescriptionText;

import org.junit.jupiter.api.Test;

import internal.su.pernova.assertions.subjects.Condition;
import internal.su.pernova.assertions.subjects.DefaultSubject;
import su.pernova.assertions.Describable;

class DescribableUtilsTest {

	@Test
	void defaultDescriptionText() {
		assertEquals(" condition", getDefaultDescriptionText(new Condition(null)));
	}

	@Test
	void defaultDescriptionTextStripsPrefix() {
		assertEquals(" subject", getDefaultDescriptionText(new DefaultSubject(null)));
	}

	@Test
	void defaultDescriptionTextStripsSuffix() {
		assertEquals(" describable", getDefaultDescriptionText(new DescribableImpl()));
	}

	private static class DescribableImpl implements Describable {
	}

	@Test
	void defaultDescriptionTextSplitsOnNumbers() {
		assertEquals(" describable 123", getDefaultDescriptionText(new Describable123()));
	}

	private static class Describable123 implements Describable {
	}

	@Test
	void defaultDescriptionTextStripsRecursively() {
		assertEquals(" describable", getDefaultDescriptionText(new BaseGenericDefaultGenericBasicDescribableImplImpl()));
	}

	private static class BaseGenericDefaultGenericBasicDescribableImplImpl implements Describable {
	}

	@Test
	void defaultDescriptionTextStripsWhenEqualToPrefix() {
		assertEquals("", getDefaultDescriptionText(new Generic()));
	}

	private static class Generic implements Describable {
	}

	@Test
	void defaultDescriptionTextStripsWhenEqualToSuffix() {
		assertEquals("", getDefaultDescriptionText(new Impl()));
	}

	private static class Impl implements Describable {
	}

	/**
	 * {@code Basement} contains the stripped prefix {@code Base} but is an unrelated class name so should not be
	 * stripped.
	 */
	@Test
	void defaultDescriptionTextDoesNotStripWhenMoreThanPrefix() {
		assertEquals(" basement", getDefaultDescriptionText(new Basement()));
	}

	private static class Basement implements Describable {
	}
}
