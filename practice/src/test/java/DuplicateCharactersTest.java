import org.junit.Test;

import static org.junit.Assert.*;

public class DuplicateCharactersTest {
    @Test
    public void DuplicateNumber() {
        String test1 = "A black zat";
        assertEquals(DuplicateCharacters.findDuplicate(test1), "A");
    }
}