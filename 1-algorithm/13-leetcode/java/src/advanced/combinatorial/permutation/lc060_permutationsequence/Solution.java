package advanced.combinatorial.permutation.lc060_permutationsequence;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * The set [1,2,3,…,n] contains a total of n! unique permutations.
 * By listing and labeling all of the permutations in order,
 * We get the following sequence (ie, for n = 3):
 *  "123"
 *  "132"
 *  "213"
 *  "231"
 *  "312"
 *  "321"
 * Given n and k, return the kth permutation sequence.
 * Note: Given n will be between 1 and 9 inclusive.
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().getPermutation(9, 101));
    }

    // My 2nd: O(N) time. Note "fact /= n" must put first in case n becomes 0
    // N is 9 at most, and 9! = 362880, so it's safe
    public String getPermutation(int n, int k) {
        // 1.Candidate numbers: 1~N
        List<Integer> nums = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            nums.add(i);
        }

        // 2.Factorial (N-1)!
        int fact = 1;
        for (int i = 2; i <= n; i++) {
            fact *= i;
        }

        // 3.K starts from 1
        k--;

        // 4.Get to calculate
        StringBuilder result = new StringBuilder();
        for ( ; n > 0; n--) {
            fact /= n;
            result.append(nums.remove(k / fact));
            k %= fact;
        }
        return result.toString();
    }

    // My 1st
    public String getPermutation1(int n, int k) {
        // Candidate numbers
        List<Integer> nums = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            nums.add(i);
        }

        // Factorial n!
        int fact = 1;
        for (int i = 1; i <= n; i++) {
            fact = fact * i;
        }

        // error1: k means kth, but this algorithm starts from 0
        k--;

        StringBuilder perm = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            fact = fact / (n - i); // error2: update before, otherwise divide-by-zero

            perm.append(nums.remove(k / fact));

            k = k % fact;
        }

        return perm.toString();
    }

    public String getPermutation2(int n, int k) {
        StringBuilder perm = new StringBuilder();

        // 1.Initial permutation
        for (int i = 1; i <= n; i++) {
            perm.append(i);
        }

        // 2.Generate next
        while (k-- > 1) {
            // 2.1 Find digit from right to left
            int i = n - 2;
            while (i > 0 && perm.charAt(i) > perm.charAt(i+1)) {
                i--;
            }

            // 2.2 Find first larger digit from right to left
            int j = n - 1;
            while (perm.charAt(i) > perm.charAt(j)) {
                j--;
            }

            // 2.3 Swap them
            char tmp = perm.charAt(i);
            perm.setCharAt(i, perm.charAt(j));
            perm.setCharAt(j, tmp);

            // 2.4 Reverse [i+1, n)
            perm.replace(i+1, n, new StringBuilder(perm.substring(i+1)).reverse().toString());
        }
        return perm.toString();
    }

    public String getPermutation3(int n, int k) {
        if (n < 1 || k < 1) {
            return "";
        }

        // Set initial permutation string
        String perm = "";
        for (int i = 1; i <= n; i++) {
            perm += i;
        }

        // Generate next permutation k times
        while (k-- > 1) {   // error2: we've generated the first one
            Set<Integer> set = new HashSet<>();
            for (int m = 1; m <= n; m++) {
                set.add(m);
            }

            // 1.Find which position to increment
            int i = n - 1;
            int j = 0;
            for ( ; i >= 0; i--) {
                j = perm.charAt(i) - '0';
                set.remove(j);

                while (++j <= n) {  // error3: next could be any of [j+1, n]
                    if (!set.contains(j)) {
                        break;
                    }
                }

                if (j <= n) {
                    break;
                }
            }

            if (i == -1) {
                return "";
            }

            // 2.Next permutation: [1342] => [1]+[4], [23]
            perm = perm.substring(0, i) + String.valueOf(j);
            //set.remove(j);      // error1: forget...
            set.add(j);
            for (i = i + 1; i < n; i++) {
                for (int m = 1; m <= n; m++) {
                    if (!set.contains(m)) {
                        perm += m;
                        set.add(m);
                        break;
                    }
                }
            }
        }
        return perm;
    }

}
