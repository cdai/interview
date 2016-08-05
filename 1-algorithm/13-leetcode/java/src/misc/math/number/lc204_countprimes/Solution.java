package misc.math.number.lc204_countprimes;

/**
 * Count the number of prime numbers less than a non-negative number, n.
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().countPrimes(5));
    }

    // Sieve of Eratosthenes: O(nloglogn)
    public int countPrimes(int n) {
        if (n <= 1) {
            return 0;
        }

        // 1.Create table (0, 1 must be false)
        boolean[] isPrimes = new boolean[n];
        for (int i = 2; i < n; i++) {
            isPrimes[i] = true;
        }

        // 2.Mark off all multiples
        for (int i = 2; i * i <= n; i++) {
            if (!isPrimes[i]) {  // Optimize-1: if 4 is marked off (by 2), then no need to mark off multiple of 4 like 8,16...
                continue;
            }
            for (int j = i * i; j < n; j += i) { // Optimize-2: start from i*i like 5*5, since i*2,i*3 must be already marked off
                isPrimes[j] = false;
            }
        }

        // 3.Sum up
        int count = 0;
        for (boolean isPrime : isPrimes) {
            if (isPrime) {
                count++;
            }
        }
        return count;
    }

    public int countPrimes2(int n) {
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (isPrime(i)) {
                count++;
            }
        }
        return count;
    }

    private boolean isPrime(int n) {
        for (int i = 2; i * i <= n; i++) { // error: <=, since n=p*q if not prime, then one of factor must lie in [2,sqrt(n)]
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

}
