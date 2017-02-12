package fundamentals.tree.dfs.postorder.lc226_invertbinarytree;

import fundamentals.tree.TreeNode;

import java.util.Stack;

/**
 * Invert a binary tree.
 *      4
 *    /   \
 *   2     7
 *  / \   / \
 * 1   3 6   9
 * to
 *      4
 *    /   \
 *   7     2
 *  / \   / \
 * 9   6 3   1
 */
public class Solution {

    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        TreeNode right = root.right;
        root.right = invertTree(root.left);
        root.left = invertTree(right);
        return root;
    }

    // Also works if we replace Stack with Queue
    public TreeNode invertTree_iterative(TreeNode root) {
        Stack<TreeNode> s = new Stack<>();
        if (root != null) s.push(root);
        while (!s.isEmpty()) {
            TreeNode node = s.pop();
            if (node.left != null) s.push(node.left);
            if (node.right != null) s.push(node.right);
            TreeNode tmp = node.left;
            node.left = node.right;
            node.right = tmp;
        }
        return root;
    }

    // My 2nd: O(N) time, O(h) space
    public TreeNode invertTree2(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode left = root.left, right = root.right;
        root.left = invertTree(right);
        root.right = invertTree(left);
        return root;
    }

    // My 1st
    public TreeNode invertTree1(TreeNode root) {
        if (root == null) {
            return null;
        }

        TreeNode invertLeft = invertTree(root.left);
        TreeNode invertRight = invertTree(root.right);

        root.left = invertRight;
        root.right = invertLeft;
        return root;
    }

}
