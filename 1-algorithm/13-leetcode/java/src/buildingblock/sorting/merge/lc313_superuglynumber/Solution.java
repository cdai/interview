package buildingblock.sorting.merge.lc313_superuglynumber;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Write a program to find the nth super ugly number.
 * Super ugly numbers are positive numbers whose all prime factors are in the given prime list primes of size k.
 * For example, [1, 2, 4, 7, 8, 13, 14, 16, 19, 26, 28, 32] is the sequence of the first 12 super ugly numbers given primes = [2, 7, 13, 19] of size 4.
 * Note:
 * (1) 1 is a super ugly number for any given primes.
 * (2) The given numbers in primes are in ascending order.
 * (3) 0 < k ≤ 100, 0 < n ≤ 106, 0 < primes[i] < 1000.
 */
public class Solution {

    // My 2nd: try lambda, but too slow
    public int nthSuperUglyNumber(int n, int[] primes) {
        if (n <= 0 || primes.length == 0) {
            return 0;
        }

        int[] ugly = new int[n];
        int[] index = new int[primes.length];
        int[] candidate = new int[primes.length];
        for (int i = 0; i < candidate.length; i++) {
            candidate[i] = primes[i];
        }

        ugly[0] = 1;
        for (int i = 1; i < n; i++) {
            // Pick min of K candidates as next ugly number
            //int min = Arrays.stream(candidate).min().getAsInt();
            int min = Integer.MAX_VALUE;
            for (int c : candidate) {
                min = Math.min(min, c);
            }
            ugly[i] = min;

            // Only re-compute candidate equals to picked min
            for (int j = 0; j < candidate.length; j++) {
                if (candidate[j] == min) {
                    candidate[j] = primes[j] * ugly[++index[j]];
                }
            }
        }
        return ugly[n - 1];
    }

    // My 1st
    public int nthSuperUglyNumber1(int n, int[] primes) {
        int[] uglyNums = new int[n];            // Optimize-1: replace ArrayList with int[], since length is known
        int[] pos = new int[primes.length];

        uglyNums[0] = 1;
        for (int i = 1; i < n; i++) {
            int min = Integer.MAX_VALUE;        // Optimize-2: heap is unnecessary
            for (int j = 0; j < primes.length; j++) {
                min = Math.min(min, primes[j] * uglyNums[pos[j]]);
            }
            uglyNums[i] = min;

            for (int j = 0; j < primes.length; j++) {
                if (primes[j] * uglyNums[pos[j]] == min) {
                    pos[j]++;
                }
            }
        }
        return uglyNums[n - 1];
    }

    public int nthSuperUglyNumber2(int n, int[] primes) {
        int[] uglyNums = new int[n];            // Optimize-1: replace ArrayList with int[], since length is known
        int[] pos = new int[primes.length];
        int[] candidates = new int[primes.length]; // Optimize-2:

        uglyNums[0] = 1;
        for (int i = 1; i < n; i++) {
            int min = Integer.MAX_VALUE;        // Optimize-3: heap is unnecessary
            for (int j = 0; j < primes.length; j++) {
                if (candidates[j] == 0) {
                    candidates[j] = primes[j] * uglyNums[pos[j]];
                }
                min = Math.min(min, candidates[j]);
            }
            uglyNums[i] = min;

            for (int j = 0; j < primes.length; j++) {
                if (candidates[j] == min) {
                    pos[j]++;
                    candidates[j] = 0;
                }
            }
        }
        return uglyNums[n - 1];
    }

    // heap is not required, we only need min...
    public int nthSuperUglyNumber3(int n, int[] primes) {
        List<Integer> uglyNums = new ArrayList<>();
        Queue<Integer> heap = new PriorityQueue<>();
        int[] pos = new int[primes.length];

        uglyNums.add(1);
        while (uglyNums.size() < n) {
            for (int i = 0; i < primes.length; i++) {
                heap.offer(primes[i] * uglyNums.get(pos[i]));
            }

            int min = heap.poll();
            uglyNums.add(min);
            heap.clear();   // it's hard to reuse heap (remind who changed last round is required)

            for (int i = 0; i < primes.length; i++) {
                if (primes[i] * uglyNums.get(pos[i]) == min) {
                    pos[i]++;
                }
            }
        }
        return uglyNums.get(n - 1);
    }

}
