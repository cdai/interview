package fundamentals.tree.dfs.leaf.lc257_binarytreepaths;

import fundamentals.tree.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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

    // Optimize using StringBuilder. This works well for DFS.
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        doFindPath(result, root, new StringBuilder());
        return result;
    }

    private void doFindPath(List<String> result, TreeNode root, StringBuilder path) {
        if (root == null) return;
        int oldlen = path.length();
        path.append(root.val);
        if (root.left == null && root.right == null) {
            result.add(path.toString());
        } else {
            path.append("->");  // error: must extract to here since we only have one instance
            doFindPath(result, root.left, path);
            doFindPath(result, root.right, path);
        }
        path.setLength(oldlen);
    }

    // For BFS, use StringBuilder is a little cumbersome, since we clone it a lot.
    public List<String> binaryTreePaths_bfs(TreeNode root) {
        List<String> ret = new ArrayList<>();
        Queue<TreeNode> nodes = new LinkedList<>();
        Queue<String> paths = new LinkedList<>();
        if (root != null) {
            nodes.offer(root);
            paths.offer(String.valueOf(root.val));
        }
        while (!nodes.isEmpty()) {
            TreeNode node = nodes.poll();
            String path = paths.poll();
            if (node.left == null && node.right == null) {
                ret.add(path);
            } else {
                if (node.left != null) {
                    nodes.offer(node.left);
                    paths.offer(path + "->" + node.left.val);
                }
                if (node.right != null) {
                    nodes.offer(node.right);
                    paths.offer(path + "->" + node.right.val);
                }
            }
        }
        return ret;
    }

    public List<String> binaryTreePaths_wrapper(TreeNode root) {
        List<String> ret = new ArrayList<>();
        Queue<NodePath> q = new LinkedList<>();
        if (root != null) q.offer(new NodePath(root, String.valueOf(root.val)));
        while (!q.isEmpty()) {
            NodePath np = q.poll();
            if (np.node.left == null && np.node.right == null) {
                ret.add(np.path);
            } else {
                if (np.node.left != null)
                    q.offer(new NodePath(np.node.left, np.path + "->" + np.node.left.val));
                if (np.node.right != null)
                    q.offer(new NodePath(np.node.right, np.path + "->" + np.node.right.val));
            }
        }
        return ret;
    }

    class NodePath {
        TreeNode node;
        String path;
        NodePath(TreeNode node, String path) {
            this.node = node;
            this.path = path;
        }
    }

    // My 2AC: O(N) time
    public List<String> binaryTreePaths_string(TreeNode root) {
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
