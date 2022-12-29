package su.pernova.matchers.internal;

import su.pernova.matchers.Description;
import su.pernova.matchers.SelfDescribing;

public class SelfDescribingValue<T> implements SelfDescribing {
    private T value;
    
    public SelfDescribingValue(T value) {
        this.value = value;
    }

    @Override
    public void describeTo(Description description) {
        description.appendValue(value);
    }
}
