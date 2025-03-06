package internal.su.pernova.assertions.subjects;

import java.nio.charset.Charset;

import su.pernova.assertions.Matcher;

public class CharacterEncodedContent extends DefaultSubject {

	private final Charset charset;

	public CharacterEncodedContent(Object actual, Charset charset) {
		super(actual);
		this.charset = charset;
	}

	@Override
	public boolean match(Matcher matcher) {
		return matcher.match(actual != null ? charset.encode(actual.toString()) : null);
	}
}
