import java.util.Arrays;

/**
 * Swap two numbers without using the third variable
 *
 * <p>Example: Input: [2,3] Output: [3,2]
 */
public class SwapTwoNumbers {

  public static int[] swap(int num1, int num2) {
    num1 = num1 + num2;
    num2 = num1 - num2;
    num1 = num1 - num2;
    return new int[] {num1, num2};
  }

  public static void main(String[] args) {
    int x = 111;
    int y = 232;
    System.out.println(Arrays.toString(swap(x, y)));
  }
}