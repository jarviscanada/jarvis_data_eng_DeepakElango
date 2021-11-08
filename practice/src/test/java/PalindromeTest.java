import org.junit.Test;

import static org.junit.Assert.*;

public class PalindromeTest {
  @Test
  public void rotateString() {
    String test1 = "racecar";
    String test2 = "race a car";
    assertTrue(Palindrome.isPalindrome(test1));
    assertFalse(Palindrome.isPalindrome(test2));
  }

  @Test
  public void rotateStringRecursion() {
    String test1 = "racecar";
    String test2 = "race a car";
    assertTrue(Palindrome.recursion(test1, 0, test1.length() - 1));
  }
  }
