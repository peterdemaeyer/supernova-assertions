package internal.su.pernova.assertions.matchers.content;

import static java.nio.ByteBuffer.allocate;
import static java.nio.ByteBuffer.wrap;
import static java.nio.channels.Channels.newChannel;
import static java.nio.charset.Charset.defaultCharset;
import static java.nio.file.Files.newByteChannel;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Path;

import internal.su.pernova.assertions.matchers.ObjectMatcher;
import internal.su.pernova.nio.channels.ByteBuffersReadableByteChannel;
import internal.su.pernova.nio.channels.CharSequenceReadableByteChannel;

public class ContentEqualTo extends ObjectMatcher {

	public ContentEqualTo(CharSequence name, Object expectedValue) {
		super(name, true, expectedValue);
	}

	@Override
	public boolean match(Object actualValue) {
		try (ReadableByteChannel expectedChannel = openChannel(expectedValue);
			 ReadableByteChannel actualChannel = openChannel(actualValue)) {
			if (expectedChannel == null) {
				return actualChannel == null;
			}
			if (actualChannel != null) {
				ByteBuffer expectedBuffer = allocate(1024).flip();
				ByteBuffer actualBuffer = allocate(1024).flip();
				while (true) {
					int expectedResult = 0;
					if (!expectedBuffer.hasRemaining()) {
						expectedBuffer.clear();
						expectedResult = expectedChannel.read(expectedBuffer);
						if (expectedResult == 0) {
							Thread.yield();
							continue;
						}
						expectedBuffer.flip();
					}
					int actualResult = 0;
					if (!actualBuffer.hasRemaining()) {
						actualBuffer.clear();
						actualResult = actualChannel.read(actualBuffer);
						actualBuffer.flip();
						if (actualResult == 0) {
							Thread.yield();
							continue;
						}
					}
					if (expectedResult == -1) {
						return actualResult == -1;
					}
					if (expectedBuffer.get() != actualBuffer.get()) {
						return false;
					}
				}
			}
			return false;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private static ReadableByteChannel openChannel(Object value) throws IOException {
		if (value instanceof URL) {
			return newChannel(((URL) value).openStream());
		} else if (value instanceof URI) {
			return newChannel(((URI) value).toURL().openStream());
		} else if (value instanceof ByteBuffer) {
			return new ByteBuffersReadableByteChannel(((ByteBuffer) value).duplicate());
		} else if (value instanceof byte[]) {
			return new ByteBuffersReadableByteChannel(wrap((byte[]) value));
		} else if (value instanceof Path) {
			return newByteChannel((Path) value);
		} else if (value instanceof File) {
			return newByteChannel(((File) value).toPath());
		} else if (value instanceof CharSequence) {
			return new CharSequenceReadableByteChannel((CharSequence) value, defaultCharset());
		} else if (value instanceof char[]) {
			return new CharSequenceReadableByteChannel(CharBuffer.wrap((char[]) value), defaultCharset());
		}
		return null;
	}
}
