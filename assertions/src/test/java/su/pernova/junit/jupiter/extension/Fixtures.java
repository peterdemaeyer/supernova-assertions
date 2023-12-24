package su.pernova.junit.jupiter.extension;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.Extension;
import org.junit.jupiter.api.extension.ExtensionContext;

import su.pernova.lang.Fixture;

public interface Fixtures {

	interface EachCallback extends BeforeEachCallback, AfterEachCallback {
	}

	static Extension forEach(Fixture fixture) {
		return new EachCallback() {

			@Override
			public void beforeEach(ExtensionContext context) throws Exception {
				fixture.before();
			}

			@Override
			public void afterEach(ExtensionContext context) throws Exception {
				fixture.after();
			}
		};
	}

	interface AllCallback extends BeforeAllCallback, AfterAllCallback {
	}

	static Extension forAll(Fixture fixture) {
		return new AllCallback() {

			@Override
			public void beforeAll(ExtensionContext context) throws Exception {
				fixture.before();
			}

			@Override
			public void afterAll(ExtensionContext context) throws Exception {
				fixture.after();
			}
		};
	}
}
