package su.pernova.assertions;

import static java.util.Objects.requireNonNull;

import internal.su.pernova.assertions.matchers.ImplicitMatcher;

public class ImplicitMatcherFactory implements MatcherFactory {

	private final MatcherFactory destination;

	public ImplicitMatcherFactory(MatcherFactory destination) {
		this.destination = requireNonNull(destination, "destination is null");
	}

	@Override
	public Matcher create(Object expectedValue) {
		return imply(destination.create(expectedValue));
	}

	@Override
	public Matcher create(double expectedValue) {
		return imply(destination.create(expectedValue));
	}

	@Override
	public Matcher create(float expectedValue) {
		return imply(destination.create(expectedValue));
	}

	@Override
	public Matcher create(long expectedValue) {
		return imply(destination.create(expectedValue));
	}

	@Override
	public Matcher create(int expectedValue) {
		return imply(destination.create(expectedValue));
	}

	@Override
	public Matcher create(short expectedValue) {
		return imply(destination.create(expectedValue));
	}

	@Override
	public Matcher create(byte expectedValue) {
		return imply(destination.create(expectedValue));
	}

	@Override
	public Matcher create(char expectedValue) {
		return imply(destination.create(expectedValue));
	}

	@Override
	public Matcher create(boolean expectedValue) {
		return imply(destination.create(expectedValue));
	}

	private static Matcher imply(Matcher matcher) {
		return new ImplicitMatcher(matcher);
	}
}
