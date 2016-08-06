package fundamentals.tree.lc235_lowestcommonancestorofabinarysearchtree;

import fundamentals.tree.TreeNode;

/**
 * Given a binary search tree (BST), find the lowest common ancestor (LCA) of two given nodes in the BST.
 * According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes v and w
 * as the lowest node in T that has both v and w as descendants (where we allow a node to be a descendant of itself).”
 * For example, the lowest common ancestor (LCA) of nodes 2 and 8 is 6.
 * Another example is LCA of nodes 2 and 4 is 2, since a node can be a descendant of itself according to the LCA definition.
 */
public class Solution {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        root.right = new TreeNode(1);
        System.out.println(new Solution().lowestCommonAncestor(root, root, root.right).val);
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        if (p.val > q.val) {
            TreeNode tmp = p;
            p = q;
            q = tmp;
        }

        if (p.val <= root.val && root.val <= q.val) {
            return root;
        }
        return (root.val > q.val)
                ? lowestCommonAncestor(root.left, p, q)
                : lowestCommonAncestor(root.right, p, q); // p.val > root.val
    }

}
