package internal.su.pernova.assertions.subjects;

import static java.nio.charset.Charset.defaultCharset;
import static java.nio.file.Files.createTempDirectory;
import static java.nio.file.Files.write;
import static java.nio.file.Files.writeString;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static su.pernova.assertions.AssertionTestUtils.assertThrowsAssertionErrorWithMessage;
import static su.pernova.assertions.Assertions.assertThat;
import static su.pernova.assertions.Matchers.equalTo;
import static su.pernova.assertions.Matchers.identicalTo;
import static su.pernova.assertions.Matchers.is;
import static su.pernova.assertions.Matchers.sameAs;
import static su.pernova.assertions.Matchers.thatOf;
import static su.pernova.assertions.Subjects.contentOf;

import java.io.File;
import java.net.URI;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Random;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class ContentOfTest {

	@Test
	void contentOfArrayIs() {
		final byte[] array = { 1, 2, 3 };
		assertThat(contentOf(array), is(array));
	}

	@Test
	void contentOfArrayIsIdenticalTo() {
		final byte[] array = { 55, 3, 1 };
		assertThat(contentOf(array), is(identicalTo(array)));
	}

	@Test
	void contentOfArrayIsSameAs() {
		final byte[] array = { 10, 9, 8 };
		assertThat(contentOf(array), is(sameAs(array)));
	}

	@Test
	void contentOfArrayIsThrows() {
		final byte[] array1 = new byte[] { 1, 2, 3 };
		final byte[] array2 = new byte[] { 1, 2, 3 };
		assertThrowsAssertionErrorWithMessage(
				() -> assertThat(contentOf(array1), is(array2)),
				String.format("expected that content of: byte[] is: %s", array2),
				String.format("but was: %s", array1)
		);
	}

	@Test
	void contentOfArrayIsIdenticalToThrows() {
		final byte[] array1 = new byte[] { 1, 2, 3 };
		final byte[] array2 = new byte[] { 1, 2, 3 };
		assertThrowsAssertionErrorWithMessage(
				() -> assertThat(contentOf(array1), is(identicalTo(array2))),
				String.format("expected that content of: byte[] is identical to: %s", array2),
				String.format("but was: %s", array1)
		);
	}

	@Test
	void contentOfArrayIsSameAsThrows() {
		final byte[] array1 = new byte[] { 1, 2, 3 };
		final byte[] array2 = new byte[] { 1, 2, 3 };
		assertThrowsAssertionErrorWithMessage(
				() -> assertThat(contentOf(array1), is(sameAs(array2))),
				String.format("expected that content of: byte[] is same as: %s", array2),
				String.format("but was: %s", array1)
		);
	}

	@Test
	void contentOfArrayIsEqualToThatOf() {
		final byte[] array1 = new byte[] { 1, 2, 3 };
		final byte[] array2 = new byte[] { 1, 2, 3 };
		assertThat(contentOf(array1), is(equalTo(thatOf(array2))));
	}

	@Test
	void contentOfArrayIsEqualToThatOfThrows() {
		final byte[] array1 = new byte[] { 1, 2, 3 };
		final byte[] array2 = new byte[] { 4, 5, 6 };
		assertThrowsAssertionErrorWithMessage(
				() -> assertThat(contentOf(array1), is(equalTo(thatOf(array2)))),
				String.format("expected that content of: byte[] is equal to that of: %s", Arrays.toString(array2)),
				String.format("but was: %s", Arrays.toString(array1))
		);
	}

	@Test
	void contentOfArrayIsEqualToThatOfPath(@TempDir Path tmpDir) throws Exception {
		final byte[] array = new byte[257];
		new Random().nextBytes(array);
		final Path path = write(tmpDir.resolve("content.bin"), array);
		assertThat(contentOf(array), is(equalTo(path)));
		assertThat(contentOf(path), is(equalTo(array)));
	}

	@Test
	void contentOfByteBufferDoesNotConsumeActualNorExpected() {
		final ByteBuffer actual = ByteBuffer.wrap(new byte[] { 1, 2, 3 });
		final ByteBuffer expected = ByteBuffer.wrap(new byte[] { 1, 2, 3 });
		assertThat(contentOf(actual), is(equalTo(expected)));
		assertThat(contentOf(actual), is(equalTo(new byte[] { 1, 2, 3 })));
		assertEquals(0, actual.position());
		assertEquals(3, actual.limit());
		assertEquals(0, expected.position());
		assertEquals(3, expected.limit());
	}

	@Test
	void contentOfUriIsEqualToThatOfCharSequence(@TempDir Path tmpDir) throws Exception {
		final CharSequence charSequence = "abc";
		final URI uri = writeString(tmpDir.resolve("abc.txt"), charSequence, defaultCharset()).toUri();
		assertThat(contentOf(uri), is(equalTo(thatOf(charSequence))));
		assertThat(contentOf(charSequence), is(equalTo(thatOf(uri))));
	}

	@Test
	void contentOfUrlIsEqualToThatOfCharBuffer(@TempDir Path tmpDir) throws Exception {
		final CharBuffer buffer = CharBuffer.wrap("abc");
		final URL url = writeString(tmpDir.resolve("abc.txt"), buffer, defaultCharset()).toUri().toURL();
		assertThat(contentOf(url), is(equalTo(thatOf(buffer))));
		assertThat(contentOf(buffer), is(equalTo(thatOf(url))));
	}

	@Test
	void contentOfFileIsEqualToThatOfCharArray(@TempDir Path tmpDir) throws Exception {
		char[] array = { 'a', 'b', 'c' };
		File file = writeString(tmpDir.resolve("abc.txt"), CharBuffer.wrap(array), defaultCharset()).toFile();
		assertThat(contentOf(file), is(equalTo(thatOf(array))));
		assertThat(contentOf(array), is(equalTo(thatOf(file))));
	}

	@Test
	void contentOfPathIsEqualToThatOfByteArray(@TempDir Path tmpDir) throws Exception {
		byte[] array = "abc".getBytes(defaultCharset());
		Path path = write(tmpDir.resolve("abc.txt"), array);
		assertThat(contentOf(path), is(equalTo(thatOf(array))));
		assertThat(contentOf(array), is(equalTo(thatOf(path))));
	}

	@Test
	void contentOfPathIsEqualToThatOfUriThrows(@TempDir Path tmpDir) throws Exception {
		Path path = writeString(tmpDir.resolve("abc.txt"), "abc", defaultCharset());
		URI uri = writeString(tmpDir.resolve("def.txt"), "def", defaultCharset()).toUri();
		assertThrowsAssertionErrorWithMessage(
				() -> assertThat(contentOf(path), is(equalTo(thatOf(uri)))),
				"expected that content of: "
		);
	}

	@Test
	void stringValue() {
		byte[] array = { 1, 2, 3 };
		assertEquals("contentOf(" + array + ")", contentOf(array).toString());
	}
}
