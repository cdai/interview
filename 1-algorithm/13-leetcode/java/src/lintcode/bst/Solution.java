package lintcode.bst;

import fundamentals.tree.TreeNode;

import java.util.ArrayList;

/**
 */
public class Solution {

    // 11-Search Range in Binary Search Tree
    public ArrayList<Integer> searchRange(TreeNode root, int k1, int k2) {
        ArrayList<Integer> ret = new ArrayList<>();
        dfs(ret, root, k1, k2);
        return ret;
    }

    private void dfs(ArrayList<Integer> ret, TreeNode root, int k1, int k2) {
        if (root == null) return;
        if (k1 < root.val) dfs(ret, root.left, k1, k2);
        if (k1 <= root.val && root.val <= k2) ret.add(root.val);
        if (root.val < k2) dfs(ret, root.right, k1, k2);
    }

    // 375-Clone Binary Tree
    public TreeNode cloneTree(TreeNode root) {
        if (root == null) return null;
        TreeNode copy = new TreeNode(root.val);
        copy.left = cloneTree(root.left);
        copy.right = cloneTree(root.right);
        return copy;
    }

    // 245-Subtree: O(mn)
    public boolean isSubtree(TreeNode t1, TreeNode t2) {
        if (t1 == null) return t2 == null;
        return identical(t1, t2) || isSubtree(t1.left, t2) || isSubtree(t1.right, t2);
    }

    private boolean identical(TreeNode t1, TreeNode t2) {
        if (t1 == null || t2 == null) return t1 == t2;
        return t1.val == t2.val && identical(t1.left, t2.left) && identical(t1.right, t2.right);
    }

    // 469-Identical Binary Tree (Same Tree)
    // [][], [][b], [a][], [a][a]...
    public boolean isIdentical(TreeNode a, TreeNode b) {
        if (a == null || b == null) return a == null && b == null;
        return a.val == b.val &&
                isIdentical(a.left, b.left) &&
                isIdentical(a.right, b.right);
    }

}
