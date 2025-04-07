package su.pernova.assertions;

import static java.util.Arrays.stream;
import static java.util.Objects.requireNonNull;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.function.Function;
import java.util.function.Supplier;

public final class Context {

	private static final Supplier<Function<Object, Object>> IDENTITY_TRANSFORMATION_ANSWER = () -> object -> object;

	private Context() {
	}

	private static class Singleton {

		private static final Instance INSTANCE = new Instance();
	}

	private static Instance getInstance() {
		return Singleton.INSTANCE;
	}

	private static class Instance {

		final Map<Object, Function<Object, Object>> actualTransformationsByProvider = new WeakHashMap<>();

		final Map<Object, Function<Object, Object>> expectedTransformationsByProvider = new WeakHashMap<>();

		final Map<Object, MatcherFactory> matcherFactoriesByProvider = new WeakHashMap<>();

		final Map<Object, Set<Object>> receiversByProvider = new WeakHashMap<>();

		final Map<Object, Object> providersByReceiver = new WeakHashMap<>();

		synchronized void forwardTo(Object provider, Matcher... receivers) {
			stream(receivers).forEach(receiver -> {
				providersByReceiver.put(receiver, provider);
				receiversByProvider.computeIfAbsent(provider, key -> new HashSet<>()).add(receiver);
			});
		}

		synchronized void replaceForwardingTo(Matcher oldProvider, Object provider) {
			Function<Object, Object> expectedTransformation = expectedTransformationsByProvider.get(oldProvider);
			if (expectedTransformation != null) {
				expectedTransformationsByProvider.put(provider, expectedTransformation);
			}
			MatcherFactory matcherFactory = matcherFactoriesByProvider.get(oldProvider);
			if (matcherFactory != null) {
				matcherFactoriesByProvider.put(provider, matcherFactory);
			}
			// Remove all the receivers of the old provider.
			Set<Object> receivers = receiversByProvider.remove(oldProvider);
			if (receivers != null) {
				receiversByProvider.put(provider, receivers);
				for (Object receiver : receivers) {
					// Point all the receivers to the new provider.
					providersByReceiver.put(receiver, provider);
				}
			}
		}

		synchronized void putMatcherFactory(Object provider, MatcherFactory matcherFactory) {
			matcherFactoriesByProvider.put(provider, matcherFactory);
		}

		synchronized void putActualTransformation(Object provider, Function<Object, Object> actualTransformation) {
			actualTransformationsByProvider.put(provider, actualTransformation);
		}

		synchronized void putExpectedTransformation(Object provider, Function<Object, Object> expectedTransformation) {
			expectedTransformationsByProvider.put(provider, expectedTransformation);
		}

		synchronized MatcherFactory getMatcherFactory(Object receiver) {
			return getByReceiver(matcherFactoriesByProvider, receiver, () -> {
				throw new IllegalStateException("no context");
			});
		}

		synchronized Function<Object, Object> getActualTransformation(Object receiver) {
			return getByReceiver(actualTransformationsByProvider, receiver, IDENTITY_TRANSFORMATION_ANSWER);
		}

		synchronized Function<Object, Object> getExpectedTransformation(Object receiver) {
			return getByReceiver(expectedTransformationsByProvider, receiver, IDENTITY_TRANSFORMATION_ANSWER);
		}

		private <O> O getByReceiver(Map<Object, O> valuesByReceiver, Object receiver, Supplier<O> defaultAnswer) {
			while (receiver != null) {
				O value = valuesByReceiver.get(receiver);
				if (value != null) {
					return value;
				}
				receiver = providersByReceiver.get(receiver);
			}
			return defaultAnswer.get();
		}

		synchronized void unset(Object provider) {
			actualTransformationsByProvider.remove(provider);
			expectedTransformationsByProvider.remove(provider);
			Set<Object> receivers = receiversByProvider.remove(provider);
			for (Object receiver : receivers) {
				providersByReceiver.remove(receiver);
			}
		}
	}

	public static void unset(Subject provider) {
		Context.getInstance().unset(provider);
	}

	public static <M extends Matcher> Setter<M> set(M provider) {
		return new Setter<>(provider);
	}

	public static <S extends Subject> Setter<S> set(S provider) {
		return new Setter<>(provider);
	}

	public static class Setter<P> {

		private final P provider;

		private Setter(P provider) {
			this.provider = requireNonNull(provider, "provider is null");
		}

		public Setter<P> transformation(Function<Object, Object> objectTransformation) {
			return actualTransformation(objectTransformation)
					.expectedTransformation(objectTransformation);
		}

		public Setter<P> actualTransformation(Function<Object, Object> actualTransformation) {
			Context.getInstance().putActualTransformation(provider, actualTransformation);
			return this;
		}

		public Setter<P> expectedTransformation(Function<Object, Object> expectedTransformation) {
			Context.getInstance().putExpectedTransformation(provider, expectedTransformation);
			return this;
		}

		public Setter<P> matcherFactory(MatcherFactory matcherFactory) {
			Context.getInstance().putMatcherFactory(provider, matcherFactory);
			return this;
		}

		public Setter<P> forwardTo(Matcher... receivers) {
			Context.getInstance().forwardTo(provider, receivers);
			return this;
		}

		public Setter<P> replaceForwardingTo(Matcher oldProvider) {
			Context.getInstance().replaceForwardingTo(oldProvider, provider);
			return this;
		}

		public P get() {
			return provider;
		}
	}

	public static <M extends Matcher> Getter<M> get(M receiver) {
		return new Getter<>(receiver);
	}

	public static <S extends Subject> Getter<S> get(S receiver) {
		return new Getter<>(receiver);
	}

	public static class Getter<P> {

		private final P receiver;

		private Getter(P receiver) {
			this.receiver = requireNonNull(receiver, "receiver is null");
		}

		public Function<Object, Object> actualTransformation() {
			return Context.getInstance().getActualTransformation(receiver);
		}

		public Function<Object, Object> expectedTransformation() {
			return Context.getInstance().getExpectedTransformation(receiver);
		}

		public MatcherFactory matcherFactory() {
			return Context.getInstance().getMatcherFactory(receiver);
		}
	}
}
