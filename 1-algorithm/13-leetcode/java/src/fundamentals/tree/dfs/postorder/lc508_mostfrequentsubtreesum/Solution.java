package fundamentals.tree.dfs.postorder.lc508_mostfrequentsubtreesum;

import fundamentals.tree.TreeNode;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Given the root of a tree, you are asked to find the most frequent subtree sum.
 * The subtree sum of a node is defined as the sum of all the node values formed by the subtree rooted at that node (including the node itself).
 * So what is the most frequent subtree sum value? If there is a tie, return all the values with the highest frequency in any order.
 */
public class Solution {

    public int[] findFrequentTreeSum(TreeNode root) {
        Map<Integer, Integer> map = new HashMap<>();
        dfs(root, map);

        int max = Collections.max(map.values(), Integer::compareTo);
        return map.entrySet().stream().
                filter(e -> e.getValue() == max).
                    map(Map.Entry::getKey).
                        mapToInt(Integer::intValue).toArray();
    }

    private int dfs(TreeNode root, Map<Integer, Integer> map) {
        if (root == null) return 0;
        int sum = root.val + dfs(root.left, map) + dfs(root.right, map);
        map.put(sum, map.getOrDefault(sum, 0) + 1);
        return sum;
    }

}
