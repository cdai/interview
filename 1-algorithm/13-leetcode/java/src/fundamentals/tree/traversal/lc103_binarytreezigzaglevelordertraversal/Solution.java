package fundamentals.tree.traversal.lc103_binarytreezigzaglevelordertraversal;

import fundamentals.tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given a binary tree, return the zigzag level order traversal of its nodes' values.
 * (ie, from left to right, then right to left for the next level and alternate between).
 * For example: Given binary tree [3,9,20,null,null,15,7],
 *  return its zigzag level order traversal as:
 * [
 *  [3],
 *  [20,9],
 *  [15,7]
 * ]
 */
public class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> vals = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        boolean isFromLeft = true;

        if (root != null) {
            queue.offer(root);
        }

        while (!queue.isEmpty()) {
            LinkedList<Integer> val = new LinkedList<>();
            int size = queue.size();
            while (size-- > 0) {
                TreeNode node = queue.poll();

                // Use as a deque to reverse
                if (isFromLeft) {
                    val.addLast(node.val);
                } else {
                    val.addFirst(node.val);
                }

                if (node.left != null) { // error1: for BFS, push left to queue first, nor right to stack first for DFS
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            vals.add(val);
            isFromLeft = !isFromLeft;
        }
        return vals;
    }
}
