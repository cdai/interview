package fundamentals.tree.bfs.lc513_findbottomlefttreevalue;

import fundamentals.tree.TreeNode;

/**
 * Given a binary tree, find the leftmost value in the last row of the tree.
 */
public class Solution {

    private int lmostVal = 0, lmostDep = -1;

    public int findBottomLeftValue(TreeNode root) {
        dfs(root, 0);
        return lmostVal;
    }

    // Same performance due to no tail recursion optimization in Java?
    private void dfs(TreeNode root, int depth) {
        if (root == null) return;
        if (lmostDep < depth) {
            lmostVal = root.val;
            lmostDep = depth;
        }
        dfs(root.left, depth + 1);
        dfs(root.right, depth + 1);
    }

    private void dfs2(TreeNode root, int depth) {
        if (root == null) return;
        dfs(root.left, depth + 1);
        dfs(root.right, depth + 1);
        if (lmostDep < depth) {
            lmostVal = root.val;
            lmostDep = depth;
        }
    }

}
