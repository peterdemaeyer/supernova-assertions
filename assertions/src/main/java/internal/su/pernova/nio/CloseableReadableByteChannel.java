package internal.su.pernova.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.ReadableByteChannel;

public abstract class CloseableReadableByteChannel implements ReadableByteChannel {

	protected boolean closed;

	@Override
	public synchronized boolean isOpen() {
		return !closed;
	}

	@Override
	public synchronized int read(ByteBuffer dst) throws IOException {
		if (closed) {
			throw new ClosedChannelException();
		}
		return -1;
	}

	@Override
	public synchronized void close() throws IOException {
		closed = true;
	}
}
