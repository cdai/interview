package buildingblock.table.alphabet.lc266_palindromepermutation;

import java.util.HashSet;
import java.util.Set;

/**
 * Given a string, determine if a permutation of the string could form a palindrome.
 * For example, "code" -> False, "aab" -> True, "carerac" -> True.
 */
public class Solution {

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.canPermutePalindrome("code")); // false
        System.out.println(sol.canPermutePalindrome("aab")); // true
        System.out.println(sol.canPermutePalindrome("carerac")); // true
    }

    // O(N) without counter in only one pass
    public boolean canPermutePalindrome(String str) {
        Set<Character> freq = new HashSet<>();
        for (char c : str.toCharArray())
            if (!freq.remove(c))
                freq.add(c);
        return freq.size() <= 1;
    }

    // O(N) with counter in two passes
    public boolean isPalindrome_counter(String str) {
        char[] freq = new char[256];
        for (char c : str.toCharArray())
            freq[c]++;

        for (int i = 0, odd = 0; i < freq.length; i++)
            if (freq[i] % 2 == 1 && odd++ > 1)
                return false;
        return true;
    }

}
