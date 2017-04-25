package miscellaneous.math.number.lc507_perfectnumber;

/**
 * We define the Perfect Number is a positive integer that is equal to the sum of all its positive divisors except itself.
 * Now, given an integer n, write a function that returns true when it is a perfect number and false when it is not.
 */
public class Solution {

    public boolean checkPerfectNumber(int num) {
        if (num <= 1) return false; // note that 1 is edge case too
        int sum = 1;
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                sum += i + num / i; // no need to check if i and num/i equals in the given range?
            }
        }
        return sum == num;
    }

    public boolean checkPerfectNumber_my(int num) {
        if (num <= 1) return false;
        int sum = 1;
        for (int l = 2, r = num / l; l < r; l++) {
            if (num % l == 0) {
                sum += l;
                r = num / l;
                if (r != l) sum += r;
            }
        }
        return sum == num;
    }

    // TLE
    public boolean checkPerfectNumber2(int num) {
        int sum = 0;
        for (int i = 1; i <= num / 2 && sum <= num; i++) {
            if (num % i == 0) sum += i;
        }
        return sum == num;
    }

}
