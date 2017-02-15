package fundamentals.string.search.lc459_repeatedsubstringpattern;

/**
 */
public class Solution {

    // dp[i+1] stores the maximum number of characters that the string is repeating itself up to position i.
    // Therefore, if a string repeats a length 5 substring 4 times, then the last entry would be of value 15.
    // To check if the string is repeating itself, we just need the last entry to be non-zero and str.size() to divide (str.size()-last entry).
    public boolean repeatedSubstringPattern(String s) {
        int n = s.length();
        int[] dfa = new int[n + 1];
        for (int i = 1, j = 0; i < n; i++) {
            while (j > 0 && s.charAt(j) != s.charAt(i)) {
                j = dfa[j];
            }
            if (s.charAt(j) == s.charAt(i)) j++;
            dfa[i + 1] = j;
        }
        return dfa[n] > 0 && dfa[n] % (n - dfa[n]) == 0;
    }

    // Naive but nice use of indexOf to avoid StringBuilder
    // O(N^1.5) time
    public boolean repeatedSubstringPattern1(String str) {
        int n = str.length();
        for (int i = 1; i <= n / 2; i++) {
            if (n % i != 0) continue;
            String substr = str.substring(0, i);
            int j = i;
            while (str.indexOf(substr, j) == j) {
                j += i;
            }
            if (j == n) return true;
        }
        return false;
    }

    // Nice idea that only check divisor
    // O(N^1.5) time
    public boolean repeatedSubstringPattern_naive(String str) {
        int n = str.length();
        for (int i = 1; i <= n / 2; i++) {
            if (n % i != 0) continue;
            StringBuilder rep = new StringBuilder();
            String substr = str.substring(0, i);
            for (int j = n / i; j > 0; j--) {
                rep.append(substr);
            }
            if (str.equals(rep.toString())) return true;
        }
        return false;
    }

}
