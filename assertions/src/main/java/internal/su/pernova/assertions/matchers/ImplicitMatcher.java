package internal.su.pernova.assertions.matchers;

import internal.su.pernova.assertions.ImplicitDescribable;
import su.pernova.assertions.Context;
import su.pernova.assertions.Matcher;

public class ImplicitMatcher extends ImplicitDescribable<Matcher> implements Matcher {

	public ImplicitMatcher(Matcher destination) {
		super(destination);
	}

	@Override
	public boolean match(Object actualValue) {
		return destination.match(actualValue);
	}
//
//	@Override
//	public Matcher and(Matcher matcher) {
//		return destination.and(matcher);
//	}
//
//	@Override
//	public Matcher and(Object expectedValue) {
//		return destination.and(expectedValue);
//	}
//
//	@Override
//	public Matcher and(double expectedValue) {
//		return destination.and(expectedValue);
//	}
//
//	@Override
//	public Matcher and(float expectedValue) {
//		return destination.and(expectedValue);
//	}
//
//	@Override
//	public Matcher and(long expectedValue) {
//		return destination.and(expectedValue);
//	}
//
//	@Override
//	public Matcher and(int expectedValue) {
//		return destination.and(expectedValue);
//	}
//
//	@Override
//	public Matcher and(short expectedValue) {
//		return destination.and(expectedValue);
//	}
//
//	@Override
//	public Matcher and(byte expectedValue) {
//		return destination.and(expectedValue);
//	}
//
//	@Override
//	public Matcher and(char expectedValue) {
//		return destination.and(expectedValue);
//	}
//
//	@Override
//	public Matcher and(boolean expectedValue) {
//		return destination.and(expectedValue);
//	}
//
//	@Override
//	public Matcher or(Matcher matcher) {
//		return destination.or(matcher);
//	}
//
//	@Override
//	public Matcher or(Object expectedValue) {
//		return destination.or(expectedValue);
//	}
//
//	@Override
//	public Matcher or(double expectedValue) {
//		return destination.or(expectedValue);
//	}
//
//	@Override
//	public Matcher or(float expectedValue) {
//		return destination.or(expectedValue);
//	}
//
//	@Override
//	public Matcher or(long expectedValue) {
//		return destination.or(expectedValue);
//	}
//
//	@Override
//	public Matcher or(int expectedValue) {
//		return destination.or(expectedValue);
//	}
//
//	@Override
//	public Matcher or(short expectedValue) {
//		return destination.or(expectedValue);
//	}
//
//	@Override
//	public Matcher or(byte expectedValue) {
//		return destination.or(expectedValue);
//	}
//
//	@Override
//	public Matcher or(char expectedValue) {
//		return destination.or(expectedValue);
//	}
//
//	@Override
//	public Matcher or(boolean expectedValue) {
//		return destination.or(expectedValue);
//	}

	@Override
	public Matcher contextualize(Context context) {
		return context.contextualize(this, destination, ImplicitMatcher::new);
	}
}
