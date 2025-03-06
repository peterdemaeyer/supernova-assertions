package internal.su.pernova.nio;

import static java.util.Arrays.stream;

import java.nio.ByteBuffer;

public final class NioUtils {

	private NioUtils() {
	}

	/**
	 * Puts as much as possible from the source into the destination buffer, until either one is depleted first.
	 * This is a variant of {@link ByteBuffer#put(ByteBuffer)} that does <i>not</i> throw
	 * {@code BufferOverflowException} when the destination buffer overflows, but instead just puts as much as possible,
	 * potentially leaving some of the source buffer unconsumed.
	 *
	 * @param dst a destination buffer,not {@code null}.
	 * @param src a source buffer, not {@code null};
	 * @return the destination buffer, not {@code null}.
	 */
	public static ByteBuffer putAsMuchAsPossible(final ByteBuffer dst, final ByteBuffer src) {
		final int initialDstRemaining = dst.remaining();
		final int initialSrcRemaining = src.remaining();
		if (initialDstRemaining >= initialSrcRemaining) {
			return dst.put(src);
		}
		final int initialSrcLimit = src.limit();
		src.limit(src.position() + initialDstRemaining);
		dst.put(src);
		src.limit(initialSrcLimit);
		return dst;
	}

	/**
	 * Duplicates an array of byte buffers.
	 *
	 * @param buffers an array of byte buffers, not {@code null}.
	 * @return a duplicate array of duplicate byte buffers.
	 */
	public static ByteBuffer[] duplicate(ByteBuffer[] buffers) {
		return stream(buffers).map(ByteBuffer::duplicate).toArray(ByteBuffer[]::new);
	}
}
