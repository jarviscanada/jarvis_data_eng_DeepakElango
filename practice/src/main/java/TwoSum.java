/**
 * Given an array of integers nums and an integer target, return indices of the two numbers such
 * that they add up to target.
 *
 * <p>Example 1:
 *
 * <p>Input: nums = [2,7,11,15], target = 9 Output: [0,1] Output: Because nums[0] + nums[1] == 9, we
 * return [0, 1].
 *
 * <p>You may assume that each input would have exactly one solution, and you may not use the same
 * element twice.
 */

import java.util.*;

public class TwoSum {
  public static int[] bruteForce(int[] arr, int target) {
    for (int i = 0; i < arr.length; i++) {
      for (int j = 0; j < arr.length; j++) {
        if ((arr[i] + arr[j]) == target) {
          return new int[] {i, j};
        }
      }
    }
    return null;
  }

  public static int[] sortTwoSum(int[] arr, int target) {
    Arrays.sort(arr);
    int first = 0;
    int last = arr.length - 1;
    while (first < last) {
      if ((arr[first] + arr[last]) == target) {
        return new int[] {first, last};
      }
      last--;
    }
      return null;
  }
}
