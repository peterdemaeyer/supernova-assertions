package su.pernova.design;

public interface ContractTest {

	default Class<?> getClassUnderTest() throws Exception {
		String className = getClass().getName();
		if (className.endsWith("Test")) {
			className = className.substring(0, className.length() - 4);
		}
		return Class.forName(className);
	}
}
