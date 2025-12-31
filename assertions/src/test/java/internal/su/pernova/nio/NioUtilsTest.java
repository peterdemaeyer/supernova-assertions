package internal.su.pernova.nio;

import static java.nio.ByteBuffer.allocate;
import static java.nio.ByteBuffer.wrap;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static internal.su.pernova.nio.NioUtils.duplicate;
import static internal.su.pernova.nio.NioUtils.putAsMuchAsPossible;

import java.nio.ByteBuffer;

import org.junit.jupiter.api.Test;

import su.pernova.design.UtilityContractTest;

class NioUtilsTest implements UtilityContractTest {

	@Test
	void puttingAsMuchAsPossibleWithLessThanCapacity() {
		ByteBuffer src = wrap(new byte[] { 1, 2, 3 });
		int initialSrcPosition = src.position();
		ByteBuffer dst = allocate(src.remaining() + 1);
		int initialDstPosition = dst.position();
		assertSame(dst, putAsMuchAsPossible(dst, src));
		assertTrue(dst.hasRemaining());
		assertEquals(dst.position() - initialDstPosition, src.position() - initialSrcPosition);
		assertEquals(wrap(new byte[] { 1, 2, 3 }), dst.flip());
	}

	@Test
	void puttingAsMuchAsPossibleWithCapacity() {
		ByteBuffer src = wrap(new byte[] { 1, 2, 3 });
		int initialSrcPosition = src.position();
		ByteBuffer dst = allocate(src.remaining());
		int initialDstPosition = dst.position();
		assertSame(dst, putAsMuchAsPossible(dst, src));
		assertFalse(dst.hasRemaining());
		assertEquals(dst.position() - initialDstPosition, src.position() - initialSrcPosition);
		assertEquals(wrap(new byte[] { 1, 2, 3 }), dst.flip());
	}

	@Test
	void puttingAsMuchAsPossibleWithMoreThanCapacity() {
		ByteBuffer src = wrap(new byte[] { 1, 2, 3 });
		int initialSrcPosition = src.position();
		ByteBuffer dst = allocate(src.remaining() - 1);
		int initialDstPosition = dst.position();
		assertSame(dst, putAsMuchAsPossible(dst, src));
		assertFalse(dst.hasRemaining());
		assertEquals(dst.position() - initialDstPosition, src.position() - initialSrcPosition);
		assertEquals(wrap(new byte[] { 1, 2 }), dst.flip());
	}

	@Test
	void duplicationOfByteBuffers() {
		ByteBuffer[] buffers = {
				wrap(new byte[] { 1, 2 }).position(1),
				wrap(new byte[] { 3, 4, 5 }).limit(2)
		};
		ByteBuffer[] duplicate = duplicate(buffers);
		ByteBuffer[] expected = {
				wrap(new byte[] { 2 }),
				wrap(new byte[] { 3, 4 })
		};
		assertArrayEquals(expected, duplicate);
		// Verify that setting the position of the duplicate does not change the position of the original.
		duplicate[0].position(0);
		assertEquals(1, buffers[0].position());
	}
}
