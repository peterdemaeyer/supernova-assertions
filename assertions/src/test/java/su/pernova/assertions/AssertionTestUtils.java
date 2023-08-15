package su.pernova.assertions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.function.Executable;

public final class AssertionTestUtils {

	private AssertionTestUtils() {
	}

	public static void assertThrowsAssertionErrorWithMessage(Executable executable, String expectedMessage) {
		assertThrowsWithMessage(AssertionError.class, executable, expectedMessage);
	}

	public static <T extends Throwable> void assertThrowsWithMessage(Class<T> expectedType, Executable executable, String expectedMessage) {
		final T throwable = assertThrows(expectedType, executable);
		assertEquals(expectedMessage, throwable.getMessage());
	}
}
