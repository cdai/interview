package fundamentals.tree.validate.lc101_symmetrictree;

import fundamentals.tree.TreeNode;

/**
 * Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
 * For example, this binary tree [1,2,2,3,4,4,3] is symmetric. But the following [1,2,2,null,3,null,3] is not.
 */
public class Solution {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isSymmetric(root.left, root.right);
    }

    private boolean isSymmetric(TreeNode tree1, TreeNode tree2) {
        if (tree1 == null || tree2 == null) {
            return (tree1 == null && tree2 == null);
        }
        return tree1.val == tree2.val &&
                isSymmetric(tree1.left, tree2.right) &&
                isSymmetric(tree1.right, tree2.left);
    }
}
