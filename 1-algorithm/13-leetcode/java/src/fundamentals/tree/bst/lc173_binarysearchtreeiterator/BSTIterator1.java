package fundamentals.tree.bst.lc173_binarysearchtreeiterator;

import fundamentals.tree.TreeNode;

import java.util.Stack;

/**
 * Implement an iterator over a binary search tree (BST).
 * Your iterator will be initialized with the root node of a BST.
 * Calling next() will return the next smallest number in the BST.
 * Note: next() and hasNext() should run in average O(1) time and uses O(h) memory,
 * where h is the height of the tree.
 */
public class BSTIterator1 {

    private Stack<TreeNode> stack = new Stack<>();

    public BSTIterator1(TreeNode root) {
        pushAllLeft(root);
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    // This is inorder traversal, each node is pushed onto stack once
    // So it's O(1) on average, and stack is O(h)
    /** @return the next smallest number */
    public int next() {
        TreeNode cur = stack.pop();
        pushAllLeft(cur.right);
        return cur.val;
    }

    private void pushAllLeft(TreeNode root) {
        for (; root != null; root = root.left) stack.push(root);
    }

}
