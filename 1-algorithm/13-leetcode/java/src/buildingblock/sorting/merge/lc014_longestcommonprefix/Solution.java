package buildingblock.sorting.merge.lc014_longestcommonprefix;

/**
 * Write a function to find the longest common prefix string amongst an array of strings.
 */
public class Solution {

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.longestCommonPrefix(new String[]{"abc", "ad", "ab"}));
    }

    // O(NK) time.
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        String pre = strs[0];
        for (int i = 1; i < strs.length; i++) {
            while (!strs[i].startsWith(pre)) // back up tmp LCP if str[i] not start with it
                pre = pre.substring(0, pre.length() - 1);
        }
        return pre;
    }

    // Inspired by soulmachine: compare strs[0] with others
    // Fairly faster than mine...
    public String longestCommonPrefix3(String[] strs) {
        if (strs.length == 0) {
            return "";
        }
        for (int i = 0; i < strs[0].length(); i++) {
            for (int j = 0; j < strs.length; j++) {
                if (strs[j].length() <= i || strs[0].charAt(i) != strs[j].charAt(i)) {
                    return strs[0].substring(0, i);
                }
            }
        }
        return strs[0];
    }

    // My 2nd: O(N^2) time
    public String longestCommonPrefix_ugly(String[] strs) {
        if (strs.length == 0) {
            return "";
        }

        int length = 0;
        while (strs[0].length() > length) { // Happens to put this check here!
            for (int i = 1; i < strs.length; i++) {
                if (strs[i].length() <= length
                        || strs[i - 1].charAt(length) != strs[i].charAt(length)) {
                    return strs[0].substring(0, length);
                }
            }
            length++;
        }
        return strs[0];
    }

    // My 1st: as ugly as my 2nd...
    public String longestCommonPrefix1(String[] strs) {
        if (strs.length == 0) {
            return "";
        }

        int i = 0;
        while (true) {
            if (strs[0].length() - 1 < i) { // error: return i > len-1
                return strs[0].substring(0, i);
            }

            for (int j = 1; j < strs.length; j++) {
                if ((strs[j].length() - 1 < i) || (strs[j-1].charAt(i) != strs[j].charAt(i))) {
                    return strs[j].substring(0, i);
                }
            }
            i++;
        }
    }

}
