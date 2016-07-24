package fundamentals.tree.traversal.normal.lc145_binarytreepostordertraversal;

import fundamentals.tree.TreeNode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

/**
 * Given a binary tree, return the postorder traversal of its nodes' values.
 * For example: Given binary tree {1,#,2,3}, return [3,2,1].
 */
public class Solution {

    public List<Integer> postorderTraversal(TreeNode root) {
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
