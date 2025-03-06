package su.pernova.assertions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.function.Executable;

public final class AssertionTestUtils {

	private AssertionTestUtils() {
	}

	public static <T extends AssertionError> T assertThrowsAssertionErrorWithMessage(Executable executable, String... expectedMessage) {
		return (T) assertThrowsWithMessage(AssertionError.class, executable, format(expectedMessage));
	}

	public static <T extends Throwable> T assertThrowsWithMessage(Class<T> expectedType, Executable executable, String... expectedMessage) {
		T throwable = assertThrows(expectedType, executable);
		assertEquals(format(expectedMessage), throwable.getMessage());
		return throwable;
	}

	private static String format(String... lines) {
		StringBuilder builder = new StringBuilder(lines.length * 4).append("%s");
		for (int i = 1; i < lines.length; i++) {
			builder.append("%n%s");
		}
		return String.format(builder.toString(), (Object[]) lines);
	}
}
