package fundamentals.tree.root2leafnum.lc257_binarytreepaths;

import fundamentals.tree.TreeNode;

import java.util.ArrayList;
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

    public List<String> binaryTreePaths(TreeNode root) {
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
