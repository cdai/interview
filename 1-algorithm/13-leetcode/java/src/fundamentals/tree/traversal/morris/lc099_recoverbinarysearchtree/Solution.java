package fundamentals.tree.traversal.morris.lc099_recoverbinarysearchtree;

import fundamentals.tree.TreeNode;

/**
 * Two elements of a binary search tree (BST) are swapped by mistake.
 * Recover the tree without changing its structure.
 * Note: A solution using O(n) space is pretty straight forward. Could you devise a constant space solution?
 */
public class Solution {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(5);
        root.right = new TreeNode(7);
        root.right.left = new TreeNode(3);
        root.right.right = new TreeNode(8);
        new Solution().recoverTree(root);
    }

    public void recoverTree(TreeNode root) {
        TreeNode node1 = null, node2 = null;

        // Do Morris traversal, find first and last inverted node
        TreeNode cur = root, prev = null;
        while (cur != null) {
            if (cur.left == null) {
                // Visit current node and go ahead on right
                if (prev != null && prev.val > cur.val) {
                    if (node1 == null) {
                        node1 = prev;
                    }
                    node2 = cur;    // error: not in else, since [0,1] cause node2=null
                }
                prev = cur;

                cur = cur.right;
            } else {
                // Find rightmost child in left subtree
                TreeNode rightmost = cur.left;
                while (rightmost.right != null && rightmost.right != cur) {
                    rightmost = rightmost.right;
                }
                // If left subtree is already visited, clear and go right
                if (rightmost.right == cur) {
                    // Visit current node and go ahead on right
                    if (prev != null && prev.val > cur.val) {
                        if (node1 == null) {
                            node1 = prev;
                        }
                        node2 = cur;    // error: not in else, since [0,1] cause node2=null
                    }
                    prev = cur;

                    rightmost.right = null;
                    cur = cur.right;
                } else {
                    rightmost.right = cur;
                    cur = cur.left;
                }
            }
        }
        swap(node1, node2);
    }

    private void swap(TreeNode node1, TreeNode node2) {
        if (node1 == null || node2 == null) {
            return;
        }
        int tmp = node1.val;
        node1.val = node2.val;
        node2.val = tmp;
    }


    // Wrong! Cannot count as constant space using recursion
    private TreeNode doRecover(TreeNode root) {
        if (root == null) {
            return null;
        }

        TreeNode left = doRecover(root.left);
        TreeNode right = doRecover(root.right);

        if (left == null) {
            left = (root.left != null && root.left.val > root.val) ? root : null;
        }
        if (right == null) {
            right = (root.right != null && root.right.val < root.val) ? root : null;
        }

        if (left != null && right != null) {    // Find mistakely swapped nodes, swap them back
            swap2(left, right);
            return null;
        } else if (left != null) {              // Only find one, return it to upper level to handle
            return left;
        } else if (right != null) {
            return right;
        } else {                                // Find nothing, return null
            return null;
        }
    }

    private void swap2(TreeNode root1, TreeNode root2) {
        TreeNode node1 = (root1.left != null && root1.left.val > root1.val) ? root1.left : root1.right;
        TreeNode node2 = (root2.left != null && root2.left.val > root2.val) ? root2.left : root2.right;

        TreeNode tmp1 = node1.left;
        TreeNode tmp2 = node1.right;
        node1.left = node2.left;
        node1.right = node2.right;
        node2.left = tmp1;
        node2.right = tmp2;

        if (node1 == root1.left) {
            root1.left = node2;
        } else {
            root1.right = node2;
        }
        if (node2 == root2.left) {
            root2.left = node1;
        } else {
            root2.right = node1;
        }
    }

}
