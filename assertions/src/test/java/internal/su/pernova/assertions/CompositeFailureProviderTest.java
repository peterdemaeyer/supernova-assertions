package internal.su.pernova.assertions;

import static java.util.Collections.emptyList;

import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class CompositeFailureProviderTest {

	@Test
	void noProviders() {
		CompositeFailureProvider provider = new CompositeFailureProvider(emptyList());
		assertNull(provider.newFailure());
		assertNull(provider.newFailure("message", this, this));
	}
}
