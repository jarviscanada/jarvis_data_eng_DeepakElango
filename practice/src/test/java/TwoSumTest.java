import org.junit.Test;

import static org.junit.Assert.*;

public class TwoSumTest {

  @Test
  public void twoSumBruteForce() {
    int[] test1 = {2, 7, 12, 15};
    int[] out1 = {0, 1};
    assertArrayEquals(out1, TwoSum.bruteForce(test1, 9));
  }

  @Test
  public void twoSumSort() {
    int[] test1 = {2, 7, 12, 15};
    int[] out1 = {0, 1};
    assertArrayEquals(out1, TwoSum.sortTwoSum(test1, 9));
  }

  @Test
  public void twoSumHashMap() {
    int[] test1 = {2, 7, 12, 15};
    int[] out1 = {0, 1};
    assertArrayEquals(out1, TwoSum.hashMapTwoSum(test1, 9));
  }
}
