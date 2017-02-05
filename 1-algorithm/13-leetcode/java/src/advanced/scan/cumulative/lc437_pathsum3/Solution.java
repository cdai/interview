package advanced.scan.cumulative.lc437_pathsum3;

import fundamentals.tree.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * You are given a binary tree in which each node contains an integer value. Find the number of paths that sum to a given value.
 * The path does not need to start or end at the root or a leaf, but it must go downwards (traveling only from parent nodes to child nodes).
 */
public class Solution {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(2);
        root.left.left.left = new TreeNode(3);
        root.left.left.right = new TreeNode(-2);
        root.left.right.right = new TreeNode(1);
        root.right = new TreeNode(-3);
        root.right.right = new TreeNode(11);
        System.out.println(new Solution().pathSum1(root, 8, 0));
    }

    // O(N) time Cumulative (Prefix) Array solution
    public int pathSum(TreeNode root, int sum) {
        Map<Integer,Integer> presum = new HashMap<>();
        presum.put(0, 1); // must-have: eg.[2,1], target=3 => premap=[(2,1),(3,1)] but (0,1) doesn't exist!
        return dfs(root, sum, 0, presum);
    }

    private int dfs(TreeNode root, int target, int sum, Map<Integer,Integer> presum) {
        if (root == null) return 0;
        sum += root.val;
        int ret = presum.getOrDefault(sum - target, 0);
        presum.put(sum, presum.getOrDefault(sum, 0) + 1);
        ret += dfs(root.left, target, sum, presum) +
                dfs(root.right, target, sum, presum);
        presum.put(sum, presum.get(sum) - 1);
        return ret;
    }

    // O(N^2) time worst case, O(NlogN) time for balanced tree.
    public int pathSum2(TreeNode root, int sum) {
        if (root == null) return 0;
        return dfs(root, 0, sum) +
                pathSum(root.left, sum) + // Wrong if call dfs(root.left, root.val+sum, target)
                pathSum(root.right, sum); // Since val+sum path in subtree doesn't connect to root necessrialy.
    }

    private int dfs(TreeNode root, int sum, int target) {
        if (root == null) return 0;
        return (root.val + sum == target ? 1 : 0) +
                dfs(root.left, root.val + sum, target) +
                dfs(root.right, root.val + sum, target);
    }

    // Wrong! Subtree has sum-root.val(8-5) path, but it doesn't mean it is connected to root to form a path = original sum 8.
    public int pathSum1(TreeNode root, int sum, int depth) {
        if (root == null) return 0;
        int ret = (root.val == sum ? 1 : 0) +
                pathSum1(root.left, sum, depth + 1) +
                pathSum1(root.right, sum, depth + 1) +
                pathSum1(root.left, sum - root.val, depth + 1) +
                pathSum1(root.right, sum - root.val, depth + 1);
        while (depth-- > 0) System.out.printf("\t");
        System.out.printf("Node %d get result %d\n", root.val, ret);
        return ret;
    }

}
