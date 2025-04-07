package internal.su.pernova.nio.channels;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;

public class NoCloseReadableByteChannel implements ReadableByteChannel {

	private final ReadableByteChannel delegate;

	public NoCloseReadableByteChannel(ReadableByteChannel delegate) {
		this.delegate = requireNonNull(delegate, "delegate is null");
	}

	@Override
	public int read(ByteBuffer destination) throws IOException {
		return delegate.read(destination);
	}

	@Override
	public boolean isOpen() {
		return delegate.isOpen();
	}

	@Override
	public void close() {
	}
}
