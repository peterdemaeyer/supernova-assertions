package internal.su.pernova.assertions.matchers;

public class Nan extends PromptedNamedMatcher {

	private static class Singleton {

		private static final Nan INSTANCE = new Nan();
	}

	private Nan() {
		super("NaN", false);
	}

	@Override
	public boolean match(Object actualValue) {
		if (actualValue instanceof Double) {
			return ((Double) actualValue).isNaN();
		}
		if (actualValue instanceof Float) {
			return ((Float) actualValue).isNaN();
		}
		return false;
	}

	public static Nan getInstance() {
		return Singleton.INSTANCE;
	}
}
