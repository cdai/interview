package fundamentals.tree.bst.lc173_binarysearchtreeiterator;

import fundamentals.tree.TreeNode;

/**
 * The key of Morris traverse is **link right pointer of rightmost child of left subtree before go left**,
 * so that when we complete left subtree we can follow the footprint back to root.
 * Then restore rightmost's right pointer and then go for right subtree.
 */
public class BSTIterator {

    private TreeNode cur;

    public BSTIterator(TreeNode root) {
        this.cur = root;
    }

    public boolean hasNext() {
        return cur != null;
    }

    public int next() { /* suppose hasNext() called ahead */
        TreeNode node = null;
        while (cur != null && node == null) {
            if (cur.left == null) {         // no left child, then go right (right child or back to root by footprint)
                node = cur;
                cur = cur.right;
            } else {
                TreeNode rmost = cur.left;
                while (rmost.right != null && rmost.right != cur) rmost = rmost.right;

                if (rmost.right == null) {  // 1.link rmost to root (cur) as footprint before go left
                    rmost.right = cur;
                    cur = cur.left;
                } else {                    // 2.We already get back to root by footprint, so restore then go right
                    node = cur;
                    rmost.right = null;
                    cur = cur.right;
                }
            }
        }
        return node.val;
    }

}
