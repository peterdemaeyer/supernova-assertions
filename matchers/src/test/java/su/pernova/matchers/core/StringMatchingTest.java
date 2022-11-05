package su.pernova.matchers.core;

import static su.pernova.matchers.core.StringContains.containsString;
import static su.pernova.matchers.core.StringEndsWith.endsWith;
import static su.pernova.matchers.core.StringStartsWith.startsWith;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * @author Steve Freeman 2016 http://www.hamcrest.com
 */
public class StringMatchingTest {

	@Test
	public void
	startsWithFailsWithNullSubstring() {
		assertThrows(IllegalArgumentException.class, () -> startsWith(null));
	}

	@Test
	public void
	endWithFailsWithNullSubstring() {
		assertThrows(IllegalArgumentException.class, () -> endsWith(null));
	}

	@Test
	public void
	containsFailsWithNullSubstring() {
		assertThrows(IllegalArgumentException.class, () -> containsString(null));
		assertThrows(IllegalArgumentException.class, () -> containsString(null));
	}

}
