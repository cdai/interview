package fundamentals.tree.bfs.lc103_binarytreezigzaglevelordertraversal;

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

    // My 2nd: BFS with height to determine insert order
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        if (root != null) queue.offer(root);
        List<List<Integer>> result = new ArrayList<>();
        for (int height = 0; !queue.isEmpty(); height++) {
            int size = queue.size();
            List<Integer> level = new ArrayList<>(size);
            while (size-- > 0) {
                TreeNode node = queue.poll();
                level.add((height % 2 == 0 ? level.size() : 0), node.val);
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            result.add(level);
        }
        return result;
    }

    // DFS solution inspired from leetcode discuss
    public List<List<Integer>> zigzagLevelOrder_dfs(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        doZigzagLevelOrder(result, root, 0);
        return result;
    }

    // must be height-1 if height starts from 1 instead 0
    private void doZigzagLevelOrder(List<List<Integer>> result, TreeNode root, int height) {
        if (root == null) return;
        if (result.size() <= height) result.add(new ArrayList<>());
        result.get(height).add((height % 2 == 0) ? result.get(height).size() : 0, root.val);
        doZigzagLevelOrder(result, root.left, height + 1);
        doZigzagLevelOrder(result, root.right, height + 1);
    }

    // My 1st
    public List<List<Integer>> zigzagLevelOrder1(TreeNode root) {
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
