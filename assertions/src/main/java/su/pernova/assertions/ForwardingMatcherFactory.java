package su.pernova.assertions;

import static java.util.Objects.requireNonNull;

import java.util.function.Function;

import internal.su.pernova.assertions.matchers.ForwardingMatcher;

record ForwardingMatcherFactory(CharSequence name, MatcherFactory destination, Function<Matcher, Matcher> forwardingFunction) implements MatcherFactory {

	ForwardingMatcherFactory(CharSequence name, MatcherFactory destination, Function<Matcher, Matcher> forwardingFunction) {
		this.name = name;
		this.destination = requireNonNull(destination, "destination is null");
		this.forwardingFunction = requireNonNull(forwardingFunction, "forwarding function is null");
	}

	@Override
	public Matcher create(Object expectedValue) {
		return new ForwardingMatcher(name, destination.create(expectedValue));
	}

	@Override
	public Matcher create(double expectedValue) {
		return new ForwardingMatcher(name, destination.create(expectedValue));
	}

	@Override
	public Matcher create(float expectedValue) {
		return new ForwardingMatcher(name, destination.create(expectedValue));
	}

	@Override
	public Matcher create(long expectedValue) {
		return new ForwardingMatcher(name, destination.create(expectedValue));
	}

	@Override
	public Matcher create(int expectedValue) {
		return new ForwardingMatcher(name, destination.create(expectedValue));
	}

	@Override
	public Matcher create(short expectedValue) {
		return new ForwardingMatcher(name, destination.create(expectedValue));
	}

	@Override
	public Matcher create(byte expectedValue) {
		return new ForwardingMatcher(name, destination.create(expectedValue));
	}

	@Override
	public Matcher create(char expectedValue) {
		return new ForwardingMatcher(name, destination.create(expectedValue));
	}

	@Override
	public Matcher create(boolean expectedValue) {
		return new ForwardingMatcher(name, destination.create(expectedValue));
	}

	@Override
	public Matcher create(Matcher expectation) {
		return new ForwardingMatcher(name, expectation);
	}
}
