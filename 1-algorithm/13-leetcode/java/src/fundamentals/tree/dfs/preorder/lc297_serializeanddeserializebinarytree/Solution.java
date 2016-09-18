package fundamentals.tree.dfs.preorder.lc297_serializeanddeserializebinarytree;

import fundamentals.tree.TreeNode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 */
public class Solution {

    // O(N) time
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        return serial(new StringBuilder(), root).toString();
    }

    private StringBuilder serial(StringBuilder str, TreeNode root) {
        if (root == null) return str.append("#");
        str.append(root.val).append(",");
        serial(str, root.left).append(","); // note execution order
        return serial(str, root.right);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        return deserial(new LinkedList<>(Arrays.asList(data.split(","))));
    }

    private TreeNode deserial(Queue<String> queue) {
        String val = queue.poll();
        if ("#".equals(val)) return null;
        TreeNode root = new TreeNode(Integer.valueOf(val));
        root.left = deserial(queue);
        root.right = deserial(queue);
        return root;
    }

}
