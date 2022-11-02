package su.pernova.matchers.core;

import su.pernova.matchers.Matcher;
import org.junit.jupiter.api.Test;

import static su.pernova.matchers.AbstractMatcherTest.*;
import static su.pernova.matchers.core.IsSame.sameInstance;
import static su.pernova.matchers.core.IsSame.theInstance;


public final class IsSameTest {

    @Test public void
    copesWithNullsAndUnknownTypes() {
        Matcher<String> matcher = sameInstance("irrelevant");
        
        assertNullSafe(matcher);
        assertUnknownTypeSafe(matcher);
    }

    @Test public void
    evaluatesToTrueIfArgumentIsReferenceToASpecifiedObject() {
        Object o1 = new Object();
        Matcher<Object> matcher = sameInstance(o1);

        assertMatches(matcher, o1);
        assertDoesNotMatch(matcher, new Object());
    }

    @Test public void
    alternativeFactoryMethodAlsoMatchesOnlyIfArgumentIsReferenceToASpecifiedObject() {
        Object o1 = new Object();
        Matcher<Object> matcher = theInstance(o1);

        assertMatches(matcher, o1);
        assertDoesNotMatch(matcher, new Object());
    }

    @Test public void
    returnsReadableDescriptionFromToString() {
        assertDescription("sameInstance(\"ARG\")", sameInstance("ARG"));
    }

    @Test public void
    returnsReadableDescriptionFromToStringWhenInitialisedWithNull() {
        assertDescription("sameInstance(null)", sameInstance(null));
    }
}
