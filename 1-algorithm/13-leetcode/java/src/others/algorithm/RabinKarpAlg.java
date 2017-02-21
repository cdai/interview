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

    private StringBuilder str;
    private int half1;
    private int half2;

    public boolean add(char c) {
        str.append(c);

        // Update hash for first half and second


        // Check if false positive
        if (half1 == half2) {
            for (int i = 0, j = str.length() - 1; i < j; i++, j--) {
                if (str.charAt(i) != str.charAt(j)) return false;
            }
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

        long h = 1; // d^(m-1) % q
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
            // Key: rolling hash by removing old digit and adding new high order digit
            if (i < m) {
                hs = (d * (hs - h * s.charAt(i - n)) + s.charAt(i)) % q;
                if (hs < 0) hs += q;
            }
        }
        return -1;
    }

}
