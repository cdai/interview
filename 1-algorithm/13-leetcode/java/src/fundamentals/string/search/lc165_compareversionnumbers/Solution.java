package fundamentals.string.search.lc165_compareversionnumbers;

import java.util.Arrays;

/**
 * Compare two version numbers version1 and version2. If version1 > version2 return 1,
 * if version1 < version2 return -1, otherwise return 0.
 * You may assume that the version strings are non-empty and contain only digits and the . character.
 * The . character does not represent a decimal point and is used to separate number sequences.
 * For instance, 2.5 is not "two and a half" or "half way to version three",
 * it is the fifth second-level revision of the second first-level revision.
 * Here is an example of version numbers ordering: 0.1 < 1.1 < 1.2 < 13.37
 */
public class Solution {

    public int compareVersion(String version1, String version2) {
        int[] ver1 = parse(version1);
        int[] ver2 = parse(version2);

        int i = 0;
        for (; (i < ver1.length) && (i < ver2.length) && (ver1[i] == ver2[i]); i++);

        /* assert: ver1 = ver2 on range [0,i] */
        // 3 conditions causing termination of loop above, check it seperately
        if (i == ver1.length) {
            return (i < ver2.length) ? -1 : 0;
        }
        if (i == ver2.length) {
            return (i < ver1.length) ? 1 : 0;
        }
        return (ver1[i] > ver2[i]) ? 1 : ((ver1[i] < ver2[i]) ? -1 : 0);
    }

    private int[] parse(String version) {
        String[] vers = version.split("\\.");
        int[] nums = new int[vers.length];
        int lastNonZero = 0;
        for (int i = 0; i < vers.length; i++) {
            nums[i] = Integer.valueOf(vers[i]);
            if (nums[i] != 0) {
                lastNonZero = i;
            }
        }
        return Arrays.copyOfRange(nums, 0, lastNonZero + 1); // error1: version 1.0.0=1
    }

}
