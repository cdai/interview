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

    

}
