package internal.su.pernova.assertions.matchers;

import static internal.su.pernova.assertions.DescribableUtils.getDefaultName;

import java.util.Objects;

import su.pernova.assertions.Context;
import su.pernova.assertions.Matcher;
import su.pernova.assertions.MatcherFactory;

public class EqualTo extends ObjectMatcher {

	public static final CharSequence NAME = getDefaultName(EqualTo.class);

	public static final MatcherFactory MATCHER_FACTORY = EqualTo::new;

	public EqualTo(CharSequence name, boolean prompt, Object expectedValue) {
		super(name, prompt, expectedValue);
	}

	public EqualTo(Object expectedValue) {
		this(NAME, true, expectedValue);
	}

	@Override
	public boolean match(Object actualValue) {
		return Objects.equals(expectedValue, actualValue);
	}

	@Override
	public Matcher contextualize(Context context) {
		return context
				.forwardMatcherFactory(this, MATCHER_FACTORY)
				.contextualizeExpectedValue(this, expectedValue, MATCHER_FACTORY);
	}
}
