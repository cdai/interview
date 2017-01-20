package buildingblock.table.dict.lc266_palindromepermutation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * Given a string, determine if a permutation of the string could form a palindrome.
 */
public class Solution {

    public boolean canPermutePalindrome(String s) {
        Set<Character> freq = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            if (!freq.remove(s.charAt(i))) freq.add(s.charAt(i));
        }
        return freq.size() <= 1; // At most one odd character
    }

    public boolean canPermutePalindrome_twopass(String s) {
        int[] freq = new int[256];
        for (int i = 0; i < s.length(); i++) freq[s.charAt(i)]++;
        for (int i = 0, odd = 0; i < freq.length; i++) {
            if (freq[i] % 2 == 1 && odd++ > 1) return false;
        }
        return true;
    }

    @Test
    void testEmpty() {
        Assertions.assertTrue(canPermutePalindrome(""));
    }

    @Test
    void testEven() {
        Assertions.assertTrue(canPermutePalindrome("ababdd"));
    }

    @Test
    void testOdd() {
        Assertions.assertTrue(canPermutePalindrome("ababc"));
    }

    @Test
    void testSpace() {
        Assertions.assertTrue(canPermutePalindrome("a bab g"));
    }

    @Test
    void testInvalid() {
        Assertions.assertFalse(canPermutePalindrome("ababcf"));
    }
}
