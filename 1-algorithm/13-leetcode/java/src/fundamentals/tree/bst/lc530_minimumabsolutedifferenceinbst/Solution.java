package fundamentals.tree.bst.lc530_minimumabsolutedifferenceinbst;

import fundamentals.tree.TreeNode;

import java.util.Stack;

/**
 * Given a binary search tree with non-negative values, find the minimum absolute difference between values of any two nodes.
 */
public class Solution {

    public int getMinimumDifference(TreeNode root) {
        Stack<TreeNode> s = new Stack<>();
        TreeNode cur = root, pre = null;
        int min = Integer.MAX_VALUE;
        while (!s.isEmpty() || cur != null) {
            if (cur != null) {
                s.push(cur);
                cur = cur.left;
            } else {
                cur = s.pop();
                if (pre != null) {
                    min = Math.min(min, cur.val - pre.val);
                }
                pre = cur;
                cur = cur.right;
            }
        }
        return min;
    }

}
