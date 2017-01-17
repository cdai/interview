package buildingblock.divideandconquer.lc106_constructbinarytreefrominorderandpostordertraversal;

import fundamentals.tree.TreeNode;

/**
 * Given inorder and postorder traversal of a tree, construct the binary tree.
 * Note: You may assume that duplicates do not exist in the tree.
 */
public class Solution {

    // 3AC. O(NlogN) time.
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return doBuild(inorder, postorder, 0, inorder.length - 1, 0, postorder.length - 1);
    }

    private TreeNode doBuild(int[] inorder, int[] postorder, int is, int ie, int ps, int pe) {
        if (is > ie) return null;

        int i = is, rootval = postorder[pe];
        while (inorder[i] != rootval) i++;

        TreeNode root = new TreeNode(rootval);
        root.left = doBuild(inorder, postorder, is, i - 1, ps, ps + i - is - 1);
        root.right = doBuild(inorder, postorder, i + 1, ie, ps + i - is, pe - 1);
        return root;
    }

    // My 2nd
    // O(N) time, O(logN) time
    public TreeNode buildTree2(int[] inorder, int[] postorder) {
        return doBuildTree(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
    }

    public TreeNode doBuildTree(int[] inorder, int inStart, int inEnd,
                                int[] postorder, int postStart, int postEnd) {
        if (inStart > inEnd) return null;
        int rootVal = postorder[postEnd];
        int i = inStart;
        for (; i <= inEnd && inorder[i] != rootVal; i++); // By changing to backward traversal it became ~1-2ms. It seems like the test cases are biased toward left subtrees being larger.

        TreeNode root = new TreeNode(rootVal);
        root.left = doBuildTree(inorder, inStart, i - 1, postorder, postStart, postStart + (i - inStart) - 1);
        root.right = doBuildTree(inorder, i + 1, inEnd, postorder, postStart + (i - inStart), postEnd - 1);
        return root;
    }

    // My 1st
    public TreeNode buildTree1(int[] inorder, int[] postorder) {
        if (inorder.length == 0 || postorder.length == 0 || inorder.length != postorder.length) {
            return null;
        }
        return buildTree(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
    }

    private TreeNode buildTree(int[] inorder, int il, int ih, int[] postorder, int pl, int ph) {
        if (il > ih || pl > ph) {
            return null;
        }
        //if (il == ih) {       // no need!!!
        //    return new TreeNode(inorder[il]);
        //}

        /* Tree(il,ih,pl,ph) and il <= ih */
        int val = postorder[ph];
        int idx = il;
        for ( ; (idx <= ih) && (inorder[idx] != val); idx++);

        TreeNode root = new TreeNode(val);
        root.left = buildTree(inorder, il, idx - 1, postorder, pl, pl + (idx - il) - 1);
        root.right = buildTree(inorder, idx + 1, ih, postorder, pl + (idx - il), ph - 1); // error: miswrite last one as pi-1... :|
        return root;
    }

}
