package fundamentals.tree.bst.lc270_closestbinarysearchtreevalue;

import fundamentals.tree.TreeNode;

/**
 */
public class Solution {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(1);
        root.right = new TreeNode(6);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(7);
        System.out.println(new Solution().closestValue(root, 3));
        System.out.println(new Solution().closestValue_iterative(root, 3));
    }

    // Duplicate value in BST?
    // Overflow and precision
    public double closestValue(TreeNode root, double target) {
        double val1 = root.val;

        TreeNode node = (target < root.val) ? root.left : root.right;
        if (node == null)
            return val1;

        double val2 = closestValue(node, target);
        return Math.abs(val1 - target) < Math.abs(val2 - target) ? val1 : val2;
    }

    public double closestValue_iterative(TreeNode root, double target) {
        double closest = root.val;
        while (root != null) {
            if (Math.abs(root.val - target) < Math.abs(closest - target))
                closest = root.val;
            root = (target < root.val) ? root.left : root.right;
        }
        return closest;
    }

}
