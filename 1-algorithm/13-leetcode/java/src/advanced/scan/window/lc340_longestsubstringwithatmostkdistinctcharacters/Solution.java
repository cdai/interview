package advanced.scan.window.lc340_longestsubstringwithatmostkdistinctcharacters;

/**
 */
public class Solution {

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.longestSubstr("eceba", 3));
    }

    public int longestSubstr(String s, int k) {
        int[] cnt = new int[255];
        int max = 0, from = 0, size = 0;
        for (int i = 0; i < s.length(); i++) {
            if (cnt[s.charAt(i)]++ == 0) size++;
            while (size > k)
                if (--cnt[s.charAt(from++)] == 0) size--;
            max = Math.max(max, i - from + 1);
        }
        return max;
    }

}
