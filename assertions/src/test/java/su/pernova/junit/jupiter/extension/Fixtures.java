package su.pernova.junit.jupiter.extension;

import static org.mockito.Answers.CALLS_REAL_METHODS;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.withSettings;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;

public final class Fixtures {

	private Fixtures() {
	}

	public static <F extends Fixture> F forEach(F fixture) {
		final F stub = (F) mock(
				fixture.getClass(),
				withSettings()
						.spiedInstance(fixture)
						.defaultAnswer(CALLS_REAL_METHODS)
						.extraInterfaces(BeforeEachCallback.class, AfterEachCallback.class));
		try {
			doAnswer(invocation -> {
				fixture.before();
				return null;
			}).when((BeforeEachCallback) stub).beforeEach(any());
			doAnswer(invocation -> {
				fixture.after();
				return null;
			}).when((AfterEachCallback) stub).afterEach(any());
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return stub;
	}

	public static <F extends Fixture> F forAll(F fixture) {
		final F stub = (F) mock(
				fixture.getClass(),
				withSettings()
						.spiedInstance(fixture)
						.defaultAnswer(CALLS_REAL_METHODS)
						.extraInterfaces(BeforeAllCallback.class, AfterAllCallback.class));
		try {
			doAnswer(invocation -> {
				fixture.before();
				return null;
			}).when((BeforeAllCallback) stub).beforeAll(any());
			doAnswer(invocation -> {
				fixture.after();
				return null;
			}).when((AfterAllCallback) stub).afterAll(any());
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return stub;
	}
}
