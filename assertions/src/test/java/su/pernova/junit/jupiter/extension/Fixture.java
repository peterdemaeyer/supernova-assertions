package su.pernova.junit.jupiter.extension;

import org.junit.jupiter.api.extension.Extension;

/**
 * A (test) fixture is something that needs to be set up before as part of the (test) environment, and needs to be torn
 * down after to reinstate the initial situation.
 */
public interface Fixture extends Extension {

	/**
	 * Runs beforehand, as part of a setup.
	 * This is typically used before each or before all tests.
	 *
	 * @see Fixtures#forEach(Fixture)
	 * @see Fixtures#forAll(Fixture)
	 */
	void before() throws Exception;

	/**
	 * Runs afterward, as part of a teardown.
	 * This is typically used before each or before all tests.
	 *
	 * @see Fixtures#forEach(Fixture)
	 * @see Fixtures#forAll(Fixture)
	 */
	void after() throws Exception;
}
