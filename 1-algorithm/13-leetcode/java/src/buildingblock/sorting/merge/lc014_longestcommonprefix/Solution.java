package buildingblock.sorting.merge.lc014_longestcommonprefix;

/**
 * Write a function to find the longest common prefix string amongst an array of strings.
 */
public class Solution {

    public String longestCommonPrefix(String[] strs) {
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
