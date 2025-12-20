package internal.su.pernova.assertions.matchers;

import static java.util.Objects.requireNonNull;

import su.pernova.assertions.Matcher;
import su.pernova.assertions.MatcherFactory;

public record IsFactory(PromptedNamedMatcher prototype) implements MatcherFactory {

	public IsFactory(PromptedNamedMatcher prototype) {
		this.prototype = requireNonNull(prototype, "prototype is null");
	}

	@Override
	public Matcher create(Object expectedValue) {
		return new IsObject(prototype.name(), prototype.prompt(), expectedValue);
	}

	@Override
	public Matcher create(double expectedValue) {
		return new IsDouble(prototype.name(), prototype.prompt(), expectedValue);
	}

	@Override
	public Matcher create(float expectedValue) {
		return new IsFloat(prototype.name(), prototype.prompt(), expectedValue);
	}

	@Override
	public Matcher create(long expectedValue) {
		return new IsLong(prototype.name(), prototype.prompt(), expectedValue);
	}

	@Override
	public Matcher create(int expectedValue) {
		return new IsInt(prototype.name(), prototype.prompt(), expectedValue);
	}

	@Override
	public Matcher create(short expectedValue) {
		return new IsShort(prototype.name(), prototype.prompt(), expectedValue);
	}

	@Override
	public Matcher create(byte expectedValue) {
		return new IsByte(prototype.name(), prototype.prompt(), expectedValue);
	}

	@Override
	public Matcher create(char expectedValue) {
		return new IsChar(prototype.name(), prototype.prompt(), expectedValue);
	}

	@Override
	public Matcher create(boolean expectedValue) {
		return new IsBoolean(prototype.name(), prototype.prompt(), expectedValue);
	}
}
