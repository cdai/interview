package fundamentals.tree.bfs.lc515_findlargestvalueineachtreerow;

import fundamentals.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * You need to find the largest value in each row of a binary tree.
 */
public class Solution {

    public List<Integer> largestValues(TreeNode root) {
        List<Integer> max = new ArrayList<>();
        dfs(max, root, 0);
        return max;
    }

    private void dfs(List<Integer> max, TreeNode root, int depth) {
        if (root == null) return;
        if (max.size() <= depth) {
            max.add(root.val);
        } else if (max.get(depth) < root.val) {
            max.set(depth, root.val);
        } /* else max.get(depth) >= root.val */
        dfs(max, root.left, depth + 1);
        dfs(max, root.right, depth + 1);
    }

}
