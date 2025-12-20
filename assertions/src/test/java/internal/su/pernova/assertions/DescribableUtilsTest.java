package internal.su.pernova.assertions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static internal.su.pernova.assertions.DescribableUtils.getDefaultName;
import static internal.su.pernova.assertions.DescribableUtils.toStrikethrough;

import org.junit.jupiter.api.Test;

import internal.su.pernova.assertions.subjects.Condition;
import su.pernova.assertions.Describable;
import su.pernova.assertions.Matcher;
import su.pernova.assertions.Subject;

class DescribableUtilsTest {

	@Test
	void defaultDescriptionText() {
		assertEquals("condition", getDefaultName(new Condition(null)));
	}

	@Test
	void defaultDescriptionTextStripsPrefix() {
		assertEquals("subject", getDefaultName(new DefaultSubject()));
	}

	static class DefaultSubject implements Subject {

		@Override
		public boolean match(Matcher matcher) {
			return false;
		}
	}

	@Test
	void defaultDescriptionTextStripsSuffix() {
		assertEquals("describable", getDefaultName(new DescribableImpl()));
	}

	private static class DescribableImpl implements Describable {
	}

	@Test
	void defaultDescriptionTextSplitsOnNumbers() {
		assertEquals("describable 123", getDefaultName(new Describable123()));
	}

	private static class Describable123 implements Describable {
	}

	@Test
	void defaultDescriptionTextStripsRecursively() {
		assertEquals("describable", getDefaultName(new BaseGenericDefaultGenericBasicDescribableImplImpl()));
	}

	private static class BaseGenericDefaultGenericBasicDescribableImplImpl implements Describable {
	}

	@Test
	void defaultDescriptionTextStripsWhenEqualToPrefix() {
		assertEquals("", getDefaultName(new Generic()));
	}

	private static class Generic implements Describable {
	}

	@Test
	void defaultDescriptionTextStripsWhenEqualToSuffix() {
		assertEquals("", getDefaultName(new Impl()));
	}

	private static class Impl implements Describable {
	}

	/**
	 * {@code Basement} contains the stripped prefix {@code Base} but is an unrelated class name so should not be
	 * stripped.
	 */
	@Test
	void defaultDescriptionTextDoesNotStripWhenMoreThanPrefix() {
		assertEquals("basement", getDefaultName(new Basement()));
	}

	private static class Basement implements Describable {
	}

	@Test
	void strikethroughAppliesToLettersAndDigits() {
		final CharSequence camelCase = "camelCase123";
		assertEquals("c̶a̶m̶e̶l̶C̶a̶s̶e̶1̶2̶3̶", toStrikethrough(camelCase));
	}

	@Test
	void noDoubleStrikethrough() {
		final CharSequence camelCase = "c̶a̶m̶e̶l̶C̶a̶s̶e̶1̶2̶3̶";
		assertEquals("c̶a̶m̶e̶l̶C̶a̶s̶e̶1̶2̶3̶", toStrikethrough(camelCase));
	}

	@Test
	void strikethroughDoesNotApplyToSpacesAndPunctuation() {
		final CharSequence sentenceWithSpacesAndPunctuation = "This is a sentence with some [brackets].";
		assertEquals("T̶h̶i̶s̶ i̶s̶ a̶ s̶e̶n̶t̶e̶n̶c̶e̶ w̶i̶t̶h̶ s̶o̶m̶e̶ [b̶r̶a̶c̶k̶e̶t̶s̶].", toStrikethrough(sentenceWithSpacesAndPunctuation));
	}
}
