package buildingblock.divideandconquer.lc087_scramblestring;

/**
 * Given a string s1, we may represent it as a binary tree by partitioning it to
 * two non-empty substrings recursively.
 * Below is one possible representation of s1 = "great":
 *       great
 *      /    \
 *     gr    eat
 *    / \    /  \
 *   g   r  e   at
 *  / \
 * a   t
 * To scramble the string, we may choose any non-leaf node and swap its two children.
 * For example, if we choose the node "gr" and swap its two children, it produces a scrambled string "rgeat".
 *       rgeat
 *      /    \
 *     rg    eat
 *    / \    /  \
 *   r   g  e   at
 *  / \
 * a   t
 * We say that "rgeat" is a scrambled string of "great".
 * Similarly, if we continue to swap the children of nodes "eat" and "at", it produces a scrambled string "rgtae".
 *       rgtae
 *      /    \
 *     rg    tae
 *    / \    /  \
 *   r   g  ta  e
 *  / \
 * t   a
 * We say that "rgtae" is a scrambled string of "great".
 * Given two strings s1 and s2 of the same length, determine if s2 is a scrambled string of s1.
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().isScramble("great", "rgtae"));
    }

    // My 2AC: T(N)=Sum(T(K)*T(N-K)) =O(4^N)?, O(N^4) space.
    public boolean isScramble(String s1, String s2) {
        if (s1.equals(s2)) return true;                 // base case
        if (s1.length() != s2.length()) return false;   // pruning-1 (assume non-null)

        int[] letters = new int[256];   // pruning-2 (don't assume all lowercase)
        for (int i = 0; i < s1.length(); i++) {
            letters[s1.charAt(i)]++;
            letters[s2.charAt(i)]--;
        }
        for (int letter : letters)      // note: faster than sort(s1),sort(s2) then compare
            if (letter != 0) return false;

        for (int i = 1; i < s1.length(); i++)
            if ((isScramble(s1.substring(0, i), s2.substring(0, i))
                    && isScramble(s1.substring(i), s2.substring(i)))
                    || (isScramble(s1.substring(0, i), s2.substring(s2.length() - i)) // Here is the key!
                    && isScramble(s1.substring(i), s2.substring(0, s2.length() - i))))
                return true;
        return false;
    }

    // My 1st attempt which is wrong...
    public boolean isScramble1(String s1, String s2) {
        System.out.println(s1 + " - " + s2);
        if (s1.length() == 1) {
            return true;
        }
        if (s1.length() == 2) {
            return (s1.charAt(0) == s2.charAt(1)) && (s1.charAt(1) == s2.charAt(0));
        }

        int idx = s2.indexOf(s1.charAt(0));
        if (idx == -1) {
            return false;
        }

        if (idx == s2.length() - 1) {
            return isScramble(s1.substring(1), s2.substring(0, idx));
        } else {
            return isScramble(s1.substring(0, idx + 1), s2.substring(0, idx + 1)) &&
                    isScramble(s1.substring(idx + 1), s2.substring(idx + 1));
        }

    }

}
