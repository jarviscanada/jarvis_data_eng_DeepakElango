import java.util.*;
import java.util.stream.Stream;

/** Find the min and the max element in the array. */
public class ArrayMinMax {

  public static int min(int[] nums) {
    int result = 0;
    Arrays.sort(nums);
    return nums[0];
  }

  public static int max(int[] nums) {
    int result = nums.length - 1;
    Arrays.sort(nums);
    return nums[result];
  }

  public static void main(String[] args) {
    List<Integer> list = new LinkedList<Integer>();
    int[] arr = {
      11, 34, 3, 5,
    };
    for (int i = 0; i < arr.length; i++) {
      list.add(i);
    }
    System.out.println(min(arr));
    System.out.println(max(arr));
    System.out.println("Collections = " + Collections.max(list));
    System.out.println("Collections = " + Collections.min(list));
  }
}
