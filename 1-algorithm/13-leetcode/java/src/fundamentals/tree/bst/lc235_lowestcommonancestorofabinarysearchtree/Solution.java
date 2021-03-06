package fundamentals.tree.bst.lc235_lowestcommonancestorofabinarysearchtree;

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

    // Why get rid of min/max?
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        if (root.val > Math.max(p.val, q.val)) return lowestCommonAncestor(root.left, p, q);
        if (root.val < Math.min(p.val, q.val)) return lowestCommonAncestor(root.right, p, q);
        return root;
    }

    // My 3AC. Nice iterative solution with O(h) time and O(1) space.
    public TreeNode lowestCommonAncestor3(TreeNode root, TreeNode p, TreeNode q) {
        while (true) { // assume root,p,q are non-null
            if (Math.max(p.val, q.val) < root.val) root = root.left;
            else if (root.val < Math.min(p.val, q.val)) root = root.right;
            else return root;
        }
    }

    // Improve recursive solution. get rid of min and max
    public TreeNode lowestCommonAncestor_recursive(TreeNode root, TreeNode p, TreeNode q) {
        if (root.val > p.val && root.val > q.val) return lowestCommonAncestor(root.left, p, q);
        if (root.val < p.val && root.val < q.val) return lowestCommonAncestor(root.right, p, q);
        return root;
    }

    // My 2AC: O(h) time and space
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        int min = Math.min(p.val, q.val);
        int max = Math.max(p.val, q.val);
        return min <= root.val && root.val <= max ? root : // must be '<=' if p or q is root
                (root.val > max
                        ? lowestCommonAncestor(root.left, p, q)
                        : lowestCommonAncestor(root.right, p, q));
    }

    // My 1AC
    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
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
