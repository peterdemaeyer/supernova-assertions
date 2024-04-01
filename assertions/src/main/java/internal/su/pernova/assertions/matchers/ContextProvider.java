package internal.su.pernova.assertions.matchers;

import su.pernova.assertions.Matcher;

public interface ContextProvider {

	Matcher provide(Matcher matcher, Context context);
}
