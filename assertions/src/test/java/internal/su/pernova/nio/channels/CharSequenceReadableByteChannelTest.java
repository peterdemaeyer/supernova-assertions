package internal.su.pernova.nio.channels;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static java.nio.ByteBuffer.allocate;
import static java.nio.ByteBuffer.wrap;
import static java.nio.channels.Channels.newChannel;
import static java.nio.charset.StandardCharsets.US_ASCII;
import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;
import java.nio.charset.UnmappableCharacterException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class CharSequenceReadableByteChannelTest implements ReadableByteChannelContractTest {

	@Override
	public CharSequenceReadableByteChannel newInstance() {
		return new CharSequenceReadableByteChannel("", UTF_8);
	}

	@Test
	void constructionThrowsWhenParameterIsNull() {
		assertEquals("char sequence is null",
				assertThrows(NullPointerException.class,
						() -> new CharSequenceReadableByteChannel(null, UTF_8))
						.getMessage());
		assertEquals("charset is null",
				assertThrows(NullPointerException.class,
						() -> new CharSequenceReadableByteChannel("", null))
						.getMessage());
	}

	@ParameterizedTest
	@ValueSource(ints = { 1, 2, 3, 8, 17, 128, 256, 257 })
	void readingCharacterEncodedContent(int bufferCapacity) throws Exception {
		String charSequence = "€ is the symbol for the Euro.";
		ByteArrayOutputStream collector = new ByteArrayOutputStream();
		WritableByteChannel outputChannel = newChannel(collector);
		try (CharSequenceReadableByteChannel channel = new CharSequenceReadableByteChannel(charSequence, UTF_8)) {
			for (ByteBuffer buffer = allocate(bufferCapacity); channel.read(buffer) != -1; buffer.clear()) {
				outputChannel.write(buffer.flip());
			}
		}
		assertEquals(UTF_8.encode(charSequence), wrap(collector.toByteArray()));
	}

	@Test
	void readingOfUnmappableCharacter() throws Exception {
		// '€' is an unmappable character in US-ASCII.
		String charSequence = "€";
		try (CharSequenceReadableByteChannel channel = new CharSequenceReadableByteChannel(charSequence, US_ASCII)) {
			assertThrows(UnmappableCharacterException.class, () -> channel.read(allocate(10)));
		}
	}
}
