package internal.su.pernova.assertions.subjects;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static su.pernova.assertions.Assertions.assertThat;
import static su.pernova.assertions.Matchers.equalTo;
import static su.pernova.assertions.Matchers.is;
import static su.pernova.assertions.Subjects.contentOf;

import java.nio.ByteBuffer;

import org.junit.jupiter.api.Test;

class ContentOfTest {

	@Test
	void contentOfByteBufferDoesNotConsumeActualNorExpected() {
		ByteBuffer actual = ByteBuffer.wrap(new byte[] { 1, 2, 3 });
		ByteBuffer expected = ByteBuffer.wrap(new byte[] { 1, 2, 3 });
		assertThat(contentOf(actual), is(equalTo(expected)));
		assertThat(contentOf(actual), is(equalTo(new byte[] { 1, 2, 3 })));
		assertEquals(0, actual.position());
		assertEquals(3, actual.limit());
		assertEquals(0, expected.position());
		assertEquals(3, expected.limit());
	}
}
