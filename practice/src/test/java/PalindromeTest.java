import org.junit.Test;

import static org.junit.Assert.*;

public class PalindromeTest {
  @Test
  public void rotateString() {
    String test1 = "race a car";
    assertTrue(Palindrome.isPalindrome(test1));
  }
}
