package fundamentals.tree.dfs.preorder.lc222_countcompletetreenodes;

import fundamentals.tree.TreeNode;

import java.util.function.UnaryOperator;

/**
 * Given a complete binary tree, count the number of nodes.
 * Definition of a complete binary tree from Wikipedia:
 * In a complete binary tree every level, except possibly the last, is completely filled,
 * and all nodes in the last level are as far left as possible.
 * It can have between 1 and 2h nodes inclusive at the last level h.
 */
public class Solution {

    // 2nd: still hard to finish on my own...
    // Have a taste of JDK 8 lambda, it turns out TLE sometimes...
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }

        // 1.Get height of leftmost and rightmost
        int leftHeight = getHeight(root, (node -> node.left));
        int rightHeight = getHeight(root, (node -> node.right));

        // 2.Calculate #nodes if the last level is full too
        if (leftHeight == rightHeight) {
            return (2 << (leftHeight - 1)) - 1;
        } else {
            return countNodes(root.left) + countNodes(root.right) + 1;
        }
    }

    private int getHeight(TreeNode root, UnaryOperator<TreeNode> next) {
        int height = 0;
        while (root != null) {
            root = next.apply(root);
            height++;
        }
        return height;
    }

    // 1st: O(logN*logN)
    public int countNodes1(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftH = 0;
        for (TreeNode node = root; ((node = node.left) != null); leftH++);

        int rightH = 0;
        for (TreeNode node = root; ((node = node.right) != null); rightH++);

        if (leftH == rightH) {
            return (2 << leftH) - 1; // error3: 2^(h+1) -> 2<<h
        } else {
            return countNodes1(root.left) + countNodes1(root.right) + 1;
        }
    }

    // O(N), TLE...
    public int countNodes2(TreeNode root) {
        if (root == null) {
            return 0;
        }

        TreeNode node = root;
        int height = 0;
        while ((node = node.left) != null) {
            height++;
        }

        // Assume all nodes on last level have two child, then -1 if leaf not found each time
        int total = (int) Math.pow(2, height + 1) - 1; // error1: last level has 2^h, totally 2^(h+1)-1
        return doCount(root, total, height, 0);
    }

    private int doCount(TreeNode root, int total, int height, int cur) {
        if (root == null) {
            return (cur == height + 1) ? total : total - 1; // error2: cur=h+1 not h means root is complete
        }

        total = doCount(root.left, total, height, cur + 1);
        total = doCount(root.right, total, height, cur + 1);
        return total;
    }

}
