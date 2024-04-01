package su.pernova.assertions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class DescribableTest {

	private final Description description = new AppendableDescription(new StringBuilder());

	private final Description mismatchDescription = new AppendableDescription(new StringBuilder());

	@Test
	void defaultDescriptionWithSingleWordDescribable() {
		final Describable describable = new Pooh();
		assertEquals("pooh", describable.describe(description).toString());
		assertEquals("", describable.describeMismatch(mismatchDescription).toString());
	}

	private static class Pooh implements Describable {
	}

	@Test
	void defaultDescriptionWithMultiWordDescribable() {
		final Describable describable = new TestDescribable();
		final String descriptionText = describable.describe(description).toString();
		assertEquals("test describable", descriptionText);
		final String mismatchDescriptionText = describable.describeMismatch(mismatchDescription).toString();
		assertEquals("", mismatchDescriptionText);
	}

	private static class TestDescribable implements Describable {
	}

	@Test
	void defaultDescriptionWithSingleDigitNumberInDescribable() {
		final Describable describable = new Describable1();
		final String descriptionText = describable.describe(description).toString();
		assertEquals("describable 1", descriptionText);
		final String mismatchDescriptionText = describable.describeMismatch(mismatchDescription).toString();
		assertEquals("", mismatchDescriptionText);
	}

	private static class Describable1 implements Describable {
	}

	@Test
	void defaultDescriptionWithMultiDigitNumberInDescribable() {
		final Describable describable = new Describable123C456();
		assertEquals("describable 123 c 456", describable.describe(description).toString());
		assertEquals("", describable.describeMismatch(mismatchDescription).toString());
	}

	private static class Describable123C456 implements Describable {
	}
}
