package fundamentals.tree.dfs.inorder.lc173_binarysearchtreeiterator;

import fundamentals.tree.TreeNode;

import java.util.Stack;

/**
 * Implement an iterator over a binary search tree (BST).
 * Your iterator will be initialized with the root node of a BST.
 * Calling next() will return the next smallest number in the BST.
 * Note: next() and hasNext() should run in average O(1) time and uses O(h) memory,
 * where h is the height of the tree.
 *
 * My 2nd attempt
 */
public class BSTIterator {

    private Stack<TreeNode> stack;

    private TreeNode cur;

    public BSTIterator(TreeNode root) {
        this.stack = new Stack<>();
        this.cur = root;
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stack.isEmpty() || cur != null;
    }

    /** @return the next smallest number */
    public int next() {
        int val = 0;
        while (hasNext()) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                val = cur.val;
                cur = cur.right;
                break;
            }
        }
        return val;
    }

}
