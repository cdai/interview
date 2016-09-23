package advanced.scan.window.lc076_minimumwindowsubstring;

/**
 * Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).
 * For example, S = "ADOBECODEBANC", T = "ABC". Minimum window is "BANC".
 * Note: If there is no such window in S that covers all characters in T, return the empty string "".
 * If there are multiple such windows, you are guaranteed that there will always be only one unique minimum window in S.
 */
public class Solution {

    // My 3AC. O(N) time.
    public String minWindow(String s, String t) {
        int[] cnt = new int[256];
        for (char c : t.toCharArray()) cnt[c]++;

        int min = Integer.MAX_VALUE, from = 0, total = t.length();
        for (int i = 0, j = 0; i < s.length(); i++) {
            if (cnt[s.charAt(i)]-- > 0) total--;
            while (total == 0) {                    // total=0 means valid window
                if (i - j + 1 < min) {
                    min = i - j + 1;
                    from = j;
                }
                if (++cnt[s.charAt(j++)] > 0) total++;
            }
        }
        return (min == Integer.MAX_VALUE) ? "" : s.substring(from, from + min);
    }

    // Thanks to zjh08177's template, beat 85.58%!
    // eg. S="abcabba", T="aab"
    // i=0: count=[2,1], total=3
    // i=3: count=[0,0,-1], total=0 -> j=1: count=[1,0,-1], total=1
    // i=5: count=[1,-2,-1], total=1
    // i=6: count=[0,-2,-1], total=0 -> j=4, count=[1,-1,0], total=1
    public String minWindow2(String s, String t) {
        // Statistic for count of char in t
        int[] count = new int[255];
        for (char c : t.toCharArray())
            count[c]++;

        // Move i as right boundary to find a valid window
        int min = Integer.MAX_VALUE, minLeft = 0, total = t.length();
        for (int i = 0, j = 0; i < s.length(); i++) {
            // S char exists in T
            if (count[s.charAt(i)] > 0)
                total--;

            // If char are NOT in T, it will be negative for a moment (until window shrinks)
            // That means char appears in S much more than what we need to match it in T
            count[s.charAt(i)]--;

            // Find a valid window, move j to find a smaller one
            while (total == 0) {
                // Update min since total=0 means they are matched now!
                if (min > i - j + 1) {
                    min = i - j + 1;
                    minLeft = j;
                }

                // But only when ... total++
                count[s.charAt(j)]++;
                if (count[s.charAt(j)] > 0) {
                    total++;
                }
                j++;
            }
        }
        return min == Integer.MAX_VALUE ? "" : s.substring(minLeft, minLeft + min);
    }

}
