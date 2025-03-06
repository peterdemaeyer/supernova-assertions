package internal.su.pernova.nio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static java.nio.ByteBuffer.allocate;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.ReadableByteChannel;

import org.junit.jupiter.api.Test;

public interface ReadableByteChannelContractTest {

	ReadableByteChannel newInstance() throws IOException;

	@Test
	default void newChannelIsOpen() throws Exception {
		try (ReadableByteChannel channel = newInstance()) {
			assertTrue(channel.isOpen());
		}
	}

	@Test
	default void closureClosesChannel() throws Exception {
		ReadableByteChannel channel = newInstance();
		channel.close();
		assertFalse(channel.isOpen());
	}

	@Test
	default void readingFromClosedChannelThrows() throws Exception {
		ReadableByteChannel channel = newInstance();
		channel.close();
		ByteBuffer buffer = allocate(10);
		assertThrows(ClosedChannelException.class, () -> channel.read(buffer));
	}

	@Test
	default void closureIsIdempotent() throws Exception {
		try (ReadableByteChannel channel = newInstance()) {
			channel.close();
			channel.close();
			assertFalse(channel.isOpen());
		}
	}

	@Test
	default void endOfStreamSupersedesEmptyBuffer() throws Exception {
		try (ReadableByteChannel channel = newInstance()) {
			// Exhaust the channel.
			for (ByteBuffer buffer = allocate(1000); channel.read(buffer) >= 0; buffer.clear()) {
			}
			// Verify that -1 "end of stream" supersedes 0-length empty buffer.
			assertEquals(-1, channel.read(allocate(0)));
		}
	}

	@Test
	default void closureSupersedesEndOfStream() throws Exception {
		ReadableByteChannel channel = newInstance();
		try {
			// Exhaust the channel.
			for (ByteBuffer buffer = allocate(1000); channel.read(buffer) >= 0; buffer.clear()) {
			}
		} finally {
			channel.close();
		}
		// Verify that ClosedChannelException supersedes -1 "end of stream".
		assertThrows(ClosedChannelException.class, () -> channel.read(allocate(0)));
	}
}
