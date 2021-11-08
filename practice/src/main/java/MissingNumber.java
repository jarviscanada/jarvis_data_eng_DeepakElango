import java.util.Arrays;
import java.util.HashSet;

/**
 * Given an array nums containing n distinct numbers in the range [0, n], return the only number in
 * the range that is missing from the array.
 *
 * <p>Example 1: Input: nums = [3,0,1] Output: 2
 */
public class MissingNumber {
  public static int missingNumber(int[] nums) {
    Arrays.sort(nums);
    for (int i = 0; i < nums.length; i++) {
      if (nums[i + 1] != nums[i] + 1) {
        return nums[i] + 1;
      }
    }
    return 0;
  }

  public static void main(String[] args) {
    int[] arr = {
      1, 2, 3, 5,
    };
    System.out.println(missingNumber(arr));
    //System.out.println(missingNumberSet(arr));
  }
}
