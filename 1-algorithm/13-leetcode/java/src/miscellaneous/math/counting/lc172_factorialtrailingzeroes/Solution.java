package miscellaneous.math.counting.lc172_factorialtrailingzeroes;

/**
 * Given an integer n, return the number of trailing zeroes in n!.
 * Note: Your solution should be in logarithmic time complexity.
 */
public class Solution {

    // My 2AC
    // Produce one zero:    5,10,15,20,25...
    // Produce another zero:25(5*5),50(5*5*2)...
    // So one factor 5 can produce 1 zero

    // Each factor 5 produce a 0. eg.5, 10, 15, 20. eg.25, 50 produces 2 zero.
    public int trailingZeroes(int n) {
        int zero = 0;
        for (long i = 5; i <= n; i *= 5) { // Be aware of overflow
            zero += n / i;
        }
        return zero;
    }

    // Eg. 136 / 5 = 27 (There're 27 '5' between 1..136, each one can cause one 0)
    // 136 / 25 = 5 (multiple of 25 can cause another 0, like 25,50,75,100,125, eg.25*4,75*8=600)
    // 136 / 125 = 1 (125 cause another 0, 3 in total, eg.125*8=1000)
    // So 136! has 33 trailing zero
    public int trailingZeroes1(int n) {
        if (n < 0) {
            return 0;
        }

        int count = 0;
        for (long i = 5; n / i >= 1; i *= 5) {
            count += n / i;
        }
        return count;
    }

    public int trailingZeroes2(int n) {
        if (n == 0) {
            return 0;
        }
        return zeroes(1, n);
    }

    // This is actually O(n) not O(logn), of course TLE...
    private int zeroes(int from, int to) {
        if (from == to) {   // from > to is impossible
            int zero = 0;
            for ( ; (from % 5 == 0); from /= 5, zero++); // from > 0 to avoid dead loop
            return zero;
        }

        int mid = (from + to) / 2;
        return zeroes(from, mid) + zeroes(mid + 1, to);
    }

}
