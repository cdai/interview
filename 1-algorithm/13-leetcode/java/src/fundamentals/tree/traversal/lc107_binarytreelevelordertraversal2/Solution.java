package fundamentals.tree.traversal.lc107_binarytreelevelordertraversal2;

import fundamentals.tree.TreeNode;

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
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
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
