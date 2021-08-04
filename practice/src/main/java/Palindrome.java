/**
 * Given a string s, determine if it is a palindrome, considering only alphanumeric characters and
 * ignoring cases.
 *
 * <p>Example 1:
 *
 * <p>Input: s = "race a car" Output: false Explanation: "raceacar" is not a palindrome.
 */
public class Palindrome {
  public static boolean isPalindrome(String s) {
    char[] chars = s.toCharArray();
    int j = chars.length - 1;
    for (int i = 0; i < chars.length; i++) {
      if (chars[i] != chars[j]) {
        return false;
      } else {
        j--;
      }
    }
    return true;
  }

  public static boolean recursion(String s, int begin, int end) {
    begin = 0;
    end = s.length() - 1;

    while (begin >= end) {
      if (s.charAt(begin) == s.charAt(end)) {
        begin++;
        end--;
        return recursion(s, begin, end);
      } else {
        return false;
      }
    }
    return true;
  }
}
