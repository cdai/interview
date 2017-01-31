package lintcode.bst;

import fundamentals.tree.TreeNode;

/**
 */
public class MyBst {

    // 85-Insert Node in a Binary Search Tree
    public TreeNode insertNode(TreeNode root, TreeNode node) {
        if (root == null) return node;
        if (node.val < root.val) {
            root.left = insertNode(root.left, node);
        } else {
            root.right = insertNode(root.right, node);
        }
        return root;
    }

    public TreeNode insertNode_iterative(TreeNode root, TreeNode node) {
        TreeNode cur = root, par = null;
        while (cur != null) {
            par = cur;
            cur = (node.val < cur.val) ? cur.left : cur.right;
        }
        if (par == null) { // root is empty
            root = node;
        } else if (node.val < par.val) {
            par.left = node;
        } else {
            par.right = node;
        }
        return root;
    }

}
