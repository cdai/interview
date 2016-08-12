package fundamentals.tree.leaf.lc257_binarytreepaths;

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

    // My 2nd: use return value to replace result argument
    public List<String> binaryTreePaths(TreeNode root) {
        return doFindPath(root, "");
    }

    private List<String> doFindPath(TreeNode root, String path) {
        if (root == null) {
            return Collections.emptyList();
        }

        List<String> result = new ArrayList<>();
        if (root.left == null && root.right == null) {
            result.add(path + root.val);
        } else {
            result.addAll(doFindPath(root.left, path + root.val + "->"));
            result.addAll(doFindPath(root.right, path + root.val + "->"));
        }
        return result;
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
