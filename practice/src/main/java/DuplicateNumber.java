import java.util.Arrays;

/**
 * Given an array of integers nums containing n + 1 integers where each integer is in the range [1,
 * n] inclusive.
 *
 * <p>There is only one repeated number in nums, return this repeated number
 *
 * <p>Example 1:
 *
 * <p>Input: nums = [1,3,4,2,2] Output: 2
 */
public class DuplicateNumber {
  public static int findDuplicateSort(int[] nums) {
    Arrays.sort(nums);
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] == nums[i+1]){
        return nums[i];
      }
    }
    return 0;
  }
}
