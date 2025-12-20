package internal.su.pernova.assertions.subjects;

import static java.nio.charset.Charset.defaultCharset;

import java.nio.charset.Charset;

import su.pernova.assertions.Context;
import su.pernova.assertions.Descriptor;
import su.pernova.assertions.Matcher;

public class ContentOf extends ObjectSubject {

	private final Charset charset;

	public ContentOf(Object actualValue, Charset charset) {
		super(null, actualValue);
		this.charset = getCharsetOrDefault(charset);
	}

	@Override
	public boolean match(Matcher matcher) {
		return super.match(matcher);
	}

	public ContentOf contextualize(Descriptor descriptor) {
		return Context.set(this).setDescriptor(descriptor).get();
	}

	private static Charset getCharsetOrDefault(Charset charset) {
		return charset != null ? charset : defaultCharset();
	}
}
