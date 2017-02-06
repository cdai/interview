package lintcode.bst;

import fundamentals.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

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

    // Leetcode-285 Inorder Successor in BST
    // "To find a successor, you just need to find the smallest one that is larger than the given value.
    // Since there are no duplicate values in a BST. It just like the binary search in a sorted list."
    public TreeNode inorderSuccessor(TreeNode root, TreeNode x) {
        TreeNode p = root, pre = null;
        while (p != null) { // Find lowest ancestor whose left child is ancestor of x too
            if (x.val < p.val) {
                pre = p;
                p = p.left;
            } else
                p = p.right;
        }
        return pre;
    }

    // Leetcode-450 Delete Node in BST
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

    // 11-Search Range in Binary Search Tree
    public List<Integer> searchRange(TreeNode root, int k1, int k2) {
        List<Integer> ret = new ArrayList<>();
        dfs(ret, root, k1, k2);
        return ret;
    }

    private void dfs(List<Integer> ret, TreeNode root, int k1, int k2) {
        if (root == null) return;
        if (k1 < root.val) dfs(ret, root.left, k1, k2);
        if (k1 <= root.val && root.val <= k2) ret.add(root.val);
        if (root.val < k2) dfs(ret, root.right, k1, k2);
    }

    // minimum, maximum, delete min, floor, ceiling, delete, rank, select refer to <Algorithm 4e>

}
