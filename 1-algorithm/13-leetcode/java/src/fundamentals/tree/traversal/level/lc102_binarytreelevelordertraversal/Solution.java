package fundamentals.tree.traversal.level.lc102_binarytreelevelordertraversal;

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

    // My 2nd: O(N) time, O(width) space
    public List<List<Integer>> levelOrder2(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        if (root != null) {
            queue.offer(root);
        }

        List<List<Integer>> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> level = new ArrayList<>(size);
            while (size-- > 0) {
                TreeNode node = queue.poll();
                level.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            result.add(level);
        }
        return result;
    }

    // Interesting DFS solution from leetcode discuss
    // This is much faster than BFS version?! Beat 85%!!!
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        doLevelOrder(result, root, 0);
        return result;
    }

    private void doLevelOrder(List<List<Integer>> result, TreeNode root, int height) {
        if (root == null) {
            return;
        }

        if (result.size() <= height) {
            result.add(new ArrayList<>());
        }
        result.get(height).add(root.val);
        doLevelOrder(result, root.left, height + 1);
        doLevelOrder(result, root.right, height + 1);
    }

    // My 1st
    public List<List<Integer>> levelOrder1(TreeNode root) {
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
