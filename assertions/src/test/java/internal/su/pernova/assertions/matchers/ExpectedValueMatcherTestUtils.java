package internal.su.pernova.assertions.matchers;

import static su.pernova.assertions.Matchers.is;

import java.util.stream.Stream;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.support.ParameterDeclarations;

public final class ExpectedValueMatcherTestUtils {

	private ExpectedValueMatcherTestUtils() {
	}

	static class IsPrimitiveValueArgumentsProvider implements ArgumentsProvider {

		@Override
		public Stream<? extends Arguments> provideArguments(ParameterDeclarations parameters, ExtensionContext context) throws Exception {
			return Stream.of(
					Arguments.of(is(0)),
					Arguments.of(is(0L)),
					Arguments.of(is((short) 0)),
					Arguments.of(is((byte) 0)),
					Arguments.of(is(0d)),
					Arguments.of(is(0f)),
					Arguments.of(is(false)),
					Arguments.of(is('0'))
			);
		}
	}
}
