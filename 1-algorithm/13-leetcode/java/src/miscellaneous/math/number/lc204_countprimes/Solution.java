package miscellaneous.math.number.lc204_countprimes;

/**
 * Count the number of prime numbers less than a non-negative number, n.
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().countPrimes(5));
    }

    // My 2AC: cannot remember optimizing skill...
    public int countPrimes(int n) {
        // 1.Create table
        boolean[] isNotPrime = new boolean[n];
        for (int i = 2; i <= n / 2; i++)
            for (int j = 2; i * j < n; j++)
                isNotPrime[i * j] = true;

        // 2.Count through the table
        int count = 0;
        for (int i = 2; i < n; i++)
            if (!isNotPrime[i]) count++;
        return count;
    }

    public int countPrimes_optimized(int n) {
        // 1.Create table
        boolean[] isNotPrime = new boolean[n];
        for (int i = 2; i * i < n; i++) {       // Optimize-3: i * i < n, since j < n
            if (isNotPrime[i]) continue;        // Optimize-1: skip those marked off already
            for (int j = i * i; j < n; j += i)  // Optimize-2: j=2 is slow, use j+=i rather than j*i
                isNotPrime[j] = true;
        }

        // 2.Count through the table
        int count = 0;
        for (int i = 2; i < n; i++)
            if (!isNotPrime[i]) count++;
        return count;
    }

    // O(N^2) time.
    public int countPrimes_naive(int n) {
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (isPrime(i)) {
                count++;
            }
        }
        return count;
    }

    // Sieve of Eratosthenes
    // upper-bound is n(1/2 + 1/3 + 1/5 + 1/7 + …), that is sum of reciprocals of primes up to n, which is O(nloglogn)
    public int countPrimes1(int n) {
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
