/**
 * Finding the duplicated characters in a string.
 *
 * <p>Input: "A black cat" Output: ["a", "c"]
 */
public class DuplicateCharacters {
  public static char[] findDuplicate(String string) {
    char[] chars = string.toCharArray();
    int i = 0;
    int j = chars.length - 1;

    while (i <= j) {
      while (j >= 0) {
        if (chars[i] == chars[j]) {
          j--;
          return new char[]{chars[j]};
        } else {
          j--;
        }
      }
      i++;
    }
    return new char[]{chars[j]};
  }

  public static void main(String[] args) {
    String test1 = "A black cat";
    System.out.println(findDuplicate(test1));
  }
}
