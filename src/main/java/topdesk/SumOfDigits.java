package topdesk;

public class SumOfDigits {

    public static void main(String[] args) {
        System.out.println(sumOfSum(12345678901234L));
    }

    /**
     * Calculates the sum of all digits in the given {@code number}. The result
     * of the calculation is always between 0 and 9 (both inclusive). If a
     * preliminary result is higher than 9, the calculation is repeated on this
     * result.
     * <p/>
     * <p/>
     * <strong>Example:</strong><br>
     * The sum of {@code 12345678901234} is:<br>
     * {@code 1+2+3+4+5+6+7+8+9+0+1+2+3+4 = 55}<br>
     * {@code 5+5 = 10}<br>
     * {@code 1+0 = 1}<br>
     * The result of the calculation is: {@code 1}
     *
     * @param number the number to perform the calculation on
     * @return the result of the calculation
     * @throws IllegalArgumentException if {@code number} is smaller than zero
     */
    public static int sumOfSum(long number) {
        long num;
        String numAsStr;

        while (number > 9) {
            numAsStr = String.valueOf(number);
            num = 0;
            for (int i = 0; i < numAsStr.length(); i++) {
                num += (numAsStr.charAt(i) - '0');
            }
            number = num;
        }

        return (int) number;
    }
}
