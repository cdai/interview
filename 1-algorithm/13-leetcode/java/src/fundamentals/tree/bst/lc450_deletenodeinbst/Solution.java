package fundamentals.tree.bst.lc450_deletenodeinbst;

import fundamentals.tree.TreeNode;

/**
 * Given a root node reference of a BST and a key, delete the node with the given key in the BST.
 * Return the root node reference (possibly updated) of the BST.
 */
public class Solution {

    // O(height) time.
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;
        if (root.val > key) {
            root.left = deleteNode(root.left, key);
        } else if (root.val < key) {
            root.right = deleteNode(root.right, key);
        } else {
            if (root.left == null) return root.right;
            if (root.right == null) return root.left;

            // Find root's successor (leftmost node of right subtree)
            TreeNode suc = null;
            for (TreeNode p = root.right; p != null; p = p.left) {
                suc = p;
            }
            root.val = suc.val;                           // cannot be null since both left and right are not
            root.right = deleteNode(root.right, suc.val); // nice trick! because we don't know suc's parent
        }
        return root;
    }

}
