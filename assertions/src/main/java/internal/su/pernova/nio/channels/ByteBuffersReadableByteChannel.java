package internal.su.pernova.nio.channels;

import static java.util.Objects.requireNonNull;

import static internal.su.pernova.nio.NioUtils.putAsMuchAsPossible;

import java.io.IOException;
import java.nio.ByteBuffer;

public class ByteBuffersReadableByteChannel extends CloseableReadableByteChannel {

	private final ByteBuffer[] buffers;

	private int index;

	public ByteBuffersReadableByteChannel(ByteBuffer... buffers) {
		this.buffers = requireNonNull(buffers, "array of buffers is null");
	}

	@Override
	public synchronized int read(ByteBuffer dst) throws IOException {
		throwIfClosed();
		final int initialPosition = dst.position();
		while (index < buffers.length && dst.hasRemaining()) {
			final ByteBuffer src = buffers[index];
			putAsMuchAsPossible(dst, src);
			if (!src.hasRemaining()) {
				index++;
			}
		}
		final int finalPosition = dst.position();
		if (finalPosition > initialPosition) {
			return finalPosition - initialPosition;
		}
		return index < buffers.length ? 0 : -1;
	}
}
