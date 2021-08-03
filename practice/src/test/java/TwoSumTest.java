import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TwoSumTest {

    @Before
    public void setUp() throws Exception {
        TwoSum twoSum1 = new TwoSum();
    }

    @Test
    public void twoSum() {
        int[] test1 = {2, 7, 12, 15};
        int[] out1 = {0, 1};
        assertArrayEquals(out1, TwoSum.bruteForce(test1, 9));
    }

}