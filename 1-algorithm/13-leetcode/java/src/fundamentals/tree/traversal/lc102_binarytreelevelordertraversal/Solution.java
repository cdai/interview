package fundamentals.tree.traversal.lc102_binarytreelevelordertraversal;

import fundamentals.tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given a binary tree, return the level order traversal of its nodes' values.
 * (ie, from left to right, level by level).
 * For example: Given binary tree [3,9,20,null,null,15,7], return its level order traversal as:
 * [
 *  [3],
 *  [9,20],
 *  [15,7]
 * ]
 */
public class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> vals = new ArrayList<>();
        if (root == null) {
            return vals;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> valsOfSameLevel = new ArrayList<>();
            int size = queue.size();

            // Pop all nodes in this level
            while (size-- > 0) {
                TreeNode node = queue.poll();
                valsOfSameLevel.add(node.val);

                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            vals.add(valsOfSameLevel);
        }
        return vals;
    }
}
