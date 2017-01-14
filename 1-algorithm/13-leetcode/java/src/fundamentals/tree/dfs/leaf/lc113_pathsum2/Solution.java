package fundamentals.tree.dfs.leaf.lc113_pathsum2;

import fundamentals.tree.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Given a binary tree and a sum, find all root-to-leaf paths
 * where each path's sum equals the given sum.
 */
public class Solution {

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> ret = new ArrayList<>();
        dfs(ret, new ArrayList<>(), root, sum);
        return ret;
    }

    private void dfs(List<List<Integer>> ret, List<Integer> path, TreeNode root, int sum) {
        if (root == null) return;

        path.add(root.val);
        if (root.left == null && root.right == null) {
            if (sum == root.val) ret.add(new ArrayList<>(path));
        } else {
            dfs(ret, path, root.left, sum - root.val);
            dfs(ret, path, root.right, sum - root.val);
        }
        path.remove(path.size() - 1);
    }

    // My 2nd
    public List<List<Integer>> pathSum2(TreeNode root, int sum) {
        List<List<Integer>> result = new ArrayList<>();
        pathSum(result, new ArrayList<>(), root, sum);
        return result;
    }

    private void pathSum(List<List<Integer>> result,
                         List<Integer> path, TreeNode root, int sum) {
        if (root == null) {
            return;
        }

        sum -= root.val;
        path.add(root.val);

        if (root.left == null && root.right == null && sum == 0) { // Wrong?!
            result.add(new ArrayList<>(path));
        } else {
            pathSum(result, path, root.left, sum);
            pathSum(result, path, root.right, sum);
        }

        path.remove(path.size() - 1);
    }

    // My 1st
    public List<List<Integer>> pathSum1(TreeNode root, int sum) {
        if (root == null) {
            return Collections.emptyList();
        }

        List<List<Integer>> paths = new ArrayList<>();
        LinkedList<Integer> path = new LinkedList<>();

        doPathSum(paths, path, root, sum);

        return paths;
    }

    private void doPathSum(List<List<Integer>> paths, LinkedList<Integer> path, TreeNode node, int sum) {
        path.add(node.val);
        sum -= node.val;

        if (node.left == null && node.right == null) {
            if (sum == 0) {
                paths.add(new ArrayList<>(path));
            }
        } else {
            if (node.left != null) {
                doPathSum(paths, path, node.left, sum);
            }
            if (node.right != null) {
                doPathSum(paths, path, node.right, sum);
            }
        }

        path.removeLast();
    }

    public List<List<Integer>> pathSum_Nonrecursive(TreeNode root, int sum) {
        List<List<Integer>> paths = new ArrayList<>();

        if (root == null) {
            return paths;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        int curSum = 0; // error1: init to 0 nor root.val
        LinkedList<Integer> curPath = new LinkedList<>();
        while (!stack.isEmpty()) {
            TreeNode n = stack.pop();
            curSum += n.val;
            curPath.add(n.val);

            if (n.left == null && n.right == null) {
                if (curSum == sum) {
                    paths.add(new ArrayList<>(curPath));
                }
                curSum -= n.val;
                curPath.removeLast(); // error2: when to remove internal node from path?
            } else {
                if (n.right != null) {
                    stack.push(n.right);
                }
                if (n.left != null) {
                    stack.push(n.left);
                }
            }
        }
        return paths;
    }
}
