package internal.su.pernova.assertions.matchers;

import static java.util.Objects.requireNonNull;

import su.pernova.assertions.Matcher;
import su.pernova.assertions.MatcherFactory;

public record AnonymousMatcherFactory(MatcherFactory delegatee) implements MatcherFactory {

	public AnonymousMatcherFactory(MatcherFactory delegatee) {
		this.delegatee = requireNonNull(delegatee, "delegatee is null");
	}

	@Override
	public Matcher create(Object expectedValue) {
		return anonymize(delegatee.create(expectedValue));
	}

	@Override
	public Matcher create(double expectedValue) {
		return anonymize(delegatee.create(expectedValue));
	}

	@Override
	public Matcher create(float expectedValue) {
		return anonymize(delegatee.create(expectedValue));
	}

	@Override
	public Matcher create(long expectedValue) {
		return anonymize(delegatee.create(expectedValue));
	}

	@Override
	public Matcher create(int expectedValue) {
		return anonymize(delegatee.create(expectedValue));
	}

	@Override
	public Matcher create(short expectedValue) {
		return anonymize(delegatee.create(expectedValue));
	}

	@Override
	public Matcher create(byte expectedValue) {
		return anonymize(delegatee.create(expectedValue));
	}

	@Override
	public Matcher create(char expectedValue) {
		return anonymize(delegatee.create(expectedValue));
	}

	@Override
	public Matcher create(boolean expectedValue) {
		return anonymize(delegatee.create(expectedValue));
	}

	@Override
	public Matcher create(Matcher expectation) {
		return anonymize(delegatee.create(expectation));
	}

	public static Matcher anonymize(Matcher delegatee) {
		return new AnonymousMatcher(delegatee);
	}
}
