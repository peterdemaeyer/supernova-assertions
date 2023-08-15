package internal.su.pernova.assertions.matchers;

public class Nan extends DescriptiveMatcher {

	private static class Singleton {

		private static final Nan INSTANCE = new Nan();
	}

	private Nan() {
		super("NaN");
	}

	@Override
	public boolean match(Object actual) {
		if (actual instanceof Double) {
			return ((Double) actual).isNaN();
		}
		if (actual instanceof Float) {
			return ((Float) actual).isNaN();
		}
		return false;
	}

	public static Nan getInstance() {
		return Singleton.INSTANCE;
	}
}
