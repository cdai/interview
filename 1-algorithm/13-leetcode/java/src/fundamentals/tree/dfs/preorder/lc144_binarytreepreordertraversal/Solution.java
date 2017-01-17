package fundamentals.tree.dfs.preorder.lc144_binarytreepreordertraversal;

import fundamentals.tree.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * Given a binary tree, return the preorder traversal of its nodes' values.
 * For example: Given binary tree {1,#,2,3}, return [1,2,3].
 */
public class Solution {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        System.out.println(new Solution().preorderTraversal(root));
    }

    // My 2nd: morris traversal. O(n) time, O(1) space
    public List<Integer> preorderTraversal_morris(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        TreeNode cur = root;
        while (cur != null) {
            if (cur.left == null) {
                result.add(cur.val);        // Exit-1: no left child, go right directly
                cur = cur.right;
            } else {
                // Find rightmost in left subtree
                TreeNode rightmost = cur.left;
                while (rightmost.right != null && rightmost.right != cur) {
                    rightmost = rightmost.right;
                }

                // 1) rightmost = null: then link i to root as footprint before go left
                // 2) rightmost = cur: means we're back to root by footprint, so restore rightmost.right then go right
                if (rightmost.right == null) {
                    result.add(cur.val);    // Exit-2: leaving footprint, be about to visit left subtree soon
                    rightmost.right = cur;
                    cur = cur.left;
                } else {
                    rightmost.right = null;
                    cur = cur.right;
                }
            }
        }
        return result;
    }

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> ret = new ArrayList<>();
        Stack<TreeNode> s = new Stack<>();
        TreeNode p = root;
        while (!s.isEmpty() || p != null) {
            if (p != null) {
                ret.add(p.val);
                s.push(p);
                p = p.left;
            } else
                p = s.pop().right;
        }
        return ret;
    }

    // 3AC.
    // My 2nd: iterative using stack. O(n) time, O(h) space
    public List<Integer> preorderTraversal_stack(TreeNode root) {
        Stack<TreeNode> s = new Stack<>();
        if (root !=null) s.push(root);

        List<Integer> ret = new ArrayList<>();
        while (!s.isEmpty()) {
            TreeNode node = s.pop();
            ret.add(node.val);
            if (node.right != null) s.push(node.right);
            if (node.left != null) s.push(node.left);
        }
        return ret;
    }

    // My 2nd: recursion. O(n) time, O(h) space
    public List<Integer> preorderTraversal_recursion(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }

        List<Integer> result = new ArrayList<>();
        result.add(root.val);
        result.addAll(preorderTraversal(root.left));
        result.addAll(preorderTraversal(root.right));
        return result;
    }

    // My 1st
    public List<Integer> preorderTraversal1(TreeNode root) {
        List<Integer> vals = new ArrayList<>();
        if (root == null) {
            return vals;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();

            // Pre-orderly visit current node
            vals.add(node.val);

            if (node.right != null) { // error2: push right first so make left popped first
                stack.push(node.right);
            }

            if (node.left != null) { // error1: push null into stack by mistake causing NPE
                stack.push(node.left);
            }
        }
        return vals;
    }
}
