package internal.su.pernova.assertions.matchers.nio.channels;

import static java.nio.ByteBuffer.wrap;
import static java.nio.channels.Channels.newChannel;
import static java.nio.charset.Charset.defaultCharset;
import static java.nio.file.Files.newByteChannel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Path;

import internal.su.pernova.assertions.matchers.DelegatingMatcher;
import internal.su.pernova.nio.channels.ByteBuffersReadableByteChannel;
import internal.su.pernova.nio.channels.CharSequenceReadableByteChannel;
import su.pernova.assertions.Matcher;

public class ContentMatcher extends DelegatingMatcher {

	public ContentMatcher(Matcher delegate) {
		super("", delegate);
	}

	@Override
	public boolean match(Object actual) {
		try (ReadableByteChannel channel = openChannel(actual)) {
			if (channel != null) {
				return delegate.match(channel);
			}
			return false;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private static ReadableByteChannel openChannel(Object actual) throws IOException {
		if (actual instanceof URL) {
			return newChannel(((URL) actual).openStream());
		} else if (actual instanceof URI) {
			return newChannel(((URI) actual).toURL().openStream());
		} else if (actual instanceof ByteBuffer) {
			return new ByteBuffersReadableByteChannel(((ByteBuffer) actual).duplicate());
		} else if (actual instanceof byte[]) {
			return new ByteBuffersReadableByteChannel(wrap((byte[]) actual));
		} else if (actual instanceof Path) {
			return newByteChannel((Path) actual);
		} else if (actual instanceof File) {
			// No need to close the FileInputStream. Closing the channel closes all resources.
			return new FileInputStream((File) actual).getChannel();
		} else if (actual instanceof CharSequence) {
			return new CharSequenceReadableByteChannel((CharSequence) actual, defaultCharset());
		} else if (actual instanceof char[]) {
			return new CharSequenceReadableByteChannel(CharBuffer.wrap((char[]) actual), defaultCharset());
		}
		return null;
	}
}
