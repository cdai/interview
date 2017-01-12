package advanced.scan.sequence.lc298_binarytreelongestconsecutivesequence;

import fundamentals.tree.TreeNode;

/**
 */
public class Solution {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(2);
        root.right.right = new TreeNode(4);
        root.right.right.right = new TreeNode(5);
        System.out.println(new Solution().longestConsecutive(root));

        root = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(2);
        root.right.left.left = new TreeNode(1);
        System.out.println(new Solution().longestConsecutive(root));
    }

    public int longestConsecutive(TreeNode root) {
        if (root == null) return 0;
        return dfs(root, 1);
    }

    private int dfs(TreeNode root, int len) {
        int left = 0, right = 0;
        if (root.left != null)
            left = dfs(root.left, root.val + 1 == root.left.val ? len + 1 : 1);
        if (root.right != null)
            right = dfs(root.right, root.val + 1 == root.right.val ? len + 1 : 1);
        return Math.max(len, Math.max(left, right)); // Compare length of subtree and if we stop here
    }

}
