package internal.su.pernova.assertions.subjects;

import static java.lang.System.arraycopy;
import static java.nio.ByteBuffer.allocate;
import static java.nio.ByteBuffer.wrap;
import static java.nio.channels.Channels.newChannel;
import static java.nio.channels.Channels.newInputStream;
import static java.nio.charset.Charset.defaultCharset;
import static java.nio.file.Files.newByteChannel;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.WeakHashMap;

import internal.su.pernova.nio.channels.ByteBuffersReadableByteChannel;
import internal.su.pernova.nio.channels.CharSequenceReadableByteChannel;
import su.pernova.assertions.Describable;
import su.pernova.assertions.Description;

public class Content implements Describable {

	private static final Map<Object, Content> CONTENTS_BY_VALUE = new WeakHashMap<>();

	private final Object value;

	private Content(Object value) {
		this.value = value;
	}

	public ReadableByteChannel openChannel() throws IOException {
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
		throw new IllegalArgumentException(String.valueOf(value));
	}

	@Override
	public String toString() {
		return String.valueOf(value);
	}

	public static Content of(Object value) {
		return CONTENTS_BY_VALUE.computeIfAbsent(value, Content::new);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(value);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj instanceof Content that) {
			return contentEquals(this, that);
		}
		return false;
	}

	private static boolean contentEquals(Content content1, Content content2) {
		try (ReadableByteChannel channel1 = content1.openChannel();
			 ReadableByteChannel channel2 = content2.openChannel()) {
			if (channel1 == null) {
				return channel2 == null;
			}
			if (channel2 != null) {
				ByteBuffer buffer1 = allocate(1024).flip();
				ByteBuffer buffer2 = allocate(1024).flip();
				while (true) {
					int result1 = 0;
					if (!buffer1.hasRemaining()) {
						buffer1.clear();
						result1 = channel1.read(buffer1);
						if (result1 == 0) {
							Thread.yield();
							continue;
						}
						buffer1.flip();
					}
					int result2 = 0;
					if (!buffer2.hasRemaining()) {
						buffer2.clear();
						result2 = channel2.read(buffer2);
						buffer2.flip();
						if (result2 == 0) {
							Thread.yield();
							continue;
						}
					}
					if (result1 == -1) {
						return result2 == -1;
					}
					if (buffer1.get() != buffer2.get()) {
						return false;
					}
				}
			}
			return false;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Description describe(Description description) {
		CharSequence text = null;
		if (value instanceof CharSequence charSequence) {
			text = charSequence;
		} else if (value instanceof char[] array) {
			text = new String(array);
		} else if (value instanceof byte[] array) {
			text = new String(array, defaultCharset());
		}
		if (text != null) {
			if (text.length() > 100) {
				text = text.subSequence(0, 100) + "...";
			}
		} else {
			try (ReadableByteChannel channel = openChannel()) {
				byte[] buffer = new byte[101];
				try (InputStream in = newInputStream(channel)) {
					int length = in.read(buffer);
					if (length < buffer.length) {
						byte[] newBuffer = new byte[buffer.length];
						arraycopy(buffer, 0, newBuffer, 0, length);
						buffer = newBuffer;
					}
					text = Arrays.toString(buffer);
					if (length > 100) {
						text = text.subSequence(0, text.length() - 1) + ", ...]";
					}
				}
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		return description.appendArgument(value).appendText("which is").appendPrompt().appendText(text);
	}

	@Override
	public Description describeMismatch(Description mismatchDescription) {
		return describe(mismatchDescription);
	}
}
