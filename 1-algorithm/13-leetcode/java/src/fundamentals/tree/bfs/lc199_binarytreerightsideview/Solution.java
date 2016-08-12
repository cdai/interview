package fundamentals.tree.bfs.lc199_binarytreerightsideview;

import fundamentals.tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given a binary tree, imagine yourself standing on the right side of it,
 * return the values of the nodes you can see ordered from top to bottom.
 * For example: Given the following binary tree,
 *    1            <---
 *  /   \
 * 2     3         <---
 * \     \
 *  5     4       <---
 * You should return [1, 3, 4].
 */
public class Solution {

    // DFS inspired by leetcode discuss.
    // O(N) time too, but advantage is: O(logN) space
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        doGetRightSideView(result, root, 0);
        return result;
    }

    // Improved: essentially, it's Preorder Traversal from right side
    private void doGetRightSideView(List<Integer> result, TreeNode root, int height) {
        if (root == null) {
            return;
        }

        // Only add first node on each level
        if (result.size() == height) {
            result.add(root.val);
        }

        // This is the key: from right to left!
        doGetRightSideView(result, root.right, height + 1);
        doGetRightSideView(result, root.left, height + 1);
    }

    private void doGetRightSideView_fromLeft(List<Integer> result, TreeNode root, int height) {
        if (root == null) {
            return;
        }

        // Add or replace if exist
        if (result.size() > height) {
            result.set(height, root.val);
        } else {
            result.add(root.val);
        }

        doGetRightSideView(result, root.left, height + 1);
        doGetRightSideView(result, root.right, height + 1);
    }

    // My 2nd: BFS O(N) time, O(width) space
    public List<Integer> rightSideView_bfs(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        if (root != null) {
            queue.offer(root);
        }

        List<Integer> rightView = new ArrayList<>();
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                if (size == 0) {
                    rightView.add(node.val);
                }
            }
        }
        return rightView;
    }

}
