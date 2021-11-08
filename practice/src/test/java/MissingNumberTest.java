import org.junit.Test;

import static org.junit.Assert.*;

public class MissingNumberTest {
  @Test
  public void MissingNumber() {
    int[] arr = {
      1, 2, 3, 5,
    };
    assertEquals(MissingNumber.missingNumber(arr), 4);
  }
}
