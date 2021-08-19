import org.junit.Test;

import static org.junit.Assert.*;

public class DuplicateNumberTest {
  @Test
  public void DuplicateNumber() {
    int[] arr = {
      1, 2, 3, 2, 5,
    };
    assertEquals(DuplicateNumber.findDuplicateSort(arr), 2);
  }
}
