package internal.su.pernova.nio.channels;

import java.io.IOException;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.ReadableByteChannel;

public abstract class CloseableReadableByteChannel implements ReadableByteChannel {

	protected boolean closed;

	@Override
	public synchronized boolean isOpen() {
		return !closed;
	}

	@Override
	public synchronized void close() throws IOException {
		closed = true;
	}

	protected void throwIfClosed() throws ClosedChannelException {
		if (closed) {
			throw new ClosedChannelException();
		}
	}
}
