/**
 * Given two strings s and goal, return true if and only if s can become goal after some number of
 * shifts on s.
 *
 * <p>Example 1:
 *
 * <p>Input: s = "abcde", goal = "cdeab" Output: true
 */
public class RotateString {
  public static boolean rotateString(String s, String goal) {
    if (s.length() == goal.length()) {
      if ((s + s).contains(goal)) {
        return true;
      }
    }
    return false;
  }
}
