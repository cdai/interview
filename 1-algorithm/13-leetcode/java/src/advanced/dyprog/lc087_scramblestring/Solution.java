package advanced.dyprog.lc087_scramblestring;

/**
 *
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().isScramble("great", "rgtae"));
    }

    public boolean isScramble(String s1, String s2) {
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
