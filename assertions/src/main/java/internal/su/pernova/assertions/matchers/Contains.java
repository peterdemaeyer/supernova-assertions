package internal.su.pernova.assertions.matchers;

import java.util.Collection;
import java.util.Iterator;

import su.pernova.assertions.Matcher;

public class Contains extends DelegatingMatcher {

	public Contains(Matcher delegate) {
		super(null, delegate);
	}

	@Override
	public boolean match(Object actual) {
		if (actual instanceof Collection) {
			for (Iterator it = ((Collection) actual).iterator(); it.hasNext(); ) {
				if (delegate.match(it.next())) {
					return true;
				}
			}
		}
		return false;
	}
}
