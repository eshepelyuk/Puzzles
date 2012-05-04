package topdesk;


import org.testng.annotations.Test

import static topdesk.SumOfDigits.sumOfSum

public class SumOfDigitsTest {
    @Test
    void testSum() {
        assert sumOfSum(0) == 0
        assert sumOfSum(11111) == 5
        assert sumOfSum(100009) == 1
        assert sumOfSum(12345678901234) == 1
    }
}
