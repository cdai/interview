package fundamentals.tree.bst.lc285_inordersuccessorinbst;

import fundamentals.tree.TreeNode;

import java.util.Stack;

/**
 * Inorder Successor in BST Given a binary search tree and a node in it, find the in-order successor of that node in the BST.
 */
public class Solution {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(15);
        root.left = new TreeNode(6);
        root.right = new TreeNode(18);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(7);
        root.right.left = new TreeNode(17);
        root.right.right = new TreeNode(20);
        root.left.left.left = new TreeNode(2);
        root.left.left.right = new TreeNode(4);
        root.left.right.right = new TreeNode(13);
        root.left.right.right.left = new TreeNode(9);

        // Example from CLRS Chapter Tree
        Solution sol = new Solution();
        System.out.println(sol.inorderSuccessor(root, root).val); // 15 -> 17
        System.out.println(sol.inorderSuccessor(root, root.left.right.right).val); // 13 -> 15
        System.out.println(sol.inorderSuccessor(root, root.right.right)); // null
    }

    // O(1) space solution from CLRS. Ignore first case from dietpepsi solution:
    // "To find a successor, you just need to find the smallest one that is larger than the given value.
    // Since there are no duplicate values in a BST. It just like the binary search in a sorted list."

    // O(h) time. Equivalent find insert position for x+1 because we also go right upon x.val=p.val
    // Case 1: x is a leaf
    //    o - suc
    //   /
    //  o
    //   \
    //    \
    //     \
    //      x
    //
    // Case 2: x is a non-leaf
    //    o
    //   /
    //  o
    //   \
    //    \
    //     x
    //      \
    //       o
    //      /
    //     /
    //    o - suc
    public TreeNode inorderSuccessor(TreeNode root, TreeNode x) {
        TreeNode p = root, suc = null;
        while (p != null) {
            if (x.val < p.val) {
                suc = p;
                p = p.left;
            } else {
                p = p.right;
            }
        }
        return suc;
    }

    public TreeNode inorderSuccessor_recursive(TreeNode root, TreeNode x) {
        if (root == null) return null;
        if (root.val < x.val) {
            TreeNode suc = inorderSuccessor(root.left, x);
            return suc == null ? root : suc;
        } else {
            return inorderSuccessor(root.right, x);
        }
    }

    // Simple and clear, but with extra O(h) space and O(N) time
    public TreeNode findInorderSuccessor_Oh(TreeNode root, TreeNode x) {
        Stack<TreeNode> s = new Stack<>();
        TreeNode p = root, pre = null;
        while (p != null || !s.isEmpty()) {
            if (p != null) {
                s.push(p);
                p = p.left;
            } else {
                p = s.pop();
                if (pre != null && pre == x)
                    return p;
                pre = p;
                p = p.right;
            }
        }
        return null;
    }

}
