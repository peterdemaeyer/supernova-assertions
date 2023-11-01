package internal.su.pernova.assertions;

import static java.util.Collections.emptyList;

import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class CompositeFailureProviderTest {

	@Test
	void noProviders() {
		CompositeFailureProvider compositeFailureProvider = new CompositeFailureProvider(emptyList());
		assertNull(compositeFailureProvider.newAssertionFailure());
		assertNull(compositeFailureProvider.newAssertionFailure("message", this, this));
	}
}
