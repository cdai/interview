package buildingblock.table.bitvector.lc318_maximumproductofwordlengths;

import java.util.HashSet;
import java.util.Set;

/**
 * Given a string array words, find the maximum value of length(word[i]) * length(word[j])
 * where the two words do not share common letters.
 * You may assume that each word will contain only lower case letters. If no such two words exist, return 0.
 * Example 1: Given ["abcw", "baz", "foo", "bar", "xtfn", "abcdef"]. Return 16. The two words can be "abcw", "xtfn".
 * Example 2: Given ["a", "ab", "abc", "d", "cd", "bcd", "abcd"]. Return 4. The two words can be "ab", "cd".
 * Example 3: Given ["a", "aa", "aaa", "aaaa"]. Return 0. No such pair of words.
 */
public class Solution {

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.maxProduct(new String[]{"eae", "ea", "aaf", "bda", "fcf", "dc", "ac", "ce", "cefde", "dabae"}));
    }

    // O(N^2) using bit vector as dictionary. And trick for combining into one loop.
    public int maxProduct(String[] words) {
        int[] dict = new int[words.length];
        int max = 0;
        for (int i = 0; i < words.length; i++) {
            for (char c : words[i].toCharArray())
                dict[i] |= 1 << (c - 'a');

            for (int j = 0; j < i; j++)
                if ((dict[i] & dict[j]) == 0)
                    max = Math.max(max, words[i].length() * words[j].length());
        }
        return max;
    }

    // My 3AC: Set wastes much space and TLE...
    public int maxProduct_set(String[] words) {
        if (words.length == 0) return 0;
        Set<Character>[] dict = new HashSet[words.length];
        for (int i = 0; i < dict.length; i++) dict[i] = new HashSet<>();
        for (int i = 0; i < words.length; i++)
            for (char c : words[i].toCharArray())
                dict[i].add(c);

        int max = 0;
        for (int i = 0; i < dict.length; i++) {
            for (int j = i + 1; j < dict.length; j++) {
                Set<Character> intersect = new HashSet<>(dict[i]);
                intersect.retainAll(dict[j]);
                if (intersect.isEmpty()) // Two disjoint words
                    max = Math.max(max, words[i].length() * words[j].length());
            }
        }
        return max;
    }

    // My 2AC: but shorter thanks to Stefan's "Reversed" loop
    // Normal:   0-1,2,3   1-2,3   2,3
    // Reversed: 0   1-0   2-0,1   3-0,1,2
    public int maxProduct2(String[] words) {
        int[] code = new int[words.length];
        int max = 0;
        for (int i = 0; i < words.length; i++) {
            for (char c : words[i].toCharArray()) {
                code[i] |= 1 << (c - 'a');
            }
            for (int j = 0; j < i; j++) {
                if ((code[i] & code[j]) == 0) {
                    max = Math.max(max, words[i].length() * words[j].length());
                }
            }
        }
        return max;
    }

    // My 1AC
    public int maxProduct1(String[] words) {
        int[] encodes = new int[words.length];
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words[i].length(); j++) {
                encodes[i] |= (1 << (words[i].charAt(j) - 'a'));
            }
        }

        int maxLength = 0;
        for (int i = 0; i < encodes.length; i++) {
            for (int j = i + 1; j < encodes.length; j++) {
                if ((encodes[i] & encodes[j]) == 0) {
                    maxLength = Math.max(maxLength, words[i].length() * words[j].length());
                }
            }
        }
        return maxLength;
    }

}
