package internal.su.pernova.assertions.subjects;

import static java.nio.channels.Channels.newChannel;
import static java.nio.charset.Charset.defaultCharset;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.function.Function;

import internal.su.pernova.nio.channels.ByteBuffersReadableByteChannel;
import internal.su.pernova.nio.channels.CharSequenceReadableByteChannel;
import su.pernova.assertions.Subject;

public class ContentOf extends Subject {

	public ContentOf(Object actual) {
		super(actual);
	}

	public static Function<Object, Object> transformation(Charset charset) {
		return object -> {
			try {
				if (object instanceof URL) {
					return newChannel(((URL) object).openStream());
				} else if (object instanceof URI) {
					return newChannel(((URI) object).toURL().openStream());
				} else if (object instanceof ByteBuffer) {
					return new ByteBuffersReadableByteChannel((ByteBuffer) object);
				} else if (object instanceof byte[]) {
					return new ByteBuffersReadableByteChannel(ByteBuffer.wrap((byte[]) object));
				} else if (object instanceof CharSequence) {
					return new CharSequenceReadableByteChannel((CharSequence) object, getCharsetOrDefault(charset));
				} else if (object instanceof char[]) {
					return new CharSequenceReadableByteChannel(CharBuffer.wrap((char[]) object), getCharsetOrDefault(charset));
				}
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			throw new IllegalArgumentException("not a content-capable object: " + object);
		};
	}

	private static Charset getCharsetOrDefault(Charset charset) {
		return charset != null ? charset : defaultCharset();
	}
}
