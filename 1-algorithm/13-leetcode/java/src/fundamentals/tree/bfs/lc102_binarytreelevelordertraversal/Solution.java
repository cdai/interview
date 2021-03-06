package fundamentals.tree.bfs.lc102_binarytreelevelordertraversal;

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
        List<List<Integer>> ret = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();
        if (root != null) q.offer(root);
        while (!q.isEmpty()) {
            List<Integer> level = new ArrayList<>();
            for (int i = q.size(); i > 0; i--) {
                TreeNode n = q.poll();
                level.add(n.val);
                if (n.left != null) q.offer(n.left);
                if (n.right != null) q.offer(n.right);
            }
            ret.add(level);
        }
        return ret;
    }

    // My 2nd: O(N) time, O(width) space
    public List<List<Integer>> levelOrder2(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        if (root != null) q.offer(root);
        List<List<Integer>> ret = new ArrayList<>();
        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> level = new ArrayList<>(size);
            while (size-- > 0) {
                TreeNode n = q.poll();
                level.add(n.val);
                if (n.left != null) q.offer(n.left);
                if (n.right != null) q.offer(n.right);
            }
            ret.add(level);
        }
        return ret;
    }

    // Interesting DFS solution from leetcode discuss
    // This is much faster than BFS version?! Beat 85%!!!
    public List<List<Integer>> levelOrder_dfs(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        dfs(result, root, 0);
        return result;
    }

    private void dfs(List<List<Integer>> result, TreeNode root, int depth) {
        if (root == null) return;
        if (result.size() <= depth) {
            result.add(new ArrayList<>());
        }
        result.get(depth).add(root.val);
        dfs(result, root.left, depth + 1);
        dfs(result, root.right, depth + 1);
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
