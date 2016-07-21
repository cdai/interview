package fundamentals.tree.construct.lc114_flattenbinarytreetolinkedlist;

import fundamentals.tree.TreeNode;

/**
 * Given a binary tree, flatten it to a linked list in-place.
 * For example,
 * Given
 *     1
 *    / \
 *   2   5
 *  / \   \
 * 3   4   6
 * The flattened tree should look like:
 *  1
 *   \
 *    2
 *     \
 *      3
 *       \
 *        4
 *         \
 *          5
 *           \
 *            6
 */
public class Solution {

    public void flatten(TreeNode root) {
        doFlatten(root);
    }

    private TreeNode doFlatten(TreeNode node) {
        if (node == null) {
            return null;
        }

        TreeNode rightmostInLeft = doFlatten(node.left);
        TreeNode rightmostInRight = doFlatten(node.right);

        // This is a leaf!
        if (rightmostInLeft == null && rightmostInRight == null) {
            return node;
        }
        // Has left child, do flatten anyway
        if (rightmostInLeft != null) {
            TreeNode right = node.right;
            node.right = node.left;
            node.left = null;                   // error1: clear it!
            rightmostInLeft.right = right;
        }
        // No right child (node.right=null -> rightmostInRight=null)
        return (rightmostInRight == null) ? rightmostInLeft : rightmostInRight;
    }

}
