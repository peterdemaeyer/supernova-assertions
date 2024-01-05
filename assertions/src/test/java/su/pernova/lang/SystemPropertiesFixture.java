package su.pernova.lang;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Properties;

import su.pernova.junit.jupiter.extension.Fixture;

public class SystemPropertiesFixture implements Fixture {

	private final Properties initialSystemProperties = new Properties();

	@Override
	public void before() {
		initialSystemProperties.clear();
		initialSystemProperties.putAll(System.getProperties());
	}

	@Override
	public void after() {
		final Properties systemProperties = System.getProperties();
		for (Iterator<Entry<Object, Object>> it = systemProperties.entrySet().iterator(); it.hasNext(); ) {
			Entry<Object, Object> entry = it.next();
			Object initialValue = initialSystemProperties.remove(entry.getKey());
			if (initialValue != null) {
				entry.setValue(initialValue);
			} else {
				it.remove();
			}
		}
		systemProperties.putAll(initialSystemProperties);
		initialSystemProperties.clear();
	}
}
