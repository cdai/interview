package fundamentals.tree.dfs.leaf.lc257_binarytreepaths;

import fundamentals.tree.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Given a binary tree, return all root-to-leaf paths.
 * For example, given the following binary tree:
 *     1
 *   /   \
 *  2     3
 *   \
 *    5
 * All root-to-leaf paths are: ["1->2->5", "1->3"]
 */
public class Solution {

    // My 2AC: O(N) time
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        doFindPath(result, root, "");
        return result;
    }

    private void doFindPath(List<String> result, TreeNode root, String path) {
        if (root == null) return;
        if (root.left == null && root.right == null) {
            result.add(path + root.val);
        } else {
            doFindPath(result, root.left, path + root.val + "->");
            doFindPath(result, root.right, path + root.val + "->");
        }
    }

    // My 1st
    public List<String> binaryTreePaths1(TreeNode root) {
        List<String> result = new ArrayList<>();
        traverse(result, "", root);
        return result;
    }

    private void traverse(List<String> result, String path, TreeNode root) {
        if (root == null) {
            return;
        }

        String curPath = path + "->" + root.val;
        if (root.left == null && root.right == null) {
            result.add(curPath.substring(2));
        } else {
            traverse(result, curPath, root.left);
            traverse(result, curPath, root.right);
        }
    }
}
