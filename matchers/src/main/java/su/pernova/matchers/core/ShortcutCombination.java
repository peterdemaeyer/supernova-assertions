package su.pernova.matchers.core;

import su.pernova.matchers.Matcher;
import su.pernova.matchers.Description;

abstract class ShortcutCombination<T> extends Matcher<T> {

    private final Iterable<Matcher<? super T>> matchers;

    public ShortcutCombination(Iterable<Matcher<? super T>> matchers) {
        this.matchers = matchers;
    }
    
    @Override
    public abstract boolean matches(Object o);
    
    @Override
    public abstract void describeTo(Description description);
    
    protected boolean matches(Object o, boolean shortcut) {
        for (Matcher<? super T> matcher : matchers) {
            if (matcher.matches(o) == shortcut) {
                return shortcut;
            }
        }
        return !shortcut;
    }
    
    public void describeTo(Description description, String operator) {
        description.appendList("(", " " + operator + " ", ")", matchers);
    }
}
