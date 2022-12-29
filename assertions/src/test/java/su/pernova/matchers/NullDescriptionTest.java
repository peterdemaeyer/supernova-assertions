package su.pernova.matchers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import su.pernova.matchers.Description.NullDescription;
import org.junit.jupiter.api.Test;

public final class NullDescriptionTest {

    private final NullDescription nullDescription = new Description.NullDescription();

    @Test
    public void
    isUnchangedByAppendedText() {
        nullDescription.appendText("myText");
        assertEquals("", nullDescription.toString());
    }

}
