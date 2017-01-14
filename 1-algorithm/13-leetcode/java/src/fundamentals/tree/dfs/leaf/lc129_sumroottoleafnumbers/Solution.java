package fundamentals.tree.dfs.leaf.lc129_sumroottoleafnumbers;

import fundamentals.tree.TreeNode;

import java.util.LinkedList;

/**
 * Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.
 * An example is the root-to-leaf path 1->2->3 which represents the number 123.
 * Find the total sum of all root-to-leaf numbers.
 */
public class Solution {

    public int sumNumbers(TreeNode root) {
        return dfs(root, 0);
    }

    private int dfs(TreeNode root, int num) {
        if (root == null) return 0; // null root or missing child of non-leaf node
        num = num * 10 + root.val;
        if (root.left == null && root.right == null) return num;
        return dfs(root.left, num) + dfs(root.right, num);
    }

    // My 2nd: much faster by using int as path
    //          no need to recover it like LinkedList.
    public int sumNumbers2(TreeNode root) {
        return doSum(root, 0);
    }

    private int doSum(TreeNode root, int path) {
        if (root == null) {     // error1: root could be null not only when input is null, but when root has only one subtree
            return 0;
        }

        path = path * 10 + root.val; // error2: must put before the following codes

        if (root.left == null && root.right == null) {
            return path;
        }
        return doSum(root.left, path) + doSum(root.right, path);
    }

    // My 1st
    public int sumNumbers1(TreeNode root) {
        return doSumNumbers(new LinkedList<Integer>(), root);
    }

    private int doSumNumbers(LinkedList<Integer> path, TreeNode node) {
        if (node == null) {
            return 0;
        }

        path.addLast(node.val);

        int sum = 0;
        if (node.left == null && node.right == null) {
            for (int i = path.size()-1, j = 1; i >= 0; i--, j *= 10) {  // error1: not sum val
                sum += (path.get(i) * j);                               // caution: i-- & path.size()-1, forget return statement, recursion first argument...
            }
        } else {
            sum += doSumNumbers(path, node.left);
            sum += doSumNumbers(path, node.right);
        }

        path.removeLast();
        return sum;
    }
}
