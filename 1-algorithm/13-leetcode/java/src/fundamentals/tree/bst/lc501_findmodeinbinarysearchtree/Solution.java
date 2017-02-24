package fundamentals.tree.bst.lc501_findmodeinbinarysearchtree;

import fundamentals.tree.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a binary search tree (BST) with duplicates, find all the mode(s) (the most frequently occurred element) in the given BST.
 */
public class Solution {

    // My O(N) time and space solution.
    public int[] findMode(TreeNode root) {
        Map<Integer,Integer> freq = new HashMap<>();
        int max = dfs(root, freq);
        return freq.entrySet().stream().
                filter(e -> e.getValue() == max).
                mapToInt(e -> e.getKey()).toArray();
    }

    public int[] findMode2(TreeNode root) {
        Map<Integer,Integer> freq = new HashMap<>();
        int max = dfs(root, freq);

        int len = 0;
        for (Map.Entry<Integer,Integer> e : freq.entrySet()) {
            if (e.getValue() == max) len++;
        }
        int[] ret = new int[len];
        for (Map.Entry<Integer,Integer> e : freq.entrySet()) {
            if (e.getValue() == max) ret[--len] = e.getKey();
        }
        return ret;
    }

    private int dfs(TreeNode root, Map<Integer,Integer> freq) {
        if (root == null) return 0;
        int l = dfs(root.left, freq);
        int r = dfs(root.right, freq);
        freq.put(root.val, freq.getOrDefault(root.val, 0) + 1);
        return Math.max(freq.get(root.val), Math.max(l, r));
    }

}
