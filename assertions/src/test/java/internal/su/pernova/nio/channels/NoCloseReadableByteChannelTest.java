package internal.su.pernova.nio.channels;

import static java.nio.ByteBuffer.allocate;
import static java.nio.ByteBuffer.wrap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.spy;

import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;

import org.junit.jupiter.api.Test;

class NoCloseReadableByteChannelTest implements ReadableByteChannelContractTest {

	private final ReadableByteChannel delegate = spy(new ByteBuffersReadableByteChannel());

	@Override
	public NoCloseReadableByteChannel newInstance() {
		return new NoCloseReadableByteChannel(delegate);
	}

	@Test
	@Override
	public void closureIsIdempotent() throws Exception {
		try (ReadableByteChannel channel = newInstance()) {
			channel.close();
			channel.close();
			assertTrue(channel.isOpen());
		}
	}

	@Override
	public void closureClosesChannel() throws Exception {
		ReadableByteChannelContractTest.super.closureClosesChannel();
	}

	@Override
	public void readingFromClosedChannelThrows() throws Exception {
		ReadableByteChannelContractTest.super.readingFromClosedChannelThrows();
	}

	@Override
	public void closureSupersedesEndOfStream() throws Exception {
		ReadableByteChannelContractTest.super.closureSupersedesEndOfStream();
	}

	@Test
	void closureDoesNotCloseDelegate() throws Exception {
		ByteBuffer content = wrap(new byte[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 });
		ByteBuffersReadableByteChannel delegate = new ByteBuffersReadableByteChannel(content.duplicate());
		NoCloseReadableByteChannel channel = new NoCloseReadableByteChannel(delegate);
		channel.close();
		ByteBuffer buffer = allocate(10);
		assertEquals(content.remaining(), channel.read(buffer));
		assertEquals(content, buffer.flip());
	}
}
