package su.pernova.assertions;

public class ContextSensitiveMatcher implements Matcher {

	public ContextSensitiveMatcher() {
	}

	@Override
	public boolean match(Object actualValue) {
		throw new NoContextException();
	}

	@Override
	public String toString() {
		return "...";
	}
}
