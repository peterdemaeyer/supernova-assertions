package internal.su.pernova.assertions.matchers;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

import su.pernova.assertions.Context;
import su.pernova.assertions.Matcher;
import su.pernova.assertions.MatcherFactory;

public class Is extends ForwardingMatcher {

	private static final ReferenceQueue<MatcherFactory> REFERENCE_QUEUE = new ReferenceQueue<>();

	private static final Map<CharSequence, NamedWeakReference> MATCHER_FACTORIES_BY_NAME = new HashMap<>();

	public Is(CharSequence name, Matcher destination) {
		super(name, destination, Is.getMatcherFactory(name));
	}

	public Is(Matcher destination) {
		this("is", destination);
	}

	public synchronized static MatcherFactory getMatcherFactory(CharSequence name) {
		for (NamedWeakReference reference = (NamedWeakReference) REFERENCE_QUEUE.poll(); reference != null; reference = (NamedWeakReference) REFERENCE_QUEUE.poll()) {
			MATCHER_FACTORIES_BY_NAME.remove(reference.name);
		}
		return MATCHER_FACTORIES_BY_NAME.computeIfAbsent(name, ignored -> new NamedWeakReference(name, new MatcherFactory() {

			@Override
			public Matcher create(Object expectedValue) {
				return new IsObject(name, true, expectedValue);
			}

			@Override
			public Matcher create(double expectedValue) {
				return new IsDouble(name, true, expectedValue);
			}

			@Override
			public Matcher create(float expectedValue) {
				return new IsFloat(name, true, expectedValue);
			}

			@Override
			public Matcher create(long expectedValue) {
				return new IsLong(name, true, expectedValue);
			}

			@Override
			public Matcher create(int expectedValue) {
				return new IsInt(name, true, expectedValue);
			}

			@Override
			public Matcher create(short expectedValue) {
				return new IsShort(name, true, expectedValue);
			}

			@Override
			public Matcher create(byte expectedValue) {
				return new IsByte(name, true, expectedValue);
			}

			@Override
			public Matcher create(char expectedValue) {
				return new IsChar(name, true, expectedValue);
			}

			@Override
			public Matcher create(boolean expectedValue) {
				return new IsBoolean(name, true, expectedValue);
			}

			public Matcher create(Matcher matcher) {
				return new Is(name, matcher);
			}
		}, REFERENCE_QUEUE)).get();
	}
}
