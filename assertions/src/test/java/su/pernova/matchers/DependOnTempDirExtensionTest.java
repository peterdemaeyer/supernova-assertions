package su.pernova.matchers;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.util.function.Supplier;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.api.io.TempDir;

class DependOnTempDirExtensionTest {

	@TempDir
	// @Order does not work here, @TempDir always comes before any other extension.
	static Path tempDir;

	@RegisterExtension
	@Order(1)
	// Use a Supplier for delayed evaluation.
	static DependOnTempDirExtension dependOnTempDirExtension = new DependOnTempDirExtension(() -> tempDir);

	@RegisterExtension
	@Order(3)
	static DependOnExtensionExtension dependOnExtensionExtension = new DependOnExtensionExtension(dependOnTempDirExtension);

	@Test
	void test() {
		assertNotNull(dependOnTempDirExtension.tempDir);
	}

	static class DependOnTempDirExtension implements BeforeEachCallback, AfterEachCallback, BeforeAllCallback, AfterAllCallback {

		final Supplier<Path> tempDir;

		int state = 0;

		/**
		 * Use dependency injection with delayed evaluation, "provider" pattern.
		 * The supplier is the provider of the temp dir.
		 */
		DependOnTempDirExtension(Supplier<Path> tempDir) {
			this.tempDir = tempDir;
		}

		@Override
		public void afterEach(ExtensionContext context) throws Exception {
			System.out.println("afterEach:tempDir = " + tempDir.get());
			state = 4;
		}

		@Override
		public void beforeEach(ExtensionContext context) throws Exception {
			System.out.println("beforeEach:tempDir = " + tempDir.get());
			state = 3;
		}

		@Override
		public void afterAll(ExtensionContext context) throws Exception {
			System.out.println("afterAll:tempDir = " + tempDir.get());
			state = 2;
		}

		@Override
		public void beforeAll(ExtensionContext context) throws Exception {
			System.out.println("beforeAll:tempDir = " + tempDir.get());
			state = 1;
		}
	}

	static class DependOnExtensionExtension implements BeforeEachCallback, AfterEachCallback, BeforeAllCallback, AfterAllCallback {

		final DependOnTempDirExtension extension;

		DependOnExtensionExtension(DependOnTempDirExtension extension) {
			this.extension = extension;
		}

		@Override
		public void afterAll(ExtensionContext context) throws Exception {
			System.out.println("afterAll:state = " + extension.state);
		}

		@Override
		public void afterEach(ExtensionContext context) throws Exception {
			System.out.println("afterEach:state = " + extension.state);
		}

		@Override
		public void beforeAll(ExtensionContext context) throws Exception {
			System.out.println("beforeAll:state = " + extension.state);
		}

		@Override
		public void beforeEach(ExtensionContext context) throws Exception {
			System.out.println("beforeEach:state = " + extension.state);
		}
	}
}
