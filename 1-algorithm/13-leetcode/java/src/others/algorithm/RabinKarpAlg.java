package others.algorithm;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Check palindrome in data stream
 */
public class RabinKarpAlg {

    @Test
    void testRabinKarp() {
        Assertions.assertEquals(0, match("ab", "a"));
        Assertions.assertEquals(1, match("ab", "b"));
        Assertions.assertEquals(2, match("abcde", "cd"));
    }

    @Test
    void testPalindromeInDataStream() {
        Assertions.assertTrue(add('a'));
        Assertions.assertTrue(add('a'));
        Assertions.assertFalse(add('b'));
        Assertions.assertFalse(add('a'));
        Assertions.assertTrue(add('a'));
        Assertions.assertFalse(add('b'));
        Assertions.assertFalse(add('a'));
        Assertions.assertTrue(add('a'));
    }

    private StringBuilder s = new StringBuilder();
    private final int d = 256, q = 103;
    private long h1 = 0, h2 = 0, h = 1;

    // aabaa -> aabaac: h1=aa->aab (append mid), h2=aa->aac (append last)
    // aabaac -> aabaacd: h1=aab->aab (same), h2=aac->acd (remove new mid, append last)
    public boolean add(char c) {
        s.append(c);

        // Update hash for first half and second
        int n = s.length();
        if (n == 1) return true;
        if (n == 2) {
            h1 = s.charAt(0) % q;
            h2 = s.charAt(1) % q;
            return s.charAt(0) == s.charAt(1);
        }

        if (n % 2 == 0) {
            h = (h * d) % q;
            h1 = (h1 + h * s.charAt((n - 1) / 2)) % q; // note: append to leading digit nor trailing
            h2 = (d * h2 + s.charAt(n - 1)) % q;
        } else {
            h2 = (d * (h2 - h * s.charAt(n / 2)) + s.charAt(n - 1)) % q;
            if (h2 < 0) h2 += q;
        }

        // Check if false positive
        if (h1 == h2) {
            return true;
        }
        return false;
    }

    // Naive Rabin Karp Matching Algorithm
    public int match(String s, String p) {
        if (s.length() < p.length()) return -1;
        final int m = s.length(), n = p.length();
        final int d = 256; // d-radix, q-hash size (any large since only one entry saved)
        final long q = 127;//BigInteger.probablePrime(31, new Random()).longValue();

        long h = 1; // old leading digit bit: d^(n-1) % q
        for (int i = 0; i < n - 1; i++) {
            h = (h * d) % q;
        }

        long hs = 0, hp = 0; // precomputed hash of length m
        for (int i = 0; i < n; i++) {
            hs = (d * hs + s.charAt(i)) % q;
            hp = (d * hp + p.charAt(i)) % q;
        }

        for (int i = n; i <= m; i++) {
            if (hs == hp) { // Monte Carlo or Las Vegas
                if (p.equals(s.substring(i - n, i))) {
                    return i - n;
                }
            }
            // Key: rolling hash by removing old leading digit
            //      and then adding new trailing digit
            if (i < m) {
                hs = (d * (hs - h * s.charAt(i - n)) + s.charAt(i)) % q;
                if (hs < 0) hs += q;
            }
        }
        return -1;
    }

}
