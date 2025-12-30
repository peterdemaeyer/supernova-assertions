package su.pernova.assertions;

import static java.util.Objects.requireNonNull;

import internal.su.pernova.assertions.matchers.AnonymousMatcher;

public class AnonymousMatcherFactory implements MatcherFactory {

	private final MatcherFactory delegatee;

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
	public Matcher create(Matcher matcher) {
		return anonymize(delegatee.create(matcher));
	}

	private static Matcher anonymize(Matcher matcher) {
		return new AnonymousMatcher(matcher);
	}
}
