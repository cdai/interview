package fundamentals.tree.dfs.postorder.lc563_binarytreetilt;

import fundamentals.tree.TreeNode;
import org.junit.Assert;
import org.junit.Test;

/**
 * Given a binary tree, return the tilt of the whole tree.
 * The tilt of a tree node is defined as the absolute difference between the sum of all left subtree node values and
 * the sum of all right subtree node values. Null node has tilt 0.
 * The tilt of the whole tree is defined as the sum of all nodes' tilt.
 */
public class Solution {

    @Test
    public void test() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        Assert.assertEquals(1, findTilt(root));
    }

    private int tilt;

    public int findTilt(TreeNode root) {
        sum(root);
        return tilt;
    }

    private int sum(TreeNode root) {
        if (root == null) return 0;
        int left = sum(root.left);
        int right = sum(root.right);
        tilt += Math.abs(left - right);
        return left + right + root.val;
    }
}
