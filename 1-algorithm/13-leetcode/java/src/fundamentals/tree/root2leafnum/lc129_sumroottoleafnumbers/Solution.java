package fundamentals.tree.root2leafnum.lc129_sumroottoleafnumbers;

import fundamentals.tree.TreeNode;

import java.util.LinkedList;

/**
 * Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.
 * An example is the root-to-leaf path 1->2->3 which represents the number 123.
 * Find the total sum of all root-to-leaf numbers.
 */
public class Solution {
    public int sumNumbers(TreeNode root) {
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
