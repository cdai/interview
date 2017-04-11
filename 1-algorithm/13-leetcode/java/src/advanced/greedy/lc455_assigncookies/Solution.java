package advanced.greedy.lc455_assigncookies;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * Assume you are an awesome parent and want to give your children some cookies. But, you should give each child at most one cookie.
 * Each child i has a greed factor gi, which is the minimum size of a cookie that the child will be content with; and each cookie j has a size sj.
 * If sj >= gi, we can assign the cookie j to the child i, and the child i will be content.
 * Your goal is to maximize the number of your content children and output the maximum number.
 * Note:
 *  You may assume the greed factor is always positive.
 *  You cannot assign more than one cookie to one child.
 */
public class Solution {

    @Test
    void test() {
        Assertions.assertEquals(1, findContentChildren(new int[]{1,2,3}, new int[]{1,1}));
        Assertions.assertEquals(2, findContentChildren(new int[]{1,2}, new int[]{1,2,3}));
        Assertions.assertEquals(2, findContentChildren(new int[]{10,9,8,7}, new int[]{5,6,7,8}));
    }

    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);

        int i = 0;
        for (int j = 0; i < g.length && j < s.length; j++) {
            if (s[j] >= g[i]) { // Make content! Otherwise try to assign bigger cookie to this child
                i++;
            }
        }
        return i;
    }

}
