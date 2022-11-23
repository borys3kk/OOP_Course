package agh.ics.oop;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class OptionsParserTest {

    @Test
    public void InvalidParseTest() {

        String[] test_args = {"f", "not", "good", "args"};
        try {
            MoveDirection [] out = OptionsParser.parse(test_args);
            fail("Should fail because there are invalid args");
        }
        catch (IllegalArgumentException ex){
            assertTrue(true, "caught exception");
        }
    }

    @Test
    public void validParseTest() {
        String[] test_args = {"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"};
        try {
            MoveDirection[] out = OptionsParser.parse(test_args);
            assertTrue(true, "there should be no exceptions");
        }
        catch (IllegalArgumentException ex){
            fail("This should not have happened");
        }
    }
}
