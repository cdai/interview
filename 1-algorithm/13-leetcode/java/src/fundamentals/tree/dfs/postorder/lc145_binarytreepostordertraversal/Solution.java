package fundamentals.tree.dfs.postorder.lc145_binarytreepostordertraversal;

import fundamentals.tree.TreeNode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Stack;

/**
 * Given a binary tree, return the postorder traversal of its nodes' values.
 * For example: Given binary tree {1,#,2,3}, return [3,2,1].
 */
public class Solution {

    //      1
    //    /   \
    //   2     5
    //  / \   / \
    // 3   4  6  7
    // Pre-order:  1-2-3-4-5-6-7
    // Post-order:   3-4-2-6-7-5-1
    // root-left-right => left-right-root
    public List<Integer> postorderTraversal(TreeNode root) {
        LinkedList<Integer> ret = new LinkedList<>();
        Stack<TreeNode> s = new Stack<>();
        TreeNode p = root;
        while (!s.isEmpty() || p != null) {
            if (p != null) {
                ret.addFirst(p.val);    // Add root ahead but reversely
                s.push(p);
                p = p.right;            // Traverse in reversed pre-order!
            } else
                p = s.pop().left;
        }
        return ret;
    }

    // My 1AC using Set
    public List<Integer> postorderTraversal_messy(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Stack<TreeNode> stack = new Stack<>();
        Set<TreeNode> visit = new HashSet<>();

        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();

            boolean isLeftDone = true, isRightDone = true;
            if (node.left != null && !visit.contains(node.left)) {
                isLeftDone = false;
            }
            if (node.right != null && !visit.contains(node.right)) {
                isRightDone = false;
            }

            if (!isLeftDone || !isRightDone) {
                stack.push(node);
                if (!isRightDone) {     // error1: left should be on the top
                    stack.push(node.right);
                }
                if (!isLeftDone) {
                    stack.push(node.left);
                }
            } else {
                result.add(node.val);
                visit.add(node);
            }
        }
        return result;
    }

}
