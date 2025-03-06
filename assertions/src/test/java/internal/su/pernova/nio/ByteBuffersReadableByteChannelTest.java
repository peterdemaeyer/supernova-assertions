package internal.su.pernova.nio;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static java.nio.ByteBuffer.allocate;
import static java.nio.ByteBuffer.wrap;

import java.nio.ByteBuffer;
import java.util.Random;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ByteBuffersReadableByteChannelTest implements ReadableByteChannelContractTest {

	@Override
	public ByteBuffersReadableByteChannel newInstance() {
		return new ByteBuffersReadableByteChannel();
	}

	@Test
	void readingWithEmptyBuffer() throws Exception {
		try (ByteBuffersReadableByteChannel channel = new ByteBuffersReadableByteChannel(allocate(10))) {
			// Read empty buffer while channel not exhausted -> 0.
			assertEquals(0, channel.read(allocate(0)));
			// Exhaust channel.
			assertEquals(10, channel.read(allocate(10)));
			// Read empty buffer while channel exhausted -> -1.
			assertEquals(-1, channel.read(allocate(0)));
		}
	}

	@ParameterizedTest
	@ValueSource(ints = { 1, 2, 3, 8, 17, 128, 256, 257 })
	void readingAllContent(int bufferCapacity) throws Exception {
		byte[] content = new byte[128];
		new Random().nextBytes(content);
		ByteBuffer collector = allocate(1000);
		try (ByteBuffersReadableByteChannel channel = new ByteBuffersReadableByteChannel(wrap(content))) {
			ByteBuffer buffer = allocate(bufferCapacity);
			while (channel.read(buffer) != -1) {
				collector.put(buffer.flip());
				buffer.clear();
			}
		}
		assertEquals(wrap(content), collector.flip());
	}
}
