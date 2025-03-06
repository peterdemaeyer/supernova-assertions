package internal.su.pernova.assertions.matchers;

import su.pernova.assertions.Matcher;

public interface Context {

	Matcher evaluate(Object expected);

	default Matcher evaluate(double expected) {
		return evaluate((Object) expected);
	}

	default Matcher evaluate(float expected) {
		return evaluate((Object) expected);
	}

	default Matcher evaluate(long expected) {
		return evaluate((Object) expected);
	}

	default Matcher evaluate(int expected) {
		return evaluate((Object) expected);
	}

	default Matcher evaluate(short expected) {
		return evaluate((Object) expected);
	}

	default Matcher evaluate(byte expected) {
		return evaluate((Object) expected);
	}

	default Matcher evaluate(char expected) {
		return evaluate((Object) expected);
	}

	default Matcher evaluate(boolean expected) {
		return evaluate((Object) expected);
	}

	static Matcher provideContext(Matcher matcher, Context context) {
		return OngoingContext.getInstance().provideContext(matcher, context);
	}

	static Matcher fork(Matcher parent, Matcher matcher) {
		return OngoingContext.getInstance().fork(parent, matcher);
	}

	static Matcher evaluateNowAndFork(Matcher parent, ContextSensitiveMatcher matcher) {
		return OngoingContext.getInstance().forkAndEvaluateNow(parent, matcher);
	}

	static Matcher evaluateLater(ContextSensitiveMatcher matcher) {
		return OngoingContext.getInstance().evaluateLater(matcher);
	}

	static void clear() {
		OngoingContext.clear();
	}
}
