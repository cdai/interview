package fundamentals.tree.construct.lc106_constructbinarytreefrominorderandpostordertraversal;

import fundamentals.tree.TreeNode;

/**
 * Given inorder and postorder traversal of a tree, construct the binary tree.
 * Note: You may assume that duplicates do not exist in the tree.
 */
public class Solution {

    public TreeNode buildTree(int[] inorder, int[] postorder) {
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
