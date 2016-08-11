package fundamentals.tree.traversal.level.lc107_binarytreelevelordertraversal2;

import fundamentals.tree.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given a binary tree, return the bottom-up level order traversal of its nodes' values.
 * (ie, from left to right, level by level from leaf to root).
 * For example: Given binary tree [3,9,20,null,null,15,7], return its bottom-up level order traversal as:
 * [
 *  [15,7],
 *  [9,20],
 *  [3]
 * ]
 */
public class Solution {

    // DFS solution inspired by solution from leetcode discuss
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        doLevelOrderBottom(result, root, 1);
        return result;
    }

    private void doLevelOrderBottom(List<List<Integer>> result, TreeNode root, int height) {
        if (root == null) {
            return;
        }

        if (result.size() < height) {
            result.add(0, new ArrayList<>());
        }
        result.get(result.size() - height).add(root.val);   // Note that: size() - height
        doLevelOrderBottom(result, root.left, height + 1);
        doLevelOrderBottom(result, root.right, height + 1);
    }

    // My 2nd: BFS solution with only one line changed
    public List<List<Integer>> levelOrderBottom2(TreeNode root) {
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
            result.add(0, level); // Only difference!!!
        }
        return result;
    }

    // My 1st
    public List<List<Integer>> levelOrderBottom1(TreeNode root) {
        List<List<Integer>> vals = new LinkedList<>();
        if (root == null) {
            return vals;
        }

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            List<Integer> level = new LinkedList<>();
            int size = q.size();

            while(size-- > 0) {
                TreeNode n = q.poll();
                level.add(n.val);

                if (n.left != null) {
                    q.offer(n.left);
                }
                if (n.right != null) {
                    q.offer(n.right);
                }
            }
            vals.add(level);
        }
        Collections.reverse(vals);
        return vals;
    }
}
