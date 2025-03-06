package internal.su.pernova.nio;

import static internal.su.pernova.nio.NioUtils.putAsMuchAsPossible;
import static java.lang.Math.round;
import static java.nio.ByteBuffer.allocate;
import static java.nio.CharBuffer.wrap;
import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;

public class CharSequenceReadableByteChannel extends CloseableReadableByteChannel {

	private final CharBuffer charBuffer;

	private final CharsetEncoder charsetEncoder;

	private final ByteBuffer pendingEncoded;

	public CharSequenceReadableByteChannel(CharSequence charSequence, Charset charset) {
		charBuffer = wrap(requireNonNull(charSequence, "char sequence is null"));
		charsetEncoder = requireNonNull(charset, "charset is null").newEncoder();
		pendingEncoded = allocate(round(charsetEncoder.maxBytesPerChar()));
		pendingEncoded.limit(0);
	}

	@Override
	public synchronized int read(ByteBuffer dst) throws IOException {
		super.read(dst);
		final int initialDstPosition = dst.position();
		if (pendingEncoded.hasRemaining()) {
			putAsMuchAsPossible(dst, pendingEncoded);
			if (pendingEncoded.hasRemaining()) {
				return dst.position() - initialDstPosition;
			}
			pendingEncoded.position(0);
			pendingEncoded.limit(0);
		}
		if (charBuffer.hasRemaining()) {
			CoderResult result = charsetEncoder.encode(charBuffer, dst, true);
			if (dst.hasRemaining() && result.isOverflow()) {
				// There is still room in dst but not enough to encode a whole character.
				// Use pendingEncoded to encode a whole character, and put as much of it as possible in dst.
				pendingEncoded.clear();
				result = charsetEncoder.encode(charBuffer, pendingEncoded, true);
				pendingEncoded.flip();
				putAsMuchAsPossible(dst, pendingEncoded);
			}
			if (result.isError()) {
				result.throwException();
			}
			return dst.position() - initialDstPosition;
		}
		return -1;
	}
}
