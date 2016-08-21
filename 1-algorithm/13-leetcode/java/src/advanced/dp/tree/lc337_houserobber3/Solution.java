package advanced.dp.tree.lc337_houserobber3;

import fundamentals.tree.TreeNode;

/**
 * The thief has found himself a new place for his thievery again. There is only one entrance to this area, called the "root." Besides the root, each house has one and only one parent house. After a tour, the smart thief realized that "all houses in this place forms a binary tree". It will automatically contact the police if two directly-linked houses were broken into on the same night.
 * Determine the maximum amount of money the thief can rob tonight without alerting the police.
 * Example 1:
 *    3
 *   / \
 *  2   3
 *   \   \
 *    3   1
 * Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
 * Example 2:
 *     3
 *    / \
 *   4   5
 *  / \   \
 * 1   3   1
 * Maximum amount of money the thief can rob = 4 + 5 = 9.
 */
public class Solution {

    public int rob(TreeNode root) {
        int[] max = doRob(root);
        return Math.max(max[0], max[1]);
    }

    private int[] doRob(TreeNode root) {
        if (root == null) {
            return new int[] { 0, 0 };
        }

        // int[0] includes root, int[1] do not.
        int[] leftMax = doRob(root.left);
        int[] rightMax = doRob(root.right);

        int[] rootMax = new int[2];
        rootMax[0] = leftMax[1] + rightMax[1] + root.val;                                   // error1: add rather than pick max between left and right
        rootMax[1] = Math.max(leftMax[0], leftMax[1]) + Math.max(rightMax[0], rightMax[1]); // error2: when root is excluded
        return rootMax;                                                                     // try max combination, left[0] and right[0] may not be the largest!
    }

}
