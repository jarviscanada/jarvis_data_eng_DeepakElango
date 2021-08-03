
import org.junit.Test;
import static org.junit.Assert.*;

public class RotateStringTest {

  @Test
  public void rotateString() {
    String test1 = "abcde";
    String test2 = "cdeab";
    assertTrue(RotateString.rotateString(test1,test2));
  }
}