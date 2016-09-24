package fundamentals.tree.bfs.lc107_binarytreelevelordertraversal2;

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

    // My 3AC
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        doLevelOrderBottom(result, root, 0);
        Collections.reverse(result);
        return result;
    }

    private void doLevelOrderBottom(List<List<Integer>> result, TreeNode root, int depth) {
        if (root == null) return;
        if (result.size() <= depth) result.add(new ArrayList<>());

        result.get(depth).add(root.val);
        doLevelOrderBottom(result, root.left, depth + 1);
        doLevelOrderBottom(result, root.right, depth + 1);
    }

    // DFS solution inspired by solution from leetcode discuss
    public List<List<Integer>> levelOrderBottom_dfswithissue(TreeNode root) {
        List<List<Integer>> result = new LinkedList<>();
        doLevelOrderBottom(result, root, 1);
        return result;
    }

    private void doLevelOrderBottom2(List<List<Integer>> result, TreeNode root, int depth) {
        if (root == null) return;
        if (result.size() < depth) result.add(0, new ArrayList<>());

        result.get(result.size() - depth).add(root.val);   // Note that: size() - height. Warning! O(N) to locate for LinkedList!!!
        doLevelOrderBottom2(result, root.left, depth + 1);
        doLevelOrderBottom2(result, root.right, depth + 1);
    }

    // My 3AC: Don't forget to use LinkedList
    // My 2nd: BFS solution with only one line changed
    public List<List<Integer>> levelOrderBottom_bfs(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        if (root != null) q.offer(root);
        List<List<Integer>> ret = new LinkedList<>();
        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> level = new ArrayList<>(size);
            while (size-- > 0) {
                TreeNode n = q.poll();
                level.add(n.val);
                if (n.left != null) q.offer(n.left);
                if (n.right != null) q.offer(n.right);
            }
            ret.add(0, level); // Only difference!!!
        }
        return ret;
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
