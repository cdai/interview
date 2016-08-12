package fundamentals.tree.construct.lc105_constructbinarytreefrompreorderandinordertraversal;

import fundamentals.tree.TreeNode;

import java.util.Arrays;

/**
 * Given preorder and inorder traversal of a tree, construct the binary tree.
 * Note: You may assume that duplicates do not exist in the tree.
 */
public class Solution {

    // My 2nd: easy index, but many sub array generated slow down the performance...
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0) {
            return null;
        }

        int rootVal = preorder[0];
        int rootIdx = search(inorder, rootVal);

        TreeNode root = new TreeNode(rootVal);
        root.left = buildTree(sub(preorder, 1, rootIdx + 1), sub(inorder, 0, rootIdx));
        root.right = buildTree(sub(preorder, rootIdx + 1), sub(inorder, rootIdx + 1));
        return root;
    }

    private int search(int[] a, int target) {
        int i = 0;
        for ( ; i < a.length; i++) {
            if (a[i] == target) {
                return i;
            }
        }
        return -1;
    }

    private int[] sub(int[] a, int from) {
        return Arrays.copyOfRange(a, from, a.length);
    }

    private int[] sub(int[] a, int from, int to) {
        return Arrays.copyOfRange(a, from, to);
    }

    // My 1st
    public TreeNode buildTree1(int[] preorder, int[] inorder) {
        if (preorder.length == 0 || inorder.length == 0 || preorder.length != inorder.length) {
            return null;
        }
        return buildTree(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    // Index in argument is inclusive for comprehension, but note JDK lib accepts exclusive index
    private TreeNode buildTree(int[] preorder, int pl, int ph, int[] inorder, int il, int ih) {
        /* Tree(pl,ph,il,ih): pl,ph,il,ih can construct a tree */
        if (pl > ph) {
            return null;
        }

        // 1.First one in preorder is the root
        /* Tree(pl,ph,il,ih) and pl <= ph and il <= ih */
        int val = preorder[pl];
        TreeNode root = new TreeNode(val);
        if (pl == ph) {
            return root;
        }

        // 2.Find its position in inorder, then elts before it are left subtree, others are right
        /* Tree(pl,ph,il,ih) and pl < ph and il < ih */
        int idx = il;
        for ( ; idx <= ih; idx++) {     // error1: binarySearch cause StackOverflow!!! Since inorder is not sorted
            if (inorder[idx] == val) {
                break;
            }
        }

        // 3.Construct subtree recursively, learned from preorder and inorder characteristic
        /* Tree(pl,ph,il,ih) and pl < ph and il < ih and idx <= ih (val must appear in inorder, otherwise input in wrong) */
        root.left = buildTree(preorder, pl + 1, pl + (idx - il), inorder, il, idx - 1); // error2: pl+(idx-il) not idx, since idx is position in inorder
        root.right = buildTree(preorder, pl + (idx - il) + 1, ph, inorder, idx + 1, ih);
        return root;
    }

}
